package com.example.springmvcrest.room;

import com.example.springmvcrest.roomCategory.Category;
import com.example.springmvcrest.roomCategory.CategoryRepository;
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

    public void deleteRoom(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        room.ifPresent(r -> roomRepository.delete(r));
    }

    public boolean addRoom(RoomDto roomDto) {
        System.out.println("From service: ");
        System.out.println(roomDto.getRoomCategory());
        Optional<Category> category = categoryRepository.findByNameIgnoreCase(roomDto.getRoomCategory());
        Room roomEntity = roomMapper.toEntity(roomDto);
        roomRepository.save(roomEntity);

        return true;
    }
}
