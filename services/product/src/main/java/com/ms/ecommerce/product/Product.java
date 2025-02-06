package com.ms.ecommerce.product;

import com.ms.ecommerce.category.Category;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean isActive;
    private LocalDateTime endAt;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
