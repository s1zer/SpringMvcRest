package com.example.springmvcrest.room;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<RoomDto> getAllRoomsByCity(String city) {
        return roomRepository.getAllByCityContainingIgnoreCase(city)
                .stream()
                .map(RoomMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<RoomDto> getAllRooms() {
        return roomRepository.findAll()
                .stream()
                .map(RoomMapper::toDto)
                .collect(Collectors.toList());
    }
}
