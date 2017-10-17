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

    @Before
    public void init() {
        productService = new ProductService(productDataService, priceRepository);
    }

    @Test
    public void getById() throws Exception {
        Long id = 1L;
        String name = "foo";
        Product product = new Product(id, name);
        ProductPrice productPrice = new ProductPrice(id, "USD", 29.99);

        when(productDataService.getProduct(id)).thenReturn(product);
        when(priceRepository.findByProductId(id)).thenReturn(productPrice);

        Product result = productService.getById(id);
        product.setProductPrice(productPrice);
        assertEquals(product, result);
    }
}