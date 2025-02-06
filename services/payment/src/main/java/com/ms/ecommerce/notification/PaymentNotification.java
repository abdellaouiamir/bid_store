package com.ms.ecommerce.notification;

import com.ms.ecommerce.payment.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotification(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String clientFirstName,
        String clientLastName,
        String clientEmail
) {
}
