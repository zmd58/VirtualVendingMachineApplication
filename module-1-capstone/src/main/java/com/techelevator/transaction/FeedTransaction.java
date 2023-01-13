package com.techelevator.Transaction;

import java.time.LocalDateTime;

/*
    This class is use when creating a Feed Transaction
 */
public class FeedTransaction extends Transaction {
//    private double balance;


    // constructor
    public FeedTransaction(LocalDateTime dateAndTime, double previousBalance, double transactionAmount) {
        super(dateAndTime, previousBalance, transactionAmount);
        super.setBalance(previousBalance + transactionAmount);
    }


    // method
    @Override
    public String getTransactionType() {
        return "FEED";
    }

}
