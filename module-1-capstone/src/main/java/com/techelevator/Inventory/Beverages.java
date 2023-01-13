package com.techelevator.Inventory;

/*
    This class is use when creating DRINK items
 */
public class Beverages extends Item{
    // constructor
    public Beverages(double price, String itemType, String productName, int quantity) {
        super(price, itemType, productName, quantity);
    }


    // method
    @Override
    public String getMessage() {
        return "Glug Glug, Yum!";
    }
}
