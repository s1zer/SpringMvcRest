package com.example.springmvcrest.roomCategory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/panel/admin/categories")
    public String getCategories(Model model) {
        Set<String> roomCategories = categoryService.getRoomCategories();
        model.addAttribute("roomCategories", roomCategories);
        return "categories";
    }
}
