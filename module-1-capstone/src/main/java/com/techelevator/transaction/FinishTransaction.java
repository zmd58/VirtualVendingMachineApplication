package com.techelevator.Transaction;

import java.time.LocalDateTime;

/*
    This class is use when creating a Finish Transaction
 */
public class FinishTransaction extends Transaction {
    private double balance;


    // constructor
    public FinishTransaction(LocalDateTime dateAndTime, double previousBalance, double transactionAmount) {
        super(dateAndTime, previousBalance, transactionAmount);
        super.setBalance(previousBalance - transactionAmount);
    }


    // method
    @Override
    public String getTransactionType() {
        return "FINISH";
    }
}
