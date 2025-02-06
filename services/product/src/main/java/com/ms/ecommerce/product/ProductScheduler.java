package com.ms.ecommerce.product;

import com.ms.ecommerce.bid.BidClient;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductScheduler {
    private final ProductRepository productRepository;
    private final BidClient bidClient;
    private final ProductMapper productMapper;
    @Scheduled(fixedRate = 60000)
    public void scheduledTask() {
        List<Product> products = productRepository.findByIsActiveIsTrue();
        List<Product> productsToSend = new ArrayList<Product>();
        products.forEach(product -> {
            boolean test = false;
            if(product.getEndAt().isEqual(LocalDateTime.now())){
                test = true;
                product.setIsActive(false);
            }
            if(product.getEndAt().isBefore(LocalDateTime.now())){
                test = true;
                product.setIsActive(false);
            }
            if(test){
                productsToSend.add(product);
            }
            productRepository.save(product);
        });
        //List<Integer> productIds = products.stream().map(Product::getId).collect(Collectors.toList());
        if (!productsToSend.isEmpty()) {
            var listProductResponse = products.stream().map(productMapper::fromProduct).collect(Collectors.toList());
            bidClient.getWinner(listProductResponse).orElseThrow(RuntimeException::new);
        }
    }
}
