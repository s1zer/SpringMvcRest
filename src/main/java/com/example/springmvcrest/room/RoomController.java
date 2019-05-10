package com.example.springmvcrest.room;

import com.example.springmvcrest.message.Message;
import com.example.springmvcrest.roomCategory.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@Controller
public class RoomController {

    private RoomService roomService;
    private CategoryService categoryService;

    public RoomController(RoomService roomService, CategoryService categoryService) {
        this.roomService = roomService;
        this.categoryService = categoryService;
    }

    @GetMapping("/panel/admin/rooms")
    public String getRoom(@RequestParam(required = false) String city, Model model) {
        if (city != null && city.length() > 0) {
            List<RoomDto> rooms = roomService.getAllRoomsByCity(city);
            model.addAttribute("rooms", rooms);
        } else {
            List<RoomDto> rooms = roomService.getAllRooms();
            model.addAttribute("rooms", rooms);
        }

        model.addAttribute("room", new RoomDto());
        Set<String> roomCategories = categoryService.getRoomCategories();
        model.addAttribute("roomCategories", roomCategories);
        return "rooms";
    }

    @PostMapping("/room/delete")
    public String deleteRoom(@RequestParam Long idRoomToDelete) {
        roomService.deleteRoom(idRoomToDelete);
        return "redirect:/panel/admin/rooms";
    }

    @PostMapping("/room/add")
    public String addNewRoom(@Valid @ModelAttribute("room") RoomDto room, BindingResult bindingResult, Model model) {

        if (!bindingResult.hasErrors()) {
            if (!roomService.addRoom(room)) {
                model.addAttribute("message", new Message("Error", "This room already exists"));
                return "rooms";
            }
            return "redirect:/panel/admin/rooms";
        } else {
            return "rooms";
        }
    }

    @GetMapping("room/{id}")
    public String getRoomDetails(@PathVariable Long id, Model model) {
        try {
            RoomDto roomDto = roomService.getRoomById(id);
            model.addAttribute("room", roomDto);
            return "roomDetails";
        } catch (RoomNotFoundException e) {
            return "error";
        }
    }


}
