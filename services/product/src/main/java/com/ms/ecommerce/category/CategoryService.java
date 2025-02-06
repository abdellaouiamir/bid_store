package com.ms.ecommerce.category;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryResponse> findAll(){
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::fromCategory)
                .collect(Collectors.toList());
    }

    public CategoryResponse findById(Integer categoryId) {
        return categoryRepository.findById(categoryId)
                .map(categoryMapper::fromCategory)
                .orElseThrow(() -> new NotFoundException("category not found"));
    }
    public Integer createCategory(CategoryRequest categoryRequest) {
        var category = categoryMapper.toCategory(categoryRequest);
        return categoryRepository.save(category).getId();
    }
}
