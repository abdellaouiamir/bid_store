package com.ms.ecommerce.category;

import jakarta.validation.constraints.NotNull;

public record CategoryRequest(
        Integer id,
        @NotNull(message = "the Category name is required")
        String name,
        @NotNull(message = "the Category name is required")
        String description
) {
}
