package com.example.springmvcrest.components.room;

import com.example.springmvcrest.components.roomCategory.Category;
import com.example.springmvcrest.components.roomCategory.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomMapper {

    private CategoryRepository categoryRepository;


    public RoomMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    static RoomDto toDto(Room room) {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setCity(room.getCity());
        roomDto.setNumber(room.getNumber());
        roomDto.setPrice(room.getPrice());
        roomDto.setAvailable(room.isAvailable());
        roomDto.setDescription(room.getDescription());
        if (room.getCategory() != null) {
            roomDto.setRoomCategory(room.getCategory().getName());
        }

        return roomDto;
    }

    Room toEntity(RoomDto roomDto) {
        Room roomEntity = new Room();
        roomEntity.setId(roomDto.getId());
        roomEntity.setCity(roomDto.getCity());
        roomEntity.setNumber(roomDto.getNumber());
        roomEntity.setPrice(roomDto.getPrice());
        roomEntity.setAvailable(roomDto.isAvailable());
        roomEntity.setDescription(roomDto.getDescription());
        System.out.println(roomDto.getRoomCategory());
        Optional<Category> roomCategory = categoryRepository.findByNameIgnoreCase(roomDto.getRoomCategory());
        roomCategory.ifPresent(roomEntity::setCategory);

        return roomEntity;
    }

}
