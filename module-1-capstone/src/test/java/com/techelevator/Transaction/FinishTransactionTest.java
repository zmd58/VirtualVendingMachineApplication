package com.techelevator.Transaction;

import com.techelevator.Customer.Customer;
import junit.framework.TestCase;
import org.junit.Test;

import java.time.LocalDateTime;

public class FinishTransactionTest extends TestCase {

    @Test
    public void testTransactionType() {
        Customer customer = new Customer(5);
        Transaction finish = new FinishTransaction(LocalDateTime.now(), customer.getBalance(), customer.getBalance());
        assertEquals("FINISH", finish.getTransactionType());
    }
    @Test
    public void testGetBalance() {
        Customer customer = new Customer(0);
        Transaction finish = new FinishTransaction(LocalDateTime.now(), customer.getBalance(), customer.getBalance());
        assertEquals(0.0,finish.getBalance());
    }
}