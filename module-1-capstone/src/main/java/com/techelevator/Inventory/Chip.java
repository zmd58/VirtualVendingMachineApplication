package com.techelevator.Inventory;

/*
    This class is use when creating CHIP items
 */
public class Chip extends Item{
    // constructor
    public Chip(double price, String itemType, String productName, int quantity) {
        super(price, itemType, productName, quantity);
    }


    // method
    @Override
    public String getMessage() {
        return "Crunch Crunch, Yum!";
    }
}
