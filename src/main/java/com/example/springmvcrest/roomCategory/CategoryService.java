package com.example.springmvcrest.roomCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Set<String> getRoomCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(c -> c.getName())
                .collect(Collectors.toSet());
    }
}
