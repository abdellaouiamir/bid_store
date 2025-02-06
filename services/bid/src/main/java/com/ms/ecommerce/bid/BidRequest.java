package com.ms.ecommerce.bid;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BidRequest(
        Integer id,
        @Positive(message = "price amount must be positive")
        BigDecimal amount,
        @NotNull(message = "payment methode should be precised")
        PaymentMethod paymentMethod,
        @NotNull(message = "client is required")
        @NotEmpty(message = "client is required")
        @NotBlank(message = "client is required")
        String clientId,
        @NotNull(message = "productId is required")
        Integer productId
) {
}
