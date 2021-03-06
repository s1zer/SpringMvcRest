package com.example.springmvcrest.components.user;

import com.example.springmvcrest.components.userRole.UserRole;
import com.example.springmvcrest.components.userRole.UserRoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final String DEFAULT_ROLE = "ROLE_USER";
    private static final boolean DEFAULT_ACTIVATION = false;
    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean addWithDefaultRole(User user) {

        Optional<User> userDuplicate = userRepository.findByEmailIgnoreCase(user.getEmail());
        if (!userDuplicate.isPresent()) {
            UserRole defaultUserRole = userRoleRepository.findByRole(DEFAULT_ROLE);
            user.setActivated(DEFAULT_ACTIVATION);
            String passwordHash = passwordEncoder.encode(user.getPassword());
            user.setPassword(passwordHash);
            user.getRoles().add(defaultUserRole);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    public List<UserDto> getAlUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    public void setUserStatus(Long idUserToUpdate) {
        Optional<User> userFindById = userRepository.findById(idUserToUpdate);
        userFindById.ifPresent(u ->
        {
            if (u.isActivated()) {
                u.setActivated(false);
            } else {
                u.setActivated(true);
            }
        });

        User savedUser = userRepository.save(userFindById.get());
    }

    public List<UserReservationDto> getUserReservation(String username) {
        return userRepository.findByEmail(username)
                .map(User::getReservations)
                .orElseThrow(UserNotFoundException::new)
                .stream()
                .map(UserReservationMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<UserReservationDto> getUserReservationByUserId(Long id) {
        return userRepository.findById(id)
                .map(User::getReservations)
                .orElseThrow(UserNotFoundException::new)
                .stream()
                .map(UserReservationMapper::toDto)
                .collect(Collectors.toList());
    }
}
