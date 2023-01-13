package com.techelevator.Features;

import com.techelevator.Inventory.Candy;
import com.techelevator.Inventory.Item;
import com.techelevator.VendingMachine.VendingMachine;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.Map;
import java.util.TreeMap;

public class UpdateQuantityTest extends TestCase {

    @Test
    public void testUpdateItemQuantity() {
        Map<String, Item> items = new TreeMap<>();
        Item candy = new Candy(2.5, "Candy", "random candy", 5);
        items.put("a1", candy);
        VendingMachine vm = new VendingMachine(items);
        Map.Entry<String, Item> choice = new AbstractMap.SimpleEntry<>("a1", candy);
        UpdateQuantity update = new UpdateQuantity();
        update.updateItemQuantity(vm, choice);
        assertEquals(4, items.get("a1").getQuantity());
    }
}