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
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LogTest extends TestCase {

    @Test
    public void testLog() throws FileNotFoundException {
        Log log = new Log();
        Customer customer = new Customer();
        Item candy = new Candy(2.0, "Candy", "Random Candy", 5);
        Map.Entry<String, Item> choice = new AbstractMap.SimpleEntry<>("A1", candy);

        Transaction purchase = new PurchaseTransaction(LocalDateTime.now(), 5, choice);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy KK:mm:ss a");

        List<String> expect = new ArrayList<>();
        Collections.addAll(expect, "Random Candy ", "A1 ", "2.0 ", "3.0");

        List<String> result = new ArrayList<>();
        Scanner scanner = new Scanner(new File("log.txt"));

        while(scanner.hasNextLine()){
            result.add(scanner.nextLine());
        }

        assertEquals(expect, result);

    }
}
