package com.techelevator.Inventory;

/*
    This class is an abstract class which is the parent class and a blueprint for
    Beverages, Candy, Chip, and Gum class to follow
 */
public abstract class Item {
    private double price;
    private String itemType;
    private String productName;
    private int quantity;


    // constructor
    public Item(double price, String itemType, String productName, int quantity) {
        this.price = price;
        this.itemType = itemType;
        this.productName = productName;
        this.quantity = quantity;
    }


    // method
    // this method is to display a specific message when dispersing an item
    public abstract String getMessage();


    // getter & setter
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
