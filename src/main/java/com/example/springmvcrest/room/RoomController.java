package com.example.springmvcrest.room;

import org.springframework.stereotype.Controller;

@Controller
public class RoomController {

    private RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


}
