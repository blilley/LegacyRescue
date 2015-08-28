package com.pillar.legacyrescue;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

public class OrderTest {
    @Test
    public void invoke_returnsOrderWithSummary(){

        Order result = Order.invoke();

        String orderSummary = result.getOrderSummary();
        assertThat(orderSummary, containsString("ORDER SUMMARY FOR John Doe:"));
        assertThat(orderSummary, containsString("Pulled Pork $3.50 (0.5 pounds at $7.0 per pound)"));
        assertThat(orderSummary, containsString("Coke $6 (2 items at $3 each)"));
        assertThat(result.getPrice().toString(), is("9.50"));

        assertThat(orderSummary, is("ORDER SUMMARY FOR John Doe: \r\n" +
                "Pulled Pork $3.50 (0.5 pounds at $7.0 per pound)\r\n" +
                "Coke $6 (2 items at $3 each)\r\n"));
    }
}