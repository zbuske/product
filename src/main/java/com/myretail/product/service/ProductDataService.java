package com.myretail.product.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.product.config.ProductConfig;
import com.myretail.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
public class ProductDataService {
    private final ProductConfig productConfig;

    @Autowired
    public ProductDataService(ProductConfig productConfig) {
        this.productConfig = productConfig;
    }

    public Product getProduct(long id) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        String url = productConfig.getRedSkyApi() + id + productConfig.getRedSkyExclude();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode product = mapper.readTree(response.getBody());
        String name = product.path("product").path("item").path("product_description").path("title").textValue();
        return new Product(id, name);
    }
}