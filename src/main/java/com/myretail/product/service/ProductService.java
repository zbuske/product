package com.myretail.product.service;

import com.myretail.product.domain.Product;
import com.myretail.product.domain.ProductPrice;
import com.myretail.product.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class ProductService {
    private final ProductDataService productDataService;
    private final PriceRepository priceRepository;

    @Autowired
    public ProductService(ProductDataService productDataService, PriceRepository productDao) {
        this.productDataService = productDataService;
        this.priceRepository = productDao;
    }

    public Product getById(Long id) throws IOException {
        Product product = productDataService.getProduct(id);
        product.setProductPrice(priceRepository.findByProductId(id));
        return product;
    }

    public ProductPrice saveOrUpdate(ProductPrice productPrice) {
        return priceRepository.save(productPrice);
    }
}
