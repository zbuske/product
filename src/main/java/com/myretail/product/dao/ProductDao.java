package com.myretail.product.dao;


import com.myretail.product.model.Price;
import org.springframework.stereotype.Component;

@Component
public class ProductDao {

    public Price getPrice(long id) {
        return new Price("USD", 149.99);
    }

}