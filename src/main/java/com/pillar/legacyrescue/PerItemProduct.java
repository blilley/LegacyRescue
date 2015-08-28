package com.pillar.legacyrescue;

import java.math.BigDecimal;

public class PerItemProduct implements Product {
    private Integer quantity;
    private String productName;
    private BigDecimal price;

    public PerItemProduct(String productName, int quantity, double price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = new BigDecimal(price);
    }

    @Override
    public BigDecimal getTotalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public String getSummary() {
        return String.format("%s $%.2f (%d items at $%.2f each)",
                productName, getTotalPrice(), quantity, price);
    }
}
