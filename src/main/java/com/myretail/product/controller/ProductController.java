package com.myretail.product.controller;

import com.myretail.product.domain.Product;
import com.myretail.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
        return productService.getById(id);
    }

    @RequestMapping(path = "/products/{id}", method = RequestMethod.PUT)
    public Product updateById(@RequestBody Product product, @PathVariable("id") Long id) throws IOException {
        // TODO: Determine what should happen if the id parameter doesn't match the id in the product (we don't really need both)
        product.setProductPrice(productService.saveOrUpdatePriceInfo(product));
        return product;
    }
}
