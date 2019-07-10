package com.karma.api.controller;

import com.karma.model.Category;
import com.karma.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class APICategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category")
    public Category get(int id) {
        return categoryService.get(id);

    }
    @PostMapping("/category-list") /// ?name=abc
        public List<Category> search(@RequestParam("name") String name) {
            return categoryService.search(name);
    }

    @PostMapping("/category")
    public Category add(@RequestBody Category category) {
        categoryService.add(category);
        return category;
    }
    @DeleteMapping("/category") /// ?id=1
    public void delete(@RequestParam("id") int id) {
        categoryService.delete(id);
    }

    @PutMapping("/category")
    public void update(@RequestBody Category category) {
        categoryService.update(category);
    }
}
