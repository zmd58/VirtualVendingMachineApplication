package com.techelevator.VendingMachine;

import com.techelevator.Inventory.Item;

import java.util.Map;
import java.util.TreeMap;

/*
    VendingMachine class tracks the slots and items within a map
 */
public class VendingMachine {
    private Map<String, Item> itemBySlot = new TreeMap<>();


    // constructor
    public VendingMachine() {}
    public VendingMachine(Map<String, Item> itemBySlot) {
        this.itemBySlot = itemBySlot;
    }


    // getter & setter
    public Map<String, Item> getItemBySlot() {
        return itemBySlot;
    }

    public void setItemBySlot(Map<String, Item> itemBySlot) {
        this.itemBySlot = itemBySlot;
    }
}
