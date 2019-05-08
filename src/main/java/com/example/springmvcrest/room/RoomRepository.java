package com.example.springmvcrest.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

//    @Query("SELECT r FROM room r WHERE r.city LIKE '%city%'")
//    List<Room> getAllByCity(@Param("city") String city);

    List<Room> getAllByCityContainingIgnoreCase(String city);
}
