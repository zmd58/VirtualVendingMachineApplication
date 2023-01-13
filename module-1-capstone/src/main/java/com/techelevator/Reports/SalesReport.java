package com.techelevator.Reports;

import com.techelevator.Customer.Customer;
import com.techelevator.Transaction.Transaction;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Scanner;

/*
    getReport is called when get sale report option is select
    to generate a sale report
 */
public class SalesReport {
    private NumberFormat currency = NumberFormat.getCurrencyInstance();


    // method
    public void getReport(Customer customer, File inputFile, File reportFile) {
        double totalSale = 0;
        // import all items' names from csv file
        try (
                Scanner in = new Scanner(inputFile);
                PrintWriter out = new PrintWriter(reportFile);
        ){
            while (in.hasNextLine()) {
                String[] line = in.nextLine().split("\\|");
                int itemsSold = 0;
                String itemName = line[1];
                for (Transaction t : customer.getTransactionList()) {
                    if (t.getTransactionType().equalsIgnoreCase("purchase") && t.getItem().getKey().equalsIgnoreCase(line[0])) {
                        itemsSold += 1;
                    }
                }
                out.println(itemName + "|" + itemsSold);
                totalSale += itemsSold * Double.parseDouble(line[2]);
            }
            out.println("\n**TOTAL SALES** " + currency.format(totalSale));
        } catch (Exception e) {
            System.out.println("An error occurred when creating a sale report");
        }
    }
}
