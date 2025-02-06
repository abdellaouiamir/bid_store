package com.ms.ecommerce.bid;

import com.ms.ecommerce.product.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "bid-service",
        url = "${application.config.bid-url}"
)
public interface BidClient {
    @PostMapping("/getWinner")
    Optional<Void> getWinner(@RequestBody List<ProductResponse> productIds);
}
