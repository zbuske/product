package com.myretail.product.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myretail.product.config.ProductConfig;
import com.myretail.product.domain.Product;
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
        String name = getNameFromResponse(response);
        return new Product(id, name);
    }

    protected String getNameFromResponse(ResponseEntity<String> response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode product = mapper.readTree(response.getBody());
        return product.path("product").path("item").path("product_description").path("title").textValue();
    }
}