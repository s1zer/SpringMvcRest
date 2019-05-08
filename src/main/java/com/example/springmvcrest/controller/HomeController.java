package com.example.springmvcrest.controller;

import com.example.springmvcrest.room.RoomDto;
import com.example.springmvcrest.room.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {

    private RoomService roomService;

    public HomeController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/")
    public String home(@RequestParam(required = false) String city, Model model) {
        if (city != null && city.length() > 0) {
            List<RoomDto> rooms = roomService.getAllRoomsByCity(city);
            model.addAttribute("rooms", rooms);
        } else {
            List<RoomDto> rooms = roomService.getAllRooms();
            model.addAttribute("rooms", rooms);
        }
        return "index";
    }

    @GetMapping("/secured")
    public String secured() {
        return "secured";
    }
}
