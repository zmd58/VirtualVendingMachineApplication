package com.techelevator.Transaction;

import com.techelevator.Inventory.Item;

import java.time.LocalDateTime;
import java.util.Map;

/*
    This class is use when creating a Purchase Transaction
 */
public class PurchaseTransaction extends Transaction {

    private double balance;


    // constructor
    public PurchaseTransaction(LocalDateTime dateAndTime, double previousBalance, Map.Entry<String, Item> item) {
        super(dateAndTime, previousBalance, item);
        super.setBalance(previousBalance - item.getValue().getPrice());
    }


    // method
    @Override
    public String getTransactionType() {
        return "PURCHASE";
    }
}
