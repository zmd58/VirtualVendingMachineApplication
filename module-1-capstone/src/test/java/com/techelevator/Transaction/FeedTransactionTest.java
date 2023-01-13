package com.techelevator.Transaction;

import com.techelevator.Customer.Customer;
import junit.framework.TestCase;
import org.junit.Test;

import java.time.LocalDateTime;

public class FeedTransactionTest extends TestCase {

    @Test
    public void testGetBalance() {
        Transaction t = new FeedTransaction(LocalDateTime.now(),5, 10);
        assertEquals((double) 15,t.getBalance());
    }


    @Test
    public void testTranscationType() {
        Customer customer = new Customer(5);
        Transaction feed = new FeedTransaction(LocalDateTime.now(), customer.getBalance(), customer.getBalance());
        assertEquals("FEED", feed.getTransactionType());
    }
}