package com.example.springmvcrest.controller.api;


import com.example.springmvcrest.components.room.RoomDto;
import com.example.springmvcrest.components.room.RoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestHomeController {

    private RoomService roomService;

    public RestHomeController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("")
    public List<RoomDto> findAllRooms(@RequestParam(required = false) String city) {
        if (city != null) {
            return roomService.getAllRoomsByCity(city);
        } else {
            return roomService.getAllRooms();
        }
    }

}
