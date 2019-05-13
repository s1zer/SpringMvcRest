package com.example.springmvcrest.components.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    Optional<Room> getAllByCityContainingIgnoreCase(String city);

    Optional<Room> findByNumber(int number);

    Optional<Room> findById(Long id);

}
