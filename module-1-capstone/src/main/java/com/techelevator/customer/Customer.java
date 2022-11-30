package com.techelevator.customer;

import com.techelevator.transaction.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private BigDecimal balance;
    public static final BigDecimal BALANCE = BigDecimal.ZERO;
    private List<Transaction> transactionList = new ArrayList<>();

    public Customer() {
        balance = BALANCE;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void feedMoney(Transaction t) {
        balance = balance.add(t.getTransactionAmount());
        transactionList.add(t);
    }

    public void purchaseItem(Transaction t) {
        balance = balance.subtract(t.getTransactionAmount());
        transactionList.add(t);
    }
}
