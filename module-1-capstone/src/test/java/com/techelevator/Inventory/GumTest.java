package com.techelevator.Inventory;

import junit.framework.TestCase;
import org.junit.Test;

public class GumTest extends TestCase {
    @Test
    public void testMessage() {
        Item item = new Gum(1,"Gum", "RandomCandy", 5);

        assertEquals("Chew Chew, Yum!", item.getMessage());
    }
}