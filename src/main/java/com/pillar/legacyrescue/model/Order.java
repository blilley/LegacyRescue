package com.pillar.legacyrescue.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String customerName;
    private List<Product> orderProducts = new ArrayList<>();

    public BigDecimal getPrice() {
        return getTotalPrice();
    }

    public String getOrderSummary() {
        return getSummary();
    }

    public static Order createOrder() {
        return new Order();
    }

    public Order forCustomer(String customer) {
        this.customerName = customer;
        return this;
    }

    public Order addProduct(Product product) {
        orderProducts.add(product);
        return this;
    }

    public String getSummary() {
        StringBuilder summaryBuilder = new StringBuilder();
        summaryBuilder.append(String.format("ORDER SUMMARY FOR %s: \n", customerName));
        for (Product product : orderProducts){
            summaryBuilder.append(product.getSummary() + System.lineSeparator());
        }
        summaryBuilder.append(System.lineSeparator());
        summaryBuilder.append(String.format("Total Price: $%.2f", getTotalPrice()));

        return summaryBuilder.toString();
    }

    public BigDecimal getTotalPrice(){
        BigDecimal price = BigDecimal.valueOf(0);
        for (Product product : orderProducts){
             price = price.add(product.getTotalPrice());
        }

        return new BigDecimal(price.doubleValue(), new MathContext(3, RoundingMode.HALF_DOWN));
    }
}
