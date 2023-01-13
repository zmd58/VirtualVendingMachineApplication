package com.techelevator.Inventory;

import junit.framework.TestCase;
import org.junit.Test;

public class CandyTest extends TestCase{

    @Test
    public void testMessage() {
        Item candy = new Candy(1.80, "Candy", "Moonpie", 5);
        assertEquals("Munch Munch, Yum!", candy.getMessage());
    }
}
