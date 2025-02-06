package com.ms.ecommerce.client;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record ClientRequest(
        String id,
        @NotNull(message = "Client firstName is required ")
        String firstName,
        @NotNull(message = "Client lastName is required")
        String lastName,
        @NotNull(message = "Client email is required")
        @Email(message = "Client email is not a valid email address")
        String email,
        Address address
) {
}
