package com.myretail.product.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productPrice")
public class ProductPrice {
    @Id
    private String id;
    private Long productId;
    private String currencyCode;
    private Double price;

    @PersistenceConstructor
    public ProductPrice() {

    }

    public ProductPrice(Long productId, String currencyCode, Double price) {
        this.productId = productId;
        this.currencyCode = currencyCode;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductPrice productPrice1 = (ProductPrice) o;

        if (id != null ? !id.equals(productPrice1.id) : productPrice1.id != null) return false;
        if (productId != null ? !productId.equals(productPrice1.productId) : productPrice1.productId != null)
            return false;
        if (currencyCode != null ? !currencyCode.equals(productPrice1.currencyCode) : productPrice1.currencyCode != null)
            return false;
        return price != null ? price.equals(productPrice1.price) : productPrice1.price == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (productId != null ? productId.hashCode() : 0);
        result = 31 * result + (currencyCode != null ? currencyCode.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ProductPrice{" +
                ", currencyCode='" + currencyCode + '\'' +
                ", productPrice=" + price +
                '}';
    }
}

