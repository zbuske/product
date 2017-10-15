package com.myretail.product.repository;


import com.myretail.product.domain.ProductPrice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface PriceRepository extends MongoRepository<ProductPrice, Long> {
    ProductPrice findByProductId(Long productId);
}


