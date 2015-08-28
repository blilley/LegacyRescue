package com.pillar.legacyrescue;

import javafx.util.Pair;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {

    static class Product{
        public String ProductName;
        public BigDecimal Price;
        public BigDecimal Weight;
        public Integer Quantity;
        public String PricingMethod;
    }


    public static void main(String[] args) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
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
        String orderSummary = "ORDER SUMMARY FOR " + order.getKey() + ": \n";

        for (Product orderProduct : order.getValue())
        {
            BigDecimal productPrice;
            orderSummary += orderProduct.ProductName;

            if(orderProduct.PricingMethod == "PerPound")
            {
                productPrice = orderProduct.Weight.multiply(orderProduct.Price);
                price = price.add(productPrice);
                orderSummary += (" $" + decimalFormat.format(productPrice) + " (" + orderProduct.Weight + " pounds at $" + decimalFormat.format(orderProduct.Price) + " per pound)");
            }
            else // Per item
            {
                productPrice = orderProduct.Price.multiply(BigDecimal.valueOf(orderProduct.Quantity));
                price = price.add(productPrice);
                orderSummary += (" $" + decimalFormat.format(productPrice) + " (" + orderProduct.Quantity + " items at $" + decimalFormat.format(orderProduct.Price) + " each)");
            }
            orderSummary += "\n";
        }
        System.out.println(orderSummary);
        System.out.println("Total Price: $" + price);
    }
}

