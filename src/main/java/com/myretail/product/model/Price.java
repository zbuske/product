package com.myretail.product.model;

public class Price {
    public final String currencyCode;
    public final Double price;

    public Price(String currencyCode, Double price) {
        this.currencyCode = currencyCode;
        this.price = price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price price1 = (Price) o;

        if (currencyCode != null ? !currencyCode.equals(price1.currencyCode) : price1.currencyCode != null)
            return false;
        return price != null ? price.equals(price1.price) : price1.price == null;
    }

    @Override
    public int hashCode() {
        int result = currencyCode != null ? currencyCode.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Price{" +
                "currencyCode='" + currencyCode + '\'' +
                ", price=" + price +
                '}';
    }
}
