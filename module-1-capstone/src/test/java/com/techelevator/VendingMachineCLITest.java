package com.techelevator;

import com.techelevator.Customer.Customer;
import com.techelevator.Features.CurrencyReturnSystem;
import com.techelevator.Features.UpdateQuantity;
import com.techelevator.Inventory.Candy;
import com.techelevator.Inventory.Item;
import com.techelevator.Transaction.FeedTransaction;
import com.techelevator.Transaction.FinishTransaction;
import com.techelevator.Transaction.PurchaseTransaction;
import com.techelevator.Transaction.Transaction;
import com.techelevator.VendingMachine.VendingMachine;
import junit.framework.TestCase;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.*;

public class VendingMachineCLITest extends TestCase {
    @Test
    public void testFeed() {
        Customer customer = new Customer();
        Transaction t = new FeedTransaction(LocalDateTime.now(), 5, 10);
        customer.updateCustomerBalance(t);
        List<Transaction> expect = new ArrayList<>();
        expect.add(t);
        assertEquals(expect,customer.getTransactionList());
    }

    @Test
    public void testPurchase() {
        VendingMachine vm = new VendingMachine();
        Customer customer = new Customer();

        //Creating new candy item
        Item candy = new Candy(2.0, "Candy", "Jolly Rancher", 1);
        //Creating selection
        Map.Entry<String, Item> userChoice = new AbstractMap.SimpleEntry<>("A1", candy);
        //Placed item in Vending Machine for customer
        Map<String, Item> item = new TreeMap<>();
        item.put(userChoice.getKey(), userChoice.getValue());
        vm.setItemBySlot(item);

        Transaction purchase = new PurchaseTransaction(LocalDateTime.now(), 5.0, userChoice);
        customer.updateCustomerBalance(purchase);

        UpdateQuantity updated = new UpdateQuantity();
        updated.updateItemQuantity(vm, userChoice);

        assertEquals(3.0, customer.getBalance());
        assertEquals(0, userChoice.getValue().getQuantity());

    }

    @Test
    public void testFinish() {
        CurrencyReturnSystem crs = new CurrencyReturnSystem();
        Customer customer = new Customer();
        Transaction finish = new FinishTransaction(LocalDateTime.now(), 5, 10);
        crs.moneyReturn(customer.getBalance());
        customer.updateCustomerBalance(finish);
        List<Transaction> expect = new ArrayList<>();
        expect.add(finish);
        assertEquals(expect,customer.getTransactionList());
    }
}