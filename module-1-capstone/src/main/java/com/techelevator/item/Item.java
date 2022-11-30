package com.techelevator.item;

import java.math.BigDecimal;

public abstract class Item {
    private String slotLocation;
    private String productName;
    private BigDecimal price;
    private String itemType;
    private int quantity;

    public Item(String slotLocation, String productName, BigDecimal price, String itemType, int quantity) {
        this.slotLocation = slotLocation;
        this.productName = productName;
        this.price = price;
        this.itemType = itemType;
        this.quantity = quantity;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String getSlotLocation() {
        return slotLocation;
    }

    public void setSlotLocation(String slotLocation) {
        this.slotLocation = slotLocation;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public abstract void message();
}
