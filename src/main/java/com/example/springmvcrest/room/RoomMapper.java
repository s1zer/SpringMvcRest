package com.example.springmvcrest.room;

import com.example.springmvcrest.roomCategory.Category;
import com.example.springmvcrest.roomCategory.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomMapper {

    private CategoryRepository categoryRepository;

    public RoomMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    RoomDto toDto(Room room) {
        RoomDto roomDto = new RoomDto();
        roomDto.setId(room.getId());
        roomDto.setNumber(room.getNumber());
        roomDto.setPrice(room.getPrice());
        roomDto.setDescription(room.getDescription());
        if (room.getCategory() != null) {
            roomDto.setRoomCategory(room.getCategory().getName());
        }

        return roomDto;
    }

    Room toEntity(RoomDto roomDto) {
        Room roomEntity = new Room();
        roomEntity.setId(roomDto.getId());
        roomEntity.setNumber(roomDto.getNumber());
        roomEntity.setPrice(roomDto.getPrice());
        roomEntity.setDescription(roomDto.getDescription());
        Optional<Category> roomCategory = categoryRepository.findByName(roomDto.getRoomCategory());
        roomCategory.ifPresent(roomEntity::setCategory);

        return roomEntity;
    }

}
