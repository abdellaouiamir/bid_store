package com.ms.ecommerce.category;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok(categoryService.findAll());
    }
    @GetMapping("/{category-id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable("category-id") Integer categoryId) {
        return ResponseEntity.ok(categoryService.findById(categoryId));
    }
    @PostMapping
    public ResponseEntity<Integer> createCategory(@RequestBody @Valid CategoryRequest categoryRequest) {
        return ResponseEntity.ok(categoryService.createCategory(categoryRequest));
    }
}
