package com.pillar.legacyrescue;

import javafx.util.Pair;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private BigDecimal price;
    private String orderSummary;

    public Order(BigDecimal price, String orderSummary) {
        this.price = price;
        this.orderSummary = orderSummary;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getOrderSummary() {
        return orderSummary;
    }

    public static Order invoke() {
        Pair<String, List<Product>> order = new Pair<>("John Doe", new ArrayList<Product>()
        {{
                add(new Product()
                {
                    {
                        ProductName = "Pulled Pork";
                        Price = new BigDecimal(6.99, new MathContext(2, RoundingMode.HALF_DOWN));
                        Weight = BigDecimal.valueOf(0.5);
                        PricingMethod = "PerPound";
                    }
                });
                add(new Product()
                {
                    {
                        ProductName = "Coke";
                        Price = new BigDecimal(3.0, new MathContext(2, RoundingMode.HALF_DOWN));
                        Quantity = 2;
                        PricingMethod = "PerItem";
                    }
                });
            }});

        BigDecimal price = BigDecimal.valueOf(0.0);
        String orderSummary = "ORDER SUMMARY FOR " + order.getKey() + ": \r\n";

        for (Product orderProduct : order.getValue())
        {
            BigDecimal productPrice;
            orderSummary += orderProduct.ProductName;

            if(orderProduct.PricingMethod == "PerPound")
            {
                productPrice = orderProduct.Weight.multiply(orderProduct.Price);
                price = price.add(productPrice);
                orderSummary += (" $" + productPrice + " (" + orderProduct.Weight + " pounds at $" + orderProduct.Price + " per pound)");
            }
            else // Per item
            {
                productPrice = orderProduct.Price.multiply(BigDecimal.valueOf(orderProduct.Quantity));
                price = price.add(productPrice);
                orderSummary += (" $" + productPrice + " (" + orderProduct.Quantity + " items at $" + orderProduct.Price + " each)");
            }
            orderSummary += "\r\n";
        }
        return new Order(price, orderSummary);
    }
}
