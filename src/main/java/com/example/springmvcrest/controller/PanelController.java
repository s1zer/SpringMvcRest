package com.example.springmvcrest.controller;


import com.example.springmvcrest.roomCategory.CategoryService;
import com.example.springmvcrest.user.UserDto;
import com.example.springmvcrest.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/panel")
public class PanelController {

    UserService userService;
    CategoryService categoryService;

    public PanelController(UserService userService, CategoryService categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @GetMapping("/admin")
    public String adminPanel(Model model) {
        List<UserDto> allUsers = userService.getAlUsers();
        Set<String> roomCategories = categoryService.getRoomCategories();
        model.addAttribute("users", allUsers);
        model.addAttribute("roomCategories", roomCategories);
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
