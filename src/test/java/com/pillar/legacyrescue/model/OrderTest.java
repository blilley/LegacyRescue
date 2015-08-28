package com.pillar.legacyrescue.model;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class OrderTest {
    @Test
    public void getSummary_WithoutProducts_ReturnsSummary(){
        String customer = "John Doe";
        Order order = Order.createOrder().forCustomer(customer);
        order.addProduct(new PerPoundProduct("Pulled Pork", 0.5, 6.99));
        order.addProduct(new PerItemProduct("Coke", 2, 3.00));

        assertThat(order.getSummary(), is("ORDER SUMMARY FOR John Doe: \n" +
                "Pulled Pork $3.50 (0.5 pounds at $6.99 per pound)\n" +
                "Coke $6.00 (2 items at $3.00 each)\n\n" +
                "Total Price: $9.50"));
    }
}