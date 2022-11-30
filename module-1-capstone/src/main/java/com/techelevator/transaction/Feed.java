package com.techelevator.transaction;

import com.techelevator.item.Item;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Feed extends Transaction {
    private BigDecimal balance;
    public Feed(LocalDateTime date, BigDecimal preBalance, BigDecimal transactionAmount) {
        super(date, preBalance, transactionAmount);
        this.balance = preBalance.add(transactionAmount);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String transactionType() {
        return "FEED MONEY";
    }
}
