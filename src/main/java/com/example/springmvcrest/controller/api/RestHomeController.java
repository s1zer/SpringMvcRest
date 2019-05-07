package com.example.springmvcrest.controller.api;


import com.example.springmvcrest.User.UserDto;
import com.example.springmvcrest.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestHomeController {

    UserService userService;

    @Autowired
    public RestHomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/panel/admin")
    public List<UserDto> getUsers() {
        return userService.getAlUsers();
    }

    @GetMapping("")
    public void home() {
        return;
    }

    @PutMapping("/panel/admin/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        if (!id.equals(userDto.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID user to update has to be the same in address URL");
        } else if (userDto.getPassword().isEmpty() && userDto.getNickname().isEmpty() && userDto.getEmail().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nick name, email and password can not be empty.");
        }
        userService.setUserStatus(id);
        return ResponseEntity.ok(userDto);
    }
}
