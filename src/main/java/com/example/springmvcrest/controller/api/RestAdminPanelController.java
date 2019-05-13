package com.example.springmvcrest.controller.api;

import com.example.springmvcrest.components.room.RoomDto;
import com.example.springmvcrest.components.room.RoomService;
import com.example.springmvcrest.components.roomCategory.CategoryService;
import com.example.springmvcrest.components.user.UserDto;
import com.example.springmvcrest.components.user.UserReservationDto;
import com.example.springmvcrest.components.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/panel/admin")
public class RestAdminPanelController {

    private UserService userService;
    private RoomService roomService;
    private CategoryService categoryService;

    public RestAdminPanelController(UserService userService, RoomService roomService, CategoryService categoryService) {
        this.userService = userService;
        this.roomService = roomService;
        this.categoryService = categoryService;
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.getAlUsers();
    }

    @GetMapping("/rooms")
    public List<RoomDto> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/room/categories")
    public Set<String> getAllRoomCategories() {
        return categoryService.getRoomCategories();
    }

    @GetMapping("/user/{id}/reservations")
    public List<UserReservationDto> getUserReservations(@PathVariable Long id) {
        return userService.getUserReservationByUserId(id);
    }
}
