package com.ms.ecommerce.bid;

import com.ms.ecommerce.product.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bid")
@RequiredArgsConstructor
public class BidController {
    private final BidService bidService;

    @PostMapping
    public ResponseEntity<Integer> createBid(
            @RequestBody @Valid BidRequest request
    ) {
        return ResponseEntity.ok(bidService.createBid(request));
    }
    @GetMapping("/{product-id}")
    public ResponseEntity<BidResponse> getHighestBid(@PathVariable("product-id") Integer productId) {
        return ResponseEntity.ok(bidService.getHighestBid(productId));
    }
    @PostMapping("/getWinner")
    public ResponseEntity<Void> sendWinner(@RequestBody List<ProductResponse> listProducts) {
        bidService.sendWinner(listProducts);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/ref/{bid-id}")
    public ResponseEntity<BidResponse> getBid(@PathVariable("bid-id") Integer bidId) {
        return ResponseEntity.ok(bidService.getBid(bidId));
    }
}

