package com.ms.ecommerce.product;

import com.ms.ecommerce.category.Category;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductRequest(
        Integer id,
        @NotNull(message = "product name is required")
        String name,
        String description,
        Boolean isActive,
        LocalDateTime endAt,
        @Positive(message = "the price must be positive")
        BigDecimal price,
        @NotNull(message = "the category id is required")
        Integer category_id
) {
}
