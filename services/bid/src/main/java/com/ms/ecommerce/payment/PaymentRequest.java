package com.ms.ecommerce.payment;

import com.ms.ecommerce.bid.PaymentMethod;
import com.ms.ecommerce.client.ClientResponse;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer bidId,
        ClientResponse client
) {
}
