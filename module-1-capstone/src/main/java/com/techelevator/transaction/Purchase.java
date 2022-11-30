package com.techelevator.transaction;

import com.techelevator.item.Item;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Purchase extends Transaction {
    private BigDecimal balance;
    public Purchase(LocalDateTime date, BigDecimal preBalance, BigDecimal transactionAmount, Item item) {
        super(date, preBalance, transactionAmount, item);
        this.balance = preBalance.subtract(transactionAmount);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String transactionType() {
        return "PURCHASE";
    }
}
