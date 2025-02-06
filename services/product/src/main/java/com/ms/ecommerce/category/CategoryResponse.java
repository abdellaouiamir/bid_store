package com.ms.ecommerce.category;

import com.ms.ecommerce.product.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.List;

public record CategoryResponse(
        Integer id,
        String name,
        String description
) {
}
