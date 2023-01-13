package com.techelevator.Reports;

import com.techelevator.Customer.Customer;
import com.techelevator.Inventory.Candy;
import com.techelevator.Inventory.Item;
import com.techelevator.Transaction.PurchaseTransaction;
import com.techelevator.Transaction.Transaction;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.*;

public class SalesReportTest extends TestCase {
    @Test
    public void testReport() throws FileNotFoundException {
        Customer customer = new Customer();
        Item candy = new Candy(2.0, "Candy", "Random Candy", 5);
        Map.Entry<String, Item> choice = new AbstractMap.SimpleEntry<>("A1", candy);
        Transaction purchase = new PurchaseTransaction(LocalDateTime.now(), 5, choice);
        customer.updateCustomerBalance(purchase);
        SalesReport saleReport = new SalesReport();
        saleReport.getReport(customer, new File("inputFileTest.txt"),
                new File("salereporttest.txt"));

        List<String> expect = new ArrayList<>();
        Collections.addAll(expect, "Random Candy|1", "", "**TOTAL SALES** $2.00");
        List<String> result = new ArrayList<>();
        Scanner in = new Scanner(new File("salereporttest.txt"));
        while (in.hasNextLine()) {
            result.add(in.nextLine());
        }

        assertEquals(expect, result);
    }
}