package com.myretail.product.listener;

import com.myretail.product.domain.ProductPrice;
import com.myretail.product.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.AbstractMap.SimpleEntry;

@Component
public class ProductEventListener {
    private final PriceRepository priceRepository;

    @Autowired
    public ProductEventListener(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    private static final String CURRENCY_CODE = "USD";

    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {
        //Of the sample tcins provided, only 2 returned product data
        //•	Example product IDs: 13860428-y, 15117729-n, 16483589-n, 16696652-y, 16752456-n, 15643793-n)
        // I found 4 more by trying values in sequence with the 2 that worked.
        // 13860428, 13860427, 13860429, 16696652, 16696651, 16696650
        Map<Long, Double> priceMap = Collections.unmodifiableMap(Stream.of(
                new SimpleEntry<>(13860428L, 12.99),
                new SimpleEntry<>(13860427L, 35.00),
                new SimpleEntry<>(13860429L, 23.99),
                new SimpleEntry<>(16696651L, 89.50),
                new SimpleEntry<>(16696652L, 15.50),
                new SimpleEntry<>(16696650L, 67.99))
                .collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue)));

        priceMap.forEach((key, value) -> {
            ProductPrice productPrice = new ProductPrice(key, CURRENCY_CODE, value);
            priceRepository.save(productPrice);
        });
    }
}

