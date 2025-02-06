package com.ms.ecommerce.kafka;

public record Client(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
