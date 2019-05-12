package com.example.springmvcrest.user;


import com.example.springmvcrest.roomCategory.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/panel")
public class AdminPanelController {

    UserService userService;
    CategoryService categoryService;

    public AdminPanelController(UserService userService, CategoryService categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @GetMapping("/admin/users")
    public String adminPanel(Model model) {
        List<UserDto> allUsers = userService.getAlUsers();
        model.addAttribute("users", allUsers);
        return "adminPanel";
    }

    @PutMapping("/updateUserStatus")
    public String setUserStatus(@RequestParam Long idUserToUpdate) {
        userService.setUserStatus(idUserToUpdate);
        return "redirect:/panel/admin";
    }

}
