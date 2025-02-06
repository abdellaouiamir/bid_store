package com.ms.ecommerce.product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        Boolean isActive,
        LocalDateTime endAt,
        BigDecimal price,
        Integer category_id,
        String category_name,
        String category_description
) {
}
