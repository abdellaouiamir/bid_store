package com.ms.ecommerce.kafka;

import com.ms.ecommerce.bid.PaymentMethod;
import com.ms.ecommerce.client.ClientResponse;
import com.ms.ecommerce.product.ProductResponse;

import java.math.BigDecimal;

public record BidWinner(
        Integer bidId,
        BigDecimal bidAmount,
        PaymentMethod paymentMethod,
        ClientResponse client,
        Product product
) {
}
