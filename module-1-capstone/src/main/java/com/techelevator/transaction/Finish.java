package com.techelevator.transaction;

import com.techelevator.item.Item;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Finish extends Transaction {
    public Finish(LocalDateTime date, BigDecimal preBalance, BigDecimal transactionAmount, Item item) {
        super(date, preBalance, transactionAmount, item);
    }

    @Override
    public String transactionType() {
        return "GIVE CHANGE";
    }

}
