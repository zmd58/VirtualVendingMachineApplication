package com.techelevator.Inventory;

import junit.framework.TestCase;
import org.junit.Test;

public class BeveragesTest extends TestCase {

    @Test
    public void testMessage() {
        Item drink = new Beverages(2.15, "Drink", "random drink", 5);
        assertEquals("Glug Glug, Yum!", drink.getMessage());
    }
}