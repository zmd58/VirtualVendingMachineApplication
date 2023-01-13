package com.techelevator.Features;

import com.techelevator.Features.Restock;
import com.techelevator.Inventory.*;
import com.techelevator.VendingMachine.VendingMachine;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;
import java.util.*;

public class RestockTest extends TestCase {
    @Test
    public void testRestock() {
        Map<String, Item> expected = new TreeMap<>();
        Item chip = new Chip(1.50,"Chip", "Lays", 5);
        Item candy = new Candy(1.50,"Candy", "Skittles", 5);
        Item drink = new Beverages(1.50,"Drink", "Cola", 5);
        Item gum = new Gum(1.50,"Gum", "Trident", 5);
        expected.put("A1", chip);
        expected.put("B1", candy);
        expected.put("C1", drink);
        expected.put("D1", gum);

        VendingMachine vm = new VendingMachine();
        File input = new File("restockTest.txt");
        Restock restock = new Restock();
        restock.restockAllItems(vm, input);

        for (var item : expected.entrySet()) {
            assertEquals(item.getValue().getMessage(), vm.getItemBySlot().get(item.getKey()).getMessage());
        }

    }

}