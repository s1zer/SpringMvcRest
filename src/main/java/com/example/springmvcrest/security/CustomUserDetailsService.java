package com.example.springmvcrest.security;


import com.example.springmvcrest.User.User;
import com.example.springmvcrest.User.UserRepository;
import com.example.springmvcrest.User.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (user != null && user.isActivated()) {
            org.springframework.security.core.userdetails.User userDetails =
                    new org.springframework.security.core.userdetails.User(
                            user.getEmail(),
                            user.getPassword(),
                            convertAuthorities(user.getRoles()));
            return userDetails;
        } else {
            throw new UsernameNotFoundException("User not found");

        }
    }

    private Set<GrantedAuthority> convertAuthorities(Set<UserRole> userRoles) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (UserRole ur : userRoles) {
            authorities.add(new SimpleGrantedAuthority(ur.getRole()));
        }
        return authorities;
    }
}
