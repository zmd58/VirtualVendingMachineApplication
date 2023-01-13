package com.techelevator.Inventory;

import junit.framework.TestCase;
import org.junit.Test;

public class ChipTest extends TestCase {

    @Test
    public void testGetMessage() {
        Item chip = new Chip(2.50, "Chip", "Lays", 5);
        assertEquals("Crunch Crunch, Yum!", chip.getMessage());
    }
}