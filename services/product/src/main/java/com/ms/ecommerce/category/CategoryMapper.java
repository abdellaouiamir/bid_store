package com.ms.ecommerce.category;

import org.springframework.stereotype.Service;

@Service
public class CategoryMapper {
    public CategoryResponse fromCategory(Category category) {
        return new CategoryResponse(category.getId(), category.getName(), category.getDescription());
    }
    public Category toCategory(CategoryRequest categoryRequest) {
        return Category.builder()
                .name(categoryRequest.name())
                .description(categoryRequest.description())
                .id(categoryRequest.id())
                .build();
    }
}
