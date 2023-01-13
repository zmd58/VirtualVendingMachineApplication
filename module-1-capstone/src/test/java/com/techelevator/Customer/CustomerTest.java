package com.techelevator.Customer;

import com.techelevator.Transaction.FeedTransaction;
import com.techelevator.Transaction.Transaction;
import junit.framework.TestCase;
import org.junit.Test;

import java.time.LocalDateTime;

public class CustomerTest extends TestCase {

    @Test
    public void testUpdateCustomerBalance() {
        Customer customer = new Customer();
        Transaction t = new FeedTransaction(LocalDateTime.now(), 5, 10);
        customer.updateCustomerBalance(t);
        assertEquals((double) 15, customer.getBalance());
    }
}