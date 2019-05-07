package com.example.springmvcrest.controller;


import com.example.springmvcrest.User.UserDto;
import com.example.springmvcrest.User.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/panel")
public class PanelController {

    UserService userService;

    public PanelController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String adminPanel(Model model) {
        List<UserDto> allUsers = userService.getAlUsers();
        model.addAttribute("users", allUsers);
        return "adminPanel";
    }

    @GetMapping("/user")
    public String userPanel() {
        return "userPanel";
    }

    @PutMapping("/ban")
    public String setUserStatus(@RequestParam Long idUserToUpdate) {
        userService.setUserStatus(idUserToUpdate);
        return "redirect:/panel/admin";
    }
}
