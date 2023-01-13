package com.techelevator.Transaction;

import java.time.LocalDateTime;
import java.util.Map;
import com.techelevator.Inventory.Item;

/*
    This class is an abstract class which is the parent class and a blueprint for
    FeedTransaction, PurchaseTransaction, and FinishTransaction
 */
public abstract class Transaction {
    private double balance;
    private double previousBalance;
    private double transactionAmount; // holds either feed amount or item's cost
    private LocalDateTime dateAndTime;
    private Map.Entry<String, Item> item;


    // constructor
    public Transaction(LocalDateTime dateAndTime, double previousBalance, Map.Entry<String, Item> item) {
        this.dateAndTime = dateAndTime;
        this.previousBalance = previousBalance;
        this.item = item;
    }

    public Transaction(LocalDateTime dateAndTime, double previousBalance, double transactionAmount) {
        this.dateAndTime = dateAndTime;
        this.previousBalance = previousBalance;
        this.transactionAmount = transactionAmount;
    }


    // method
    // this method is to indicate what type of transaction it is
    public abstract String getTransactionType();


    // getter & setter
    public double getPreviousBalance() {
        return previousBalance;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public Map.Entry<String, Item> getItem() {
        return item;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }
}
