package com.ms.ecommerce.bid;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class BidMapper {
    public Bid toBid(@Valid BidRequest request) {
        return Bid.builder()
                .id(request.id())
                .clientId(request.clientId())
                .productId(request.productId())
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .build();
    }
    public BidResponse fromBid(Bid bid) {
        return new BidResponse(
                bid.getId(),
                bid.getAmount(),
                bid.getPaymentMethod(),
                bid.getClientId(),
                bid.getProductId(),
                bid.getCreatedAt()
        );
    }
}
