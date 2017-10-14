package com.myretail.product.service;

import com.myretail.product.dao.ProductDao;
import com.myretail.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class ProductService {
    private final ProductDataService productDataService;
    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDataService productDataService, ProductDao productDao) {
        this.productDataService = productDataService;
        this.productDao = productDao;
    }

    public Product getProduct(Long id) throws IOException {
        Product product = productDataService.getProduct(id);
        product.setPrice(productDao.getPrice(id));
        return product;
    }
}
