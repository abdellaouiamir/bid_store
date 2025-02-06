package com.ms.ecommerce.kafka.payment;

import com.ms.ecommerce.kafka.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotification(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String clientFirstName,
        String clientLastName,
        String clientEmail
) {
}
