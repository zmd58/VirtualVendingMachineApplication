package com.techelevator.item;

import java.math.BigDecimal;

public class Drink extends Item {
    public Drink(String slotLocation, String productName, BigDecimal price, String itemType, int quantity) {
        super(slotLocation, productName, price, itemType, quantity);
    }

    @Override
    public void message() {
        System.out.println("Glug Glug, Yum!");
    }
}
