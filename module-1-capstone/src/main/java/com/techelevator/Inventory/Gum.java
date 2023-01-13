package com.techelevator.Inventory;

/*
    This class is use when creating GUM items
 */
public class Gum extends Item{
    // constructor
    public Gum(double price, String itemType, String productName, int quantity) {
        super(price, itemType, productName, quantity);
    }


    // method
    @Override
    public String getMessage() {
       return "Chew Chew, Yum!";
    }

}
