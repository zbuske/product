package com.myretail.product.service;

import com.myretail.product.domain.Product;
import com.myretail.product.domain.ProductPrice;
import com.myretail.product.repository.PriceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    @Mock
    PriceRepository priceRepository;

    @Mock
    ProductDataService productDataService;

    ProductService productService;
    ProductPrice productPrice;
    Long id = 1L;
    String productPriceId = "5678";

    @Before
    public void init() {
        productService = new ProductService(productDataService, priceRepository);
        productPrice = new ProductPrice(id, "USD", 29.99);
        productPrice.setId(productPriceId);

    }

    @Test
    public void getById() throws Exception {
        String name = "foo";
        Product product = new Product(id, name);
        ProductPrice productPrice = new ProductPrice(id, "USD", 29.99);

        when(productDataService.getProduct(id)).thenReturn(product);
        when(priceRepository.findByProductId(id)).thenReturn(productPrice);

        Product result = productService.getById(id);
        product.setProductPrice(productPrice);
        assertEquals(product, result);
    }

    @Test
    public void setUpdatablePriceExisting() {
        Product product = new Product(id, "foo");
        product.setProductPrice(productPrice);


        String currencyCode = "AUD";
        Double value = 13.50;
        ProductPrice existingProductPrice = new ProductPrice(id, currencyCode, value);
        existingProductPrice.setId("1234");

        when(priceRepository.findByProductId(id)).thenReturn(existingProductPrice);

        productService.setUpdatablePrice(product);

        assertEquals(existingProductPrice.getId(), product.getProductPrice().getId());
        assertEquals(existingProductPrice.getProductId(), product.getProductPrice().getProductId());
        assertEquals(productPrice.getProductId(), product.getProductPrice().getProductId());
        assertEquals(productPrice.getCurrencyCode(), product.getProductPrice().getCurrencyCode());
        assertEquals(productPrice.getValue(), product.getProductPrice().getValue());
    }

    @Test
    public void setUpdatablePriceNew() {
        Product product = new Product(id, "foo");
        product.setProductPrice(productPrice);


        when(priceRepository.findByProductId(id)).thenReturn(null);

        productService.setUpdatablePrice(product);

        assertEquals(productPrice.getId(), product.getProductPrice().getId());
        assertEquals(productPrice.getProductId(), product.getProductPrice().getProductId());
        assertEquals(productPrice.getCurrencyCode(), product.getProductPrice().getCurrencyCode());
        assertEquals(productPrice.getValue(), product.getProductPrice().getValue());
    }

}