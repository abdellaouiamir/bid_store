package com.ms.ecommerce.kafka;

import java.math.BigDecimal;

public record Product(
        Integer id,
        String name,
        String description,
        BigDecimal price,
        Integer category_id,
        String category_name,
        String category_description
) {
}
