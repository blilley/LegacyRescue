package com.pillar.legacyrescue;

import javafx.util.Pair;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Order order1 = Order.invoke();
        String orderSummary = order1.getOrderSummary();
        BigDecimal price = order1.getPrice();
        System.out.println(orderSummary);
        System.out.println("Total Price: $" + price);
    }
}

