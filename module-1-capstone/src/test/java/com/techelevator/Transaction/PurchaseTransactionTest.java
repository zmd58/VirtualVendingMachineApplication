package com.techelevator.Transaction;

import com.techelevator.Customer.Customer;
import com.techelevator.Inventory.Candy;
import com.techelevator.Inventory.Chip;
import com.techelevator.Inventory.Item;
import junit.framework.TestCase;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.Map;

public class PurchaseTransactionTest extends TestCase {
    @Test
    public void testGetBalance() {
        Item candy = new Candy(2.5, "Candy", "random candy", 5);
        Map.Entry<String, Item> choice = new AbstractMap.SimpleEntry<>("a1", candy);
        Transaction t = new PurchaseTransaction(LocalDateTime.now(),5,choice);
        assertEquals(2.5, t.getBalance());
    }
    @Test
    public void testTransactionType(){
        Customer customer = new Customer(5);
        Item aChip = new Chip(1, "Chip", "Lays", 1);
        Map.Entry<String, Item> item = new AbstractMap.SimpleEntry<>("A1", aChip);

        Transaction purchase = new PurchaseTransaction(LocalDateTime.now(), customer.getBalance(), item);
        assertEquals("PURCHASE", purchase.getTransactionType());
    }

}