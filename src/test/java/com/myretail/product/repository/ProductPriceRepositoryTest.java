package com.myretail.product.repository;

import com.myretail.product.domain.ProductPrice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductPriceRepositoryTest {
    @Autowired
    private PriceRepository priceRepository;

    @Before
    public void setup() {
        ProductPrice productPrice1 = new ProductPrice(1234L, "USD", 29.99);
        ProductPrice productPrice2 = new ProductPrice(5678L, "USD", 13.50);

        assertNull(productPrice1.getId());
        assertNull(productPrice2.getId());
        priceRepository.save(productPrice1);
        priceRepository.save(productPrice2);
        assertNotNull(productPrice1.getId());
        assertNotNull(productPrice2.getId());
    }

    @Test
    public void testFindById() throws Exception {
        ProductPrice productPrice1 = priceRepository.findByProductId(1234L);
        ProductPrice productPrice2 = priceRepository.findByProductId(5678L);
        ProductPrice productPrice3 = priceRepository.findByProductId(9876L);
        assertNotNull(productPrice1);
        assertEquals(new Long(1234), productPrice1.getProductId());
        assertEquals("USD", productPrice1.getCurrencyCode());
        assertEquals(new Double(29.99), productPrice1.getPrice());
        assertEquals(new Long(5678), productPrice2.getProductId());
        assertEquals("USD", productPrice2.getCurrencyCode());
        assertEquals(new Double(13.50), productPrice2.getPrice());
        assertNull(productPrice3);
    }
}