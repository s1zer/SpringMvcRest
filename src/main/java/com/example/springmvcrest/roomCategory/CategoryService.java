package com.example.springmvcrest.roomCategory;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

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
