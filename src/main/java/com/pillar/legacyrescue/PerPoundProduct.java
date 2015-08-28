package com.pillar.legacyrescue;

import java.math.BigDecimal;

public class PerPoundProduct implements Product{
    private BigDecimal weight;
    private String productName;
    private BigDecimal price;

    public PerPoundProduct(String productName, double weight, double price) {
        this.productName = productName;
        this.weight = new BigDecimal(weight);
        this.price = new BigDecimal(price);
    }

    public BigDecimal getTotalPrice(){
        return weight.multiply(price);
    }

    public String getSummary(){
        return String.format("%s $%.2f (%.1f pounds at $%.2f per pound)",
                productName, getTotalPrice(), weight, price);
    }
}
