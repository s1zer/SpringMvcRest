package com.example.springmvcrest.components.user;


import com.example.springmvcrest.components.roomCategory.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/panel/admin")
public class AdminPanelController {

    UserService userService;
    CategoryService categoryService;

    public AdminPanelController(UserService userService, CategoryService categoryService) {
        this.userService = userService;
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String adminPanel() {
        return "adminPanel";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<UserDto> allUsers = userService.getAlUsers();
        model.addAttribute("users", allUsers);
        return "users";
    }

    @PostMapping("/updateUserStatus")
    public String setUserStatus(@RequestParam Long idUserToUpdate) {
        userService.setUserStatus(idUserToUpdate);
        return "redirect:/panel/admin/users";
    }

}
