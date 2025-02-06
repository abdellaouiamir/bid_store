package com.ms.ecommerce.bid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BidRepository extends JpaRepository<Bid, Integer> {
    @Query(value = "SELECT * FROM bid WHERE product_id = :productId ORDER BY amount DESC LIMIT 1", nativeQuery = true)
    public Bid findHighestBidByProductId(@Param("productId") int productId);

}
