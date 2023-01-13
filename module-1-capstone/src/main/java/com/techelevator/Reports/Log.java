package com.techelevator.Reports;

import com.techelevator.Transaction.Transaction;

import java.io.*;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;

/*
    After each feedMoney, makePurchase, and finishPurchase
    logTransaction needs to be called to log the transaction that happened
 */
public class Log {
    private File log = new File("log.txt");
    private NumberFormat currency = NumberFormat.getCurrencyInstance();
    private PrintWriter out;


    // method
    public void logTransaction(Transaction transaction) {
        try {
            // test if printer was created, if not create a new printwriter
            if (out == null) {
                out = new PrintWriter(new FileOutputStream(log, true));
            }
            // date formatter to display in 12h format
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy KK:mm:ss a");

            if(transaction.getTransactionType().equalsIgnoreCase("purchase")){
                // log purchase transaction
                out.printf("%-25s %-20s %-10s %-10s %s \n",
                        transaction.getDateAndTime().format(formatter),
                        transaction.getItem().getValue().getProductName(),
                        transaction.getItem().getKey(),
                        currency.format(transaction.getItem().getValue().getPrice()),
                        currency.format(transaction.getBalance()));
            }
            else if (transaction.getTransactionType().equalsIgnoreCase("feed")) {
                // log feed transaction
                out.printf("%-25s %-20s %-10s %-10s %s \n",
                        transaction.getDateAndTime().format(formatter),
                        "FEED MONEY:",
                        "",
                        currency.format(transaction.getTransactionAmount()),
                        currency.format(transaction.getBalance()));
            } else {
                // log finish transaction
                out.printf("%-25s %-20s %-10s %-10s %s \n",
                        transaction.getDateAndTime().format(formatter),
                        "GIVE CHANGE:",
                        "",
                        currency.format(transaction.getTransactionAmount()),
                        currency.format(transaction.getBalance()));
            }
            // flush/clear previous message from printwriter
            out.flush();
        }
        catch (IOException e){
            System.out.println("An error occurred during logging");
        }
    }
}
