package com.ms.ecommerce.bid;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BidResponse(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String clientId,
        Integer productId,
        LocalDateTime createdAt
) {
}
