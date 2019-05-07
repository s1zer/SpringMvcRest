package com.example.springmvcrest.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    Optional<User> findByEmailIgnoreCase(String email);

    @Override
    Optional<User> findById(Long id);
}