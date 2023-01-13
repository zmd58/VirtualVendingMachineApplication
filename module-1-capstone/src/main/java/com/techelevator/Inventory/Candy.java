package com.techelevator.Inventory;

/*
        This class is use when creating CANDY items
 */
public class Candy extends Item{
    // constructor
    public Candy(double price, String itemType, String productName, int quantity) {
        super(price, itemType, productName, quantity);
    }


    // method
    @Override
    public String getMessage() {
        return "Munch Munch, Yum!";
    }
}
