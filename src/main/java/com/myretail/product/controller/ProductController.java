package com.myretail.product.controller;

import com.myretail.product.model.Product;
import com.myretail.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(path = "/products/{id}", method = RequestMethod.GET)
    public Product getById(@PathVariable("id") Long id) throws IOException {
            return productService.getProduct(id);
    }
}
