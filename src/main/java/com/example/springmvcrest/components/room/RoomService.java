package com.example.springmvcrest.components.room;

import com.example.springmvcrest.components.roomCategory.Category;
import com.example.springmvcrest.components.roomCategory.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private RoomRepository roomRepository;
    private CategoryRepository categoryRepository;
    private RoomMapper roomMapper;

    public RoomService(RoomRepository roomRepository, CategoryRepository categoryRepository, RoomMapper roomMapper) {
        this.roomRepository = roomRepository;
        this.categoryRepository = categoryRepository;
        this.roomMapper = roomMapper;
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

    public RoomDto getRoomById(Long id) {
        Optional<Room> findRoom = roomRepository.findById(id);
        if (findRoom.isPresent()) {
            return findRoom.map(r -> RoomMapper.toDto(r)).get();
        } else {
            throw new RoomNotFoundException();
        }
    }

    public boolean addRoom(RoomDto roomDto) {
        Optional<Room> duplicateRoom = roomRepository.findByNumber(roomDto.getNumber());
        if (duplicateRoom.isPresent()) {
            return false;
        } else {
            Optional<Category> category = categoryRepository.findByNameIgnoreCase(roomDto.getRoomCategory());
            Room roomEntity = roomMapper.toEntity(roomDto);
            roomRepository.save(roomEntity);
            return true;
        }
    }

    public boolean deleteRoom(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isPresent() && room.get().isAvailable()) {
            roomRepository.delete(room.get());
            return true;
        } else {
            return false;
        }
    }

}
