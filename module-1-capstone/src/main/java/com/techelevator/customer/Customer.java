package com.techelevator.Customer;

import com.techelevator.Transaction.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*
    Customer class tracks the total balance when a customer feeds money in
    Along with a list of transactions that the customer made
 */
public class Customer {
    public static final double BALANCE = 0;
    private double balance;
    private List<Transaction> transactionList = new ArrayList<>();


    // constructor
    public Customer() {
        this.balance = BALANCE;
    }
    public Customer(double balance) {
        this.balance = balance;
    }


    // method
    // this method is for updating customer's balance and adding the transaction of the
    // transaction list
    public void updateCustomerBalance(Transaction t) {
        this.balance = t.getBalance();
        this.transactionList.add(t);
    }


    // getter & setter
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

}
