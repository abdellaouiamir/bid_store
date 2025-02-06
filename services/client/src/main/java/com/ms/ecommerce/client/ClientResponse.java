package com.ms.ecommerce.client;

public record ClientResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
