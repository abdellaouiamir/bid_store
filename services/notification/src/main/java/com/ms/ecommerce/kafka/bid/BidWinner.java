package com.ms.ecommerce.kafka.bid;

import com.ms.ecommerce.kafka.Client;
import com.ms.ecommerce.kafka.PaymentMethod;
import com.ms.ecommerce.kafka.Product;

import java.math.BigDecimal;

public record BidWinner(
        Integer bidId,
        BigDecimal bidAmount,
        PaymentMethod paymentMethod,
        Client client,
        Product product
) {
}
