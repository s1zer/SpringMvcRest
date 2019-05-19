package com.example.springmvcrest.components.room;

import com.example.springmvcrest.components.message.Message;
import com.example.springmvcrest.components.reservation.ReservationDto;
import com.example.springmvcrest.components.roomCategory.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class RoomController {

    private RoomService roomService;
    private CategoryService categoryService;

    public RoomController(RoomService roomService, CategoryService categoryService) {
        this.roomService = roomService;
        this.categoryService = categoryService;
    }

    @GetMapping("room/{id}")
    public String getRoomDetails(@PathVariable Long id, Model model) {
        try {
            RoomDto roomDto = roomService.getRoomById(id);
            model.addAttribute("room", roomDto);
            model.addAttribute("reservation", new ReservationDto());
            return "roomDetails";
        } catch (RoomNotFoundException e) {
            model.addAttribute("message", new Message("Error", "Room has not been found."));
            return "error";
        }
    }

    @GetMapping("panel/admin/rooms")
    public String getRoom(@RequestParam(required = false) String city, Model model) {
        if (city != null && city.length() > 0) {
            model.addAttribute("rooms", roomService.getAllRoomsByCity(city));
        } else {
            model.addAttribute("rooms", roomService.getAllRooms());
        }
        model.addAttribute("room", new RoomDto());
        model.addAttribute("roomCategories", categoryService.getRoomCategories());
        return "rooms";
    }

    @PostMapping("/room/add")
    public String addNewRoom(@Valid @ModelAttribute("room") RoomDto room, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "rooms";
        } else if (roomService.addRoom(room)) {
            return "redirect:/panel/admin/rooms";
        } else {
            model.addAttribute("message", new Message("Error", "This rooms already exists"));
            return "rooms";
        }
    }

    @PostMapping("/room/delete")
    public String deleteRoom(@RequestParam Long idRoomToDelete) {
        roomService.deleteRoom(idRoomToDelete);
        return "redirect:/panel/admin/rooms";
    }

}
