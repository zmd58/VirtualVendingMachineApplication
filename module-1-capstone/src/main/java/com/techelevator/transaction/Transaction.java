package com.techelevator.transaction;

import com.techelevator.item.Item;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class Transaction {
    private LocalDateTime date;
    private BigDecimal preBalance;
    private BigDecimal balance;
    private BigDecimal transactionAmount;
    private Item item;

    public Transaction(LocalDateTime date, BigDecimal preBalance, BigDecimal transactionAmount, Item item) {
        this.date = date;
        this.preBalance = preBalance;
        this.balance = preBalance.subtract(transactionAmount);
        this.transactionAmount = transactionAmount;
        this.item = item;
    }

    public Transaction(LocalDateTime date, BigDecimal preBalance, BigDecimal transactionAmount) {
        this.date = date;
        this.preBalance = preBalance;
        this.balance = preBalance.add(transactionAmount);
        this.transactionAmount = transactionAmount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public BigDecimal getPreBalance() {
        return preBalance;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public Item getItem() {
        return item;
    }

    public abstract String transactionType();

}
