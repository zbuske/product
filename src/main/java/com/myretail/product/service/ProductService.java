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
        //TODO: What should happen if a name isn't found
        Product product = productDataService.getProduct(id);
        //TODO: What should happen if a price isn't found
        product.setProductPrice(priceRepository.findByProductId(id));
        return product;
    }

    public ProductPrice saveOrUpdatePriceInfo(Product product) {
        // Because the mongo id may have changed, get the one from  the datastore,
        // if it exists, update it, and use that one. Otherwise we won't persist the data properly.
        setUpdatablePrice(product);
        return priceRepository.save(product.getProductPrice());

    }

    protected void setUpdatablePrice(Product product) {
        ProductPrice currentPrice = priceRepository.findByProductId(product.getId());
        if (currentPrice != null) {
            currentPrice.setCurrencyCode(product.getProductPrice().getCurrencyCode());
            currentPrice.setValue(product.getProductPrice().getValue());
            product.setProductPrice(currentPrice);
        }
    }
}
