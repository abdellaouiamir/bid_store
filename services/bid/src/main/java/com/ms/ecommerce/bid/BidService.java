package com.ms.ecommerce.bid;

import com.ms.ecommerce.client.ClientClient;
import com.ms.ecommerce.exception.BidTooLowException;
import com.ms.ecommerce.exception.ClientNotFoundException;
import com.ms.ecommerce.exception.ProductNotFoundException;
import com.ms.ecommerce.kafka.BidProducer;
import com.ms.ecommerce.kafka.BidWinner;
import com.ms.ecommerce.kafka.Product;
import com.ms.ecommerce.payment.PaymentClient;
import com.ms.ecommerce.payment.PaymentRequest;
import com.ms.ecommerce.product.ProductClient;
import com.ms.ecommerce.product.ProductResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BidService {
    private final BidRepository bidRepository;
    private final ClientClient clientClient;
    private final ProductClient productClient;
    private final PaymentClient paymentClient;
    private final BidMapper bidMapper;
    private final BidProducer bidProducer;
    public Integer createBid(@Valid BidRequest request) {
        // check the client
        var client = this.clientClient.findClientById(request.clientId())
                .orElseThrow(() -> new ClientNotFoundException("Cannot create Bid :: Client not found"));
        // check the product
        var product = this.productClient.findProductById(request.productId())
                .orElseThrow(() -> new ProductNotFoundException("Cannot create Bid :: Product not found"));
        // check if the price higher than the previous price
        if(LocalDateTime.now().isAfter(product.endAt())){
            throw new BidTooLowException("Time is up");
        }
        var highestPriceBid = this.bidRepository.findHighestBidByProductId(request.productId());
        if (highestPriceBid != null) {
            if(request.amount().compareTo(highestPriceBid.getAmount()) <= 0){
                throw new BidTooLowException("Cannot create Bid :: Price too low");
            }
        } else if (request.amount().compareTo(product.price()) < 0) {
            throw new BidTooLowException("Cannot create Bid :: Price too low");
        }
        // add the bid
        var bid = bidRepository.save(bidMapper.toBid(request));
        return bid.getId();
    }

    public void sendWinner(List<ProductResponse> listProducts){
        listProducts.forEach(this::getWinner);
    }
    public void getWinner(ProductResponse product) {
        var bid = this.bidRepository.findHighestBidByProductId(product.id());
        if (bid == null) {
            throw new ProductNotFoundException("Product has no bid");
        }
        var client = this.clientClient.findClientById(bid.getClientId())
                .orElseThrow(() -> new ClientNotFoundException("Cannot create Bid :: Client not found"));
        bidProducer.sendBidWinner(new BidWinner(
                bid.getId(),
                bid.getAmount(),
                bid.getPaymentMethod(),
                client,
                new Product(
                        product.id(),
                        product.name(),
                        product.description(),
                        product.price(),
                        product.category_id(),
                        product.category_name(),
                        product.category_description()
                )
        ));
        paymentClient.requestPayment(new PaymentRequest(
                bid.getAmount(),
                bid.getPaymentMethod(),
                bid.getId(),
                client
        ));
        System.out.println("stop");
    }
    public BidResponse getHighestBid(Integer productId) {
        var bid = bidRepository.findHighestBidByProductId(productId);
        if (bid == null) {
            throw new ProductNotFoundException("Product has no bid");
        }
        return bidMapper.fromBid(bid);
    }
    public BidResponse getBid(Integer id) {
        Bid bid = bidRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Bid not Found"));
        return bidMapper.fromBid(bid);
    }
}
