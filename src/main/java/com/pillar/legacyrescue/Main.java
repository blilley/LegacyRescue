package com.pillar.legacyrescue;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    static class Product{
        public String ProductName;
        public BigDecimal Price;
        public BigDecimal Weight;
        public Integer Quantity;
        public String PricingMethod;
    }


    public static void main(String[] args) {
        Map<String, List<Product>> order = new HashMap<>();

        Product pulledPork = new Product();
        pulledPork.ProductName = "Pulled Pork";
        pulledPork.Price = new BigDecimal(6.99, new MathContext(2, RoundingMode.HALF_DOWN));
        pulledPork.Weight = BigDecimal.valueOf(0.5);
        pulledPork.PricingMethod = "PerPound";

        Product coke = new Product();
        coke.ProductName = "Coke";
        coke.Price = new BigDecimal(3.0, new MathContext(2, RoundingMode.HALF_DOWN));
        coke.Quantity = 2;
        coke.PricingMethod = "PerItem";

        order.put("John Doe", new ArrayList<Product>(){{add(pulledPork); add(coke);}});

        BigDecimal price = BigDecimal.valueOf(0.0);
        String orderSummary = "ORDER SUMMARY FOR " + order.keySet().iterator().next() + ": \r\n";

        for (Product orderProduct : order.values().iterator().next())
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
        System.out.println(orderSummary);
        System.out.println("Total Price: $" + price);

        try
        {
            System.in.read();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

