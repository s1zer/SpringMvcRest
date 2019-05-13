package com.example.springmvcrest.controller.api;


import com.example.springmvcrest.components.room.RoomDto;
import com.example.springmvcrest.components.room.RoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/room")
public class RestRoomController {

    private RoomService roomService;

    public RestRoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/{id}")
    public RoomDto getRoomDetails(@PathVariable Long id) {
        return roomService.getRoomById(id);
    }

}
