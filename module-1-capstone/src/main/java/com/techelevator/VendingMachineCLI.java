package com.techelevator;

import com.techelevator.CustomENUM.Coins;
import com.techelevator.CustomExceptions.InsufficientFundsException;
import com.techelevator.CustomExceptions.SoldOutException;
import com.techelevator.Customer.Customer;
import com.techelevator.Features.Restock;
import com.techelevator.Features.UpdateQuantity;
import com.techelevator.Inventory.Item;
import com.techelevator.Reports.Log;
import com.techelevator.Reports.SalesReport;
import com.techelevator.Transaction.*;
import com.techelevator.view.CustomMenu;
import com.techelevator.view.Menu;
import com.techelevator.Features.CurrencyReturnSystem;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;
import com.techelevator.VendingMachine.VendingMachine;

import java.text.NumberFormat;

/*
    VendingMachineCLI is the Main class
    Contains main method that call on run() to generate a Customer and a VendingMachine
 */
public class VendingMachineCLI {
    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Make Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String MAIN_MENU_SECRET_SALES_REPORT = "Get Sale Report";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS,
            MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT, MAIN_MENU_SECRET_SALES_REPORT};
    private static final String PURCHASE_MENU_OPTION_FEED = "Feed Money";

    private static final String PURCHASE_MENU_OPTION_PURCHASE = "Select Product";

    private static final String PURCHASE_MENU_OPTION_FINISH = "Finish Transaction";

    private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_OPTION_FEED, PURCHASE_MENU_OPTION_PURCHASE, PURCHASE_MENU_OPTION_FINISH};
    private final CustomMenu customMenu = new CustomMenu(System.in, System.out);
    private final NumberFormat currency = NumberFormat.getCurrencyInstance();
    private File saleReportFile = new File("SalesReport.txt");
    private File inputFile = new File("input.txt");
    private final VendingMachine vm = new VendingMachine();
    private final Customer customer = new Customer();


    // main method
    public static void main(String[] args) {
        VendingMachineCLI cli = new VendingMachineCLI();
        cli.run();
    }

    // method
    // display the main menu
    public void run() {
        // initialize all stock when starting with max of 5 quantity for each item
        Restock initializeStock = new Restock();
        initializeStock.restockAllItems(vm, inputFile);

        System.out.printf("\n%s\n", "Welcome to our virtual vending machine!");
        // while (true) to re-loop for customer choice
        while (true) {
            // get user choice from menu
            String choice = (String) customMenu.getChoiceFromOptionsWithSaleReport(MAIN_MENU_OPTIONS);

            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                // display vending machine items
                displayItems(vm);
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                // calls on the displayPurchaseMenu() method that shows options for #2
                displayPurchaseMenu(customer);
            } else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
                System.out.println("Thank you for using our vending machine!");
                System.out.println("Have a great day.");
                break;
            } else if (choice.equals(MAIN_MENU_SECRET_SALES_REPORT)) {
                // list item and get user input, handle purchase
                SalesReport report = new SalesReport();
                report.getReport(customer, inputFile, saleReportFile);
                System.out.println("Sale report was made");
            }
        }
    }

    // display the purchase menu
    public void displayPurchaseMenu(Customer customer) {
        while (true) {
            Menu menu = new Menu(System.in, System.out);
            System.out.println("\nCurrent Money Balance: " + currency.format(customer.getBalance()));
            String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

            if (choice.equals(PURCHASE_MENU_OPTION_FEED)) {
                // display vending machine items
                // while (true) to re-loop for customer choice if it was invalid
                while (true) {
                    try {
                        System.out.print("Please Enter Money: ");
                        // obtain user input
                        Scanner userInput = new Scanner(System.in);
                        double customerAmountEntered = Double.parseDouble(userInput.nextLine());
                        // call on feedMoney method from below
                        feedMoney(customer, customerAmountEntered);
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Entry: Please Enter A Number");
                    }
                }
            } else if (choice.equals(PURCHASE_MENU_OPTION_PURCHASE)) {
                // call on makePurchase method from below
                makePurchase(customer,vm);
            } else if (choice.equals(PURCHASE_MENU_OPTION_FINISH)) {
                // call on finishPurchase from below
                finishPurchase(customer);
                break;
            }
        }
    }

    // create a feed transaction and add to the customer then log the transaction
    public void feedMoney(Customer customer, double customerAmountEntered) {
        Transaction feed = new FeedTransaction(LocalDateTime.now(), customer.getBalance(), customerAmountEntered);
        customer.updateCustomerBalance(feed);
        Log log = new Log();
        log.logTransaction(feed);
    }

    // check if balance is sufficient and quantity is available then create a transaction
    // display associated message
    // add transaction and update customer balance and log transaction
    public void makePurchase(Customer customer, VendingMachine vm) {
        //buying process
        try {
            // display items & Make Selection
            Map.Entry<String, Item> userChoice = customMenu.getChoiceFromOptions(vm.getItemBySlot());
            // returns errors if out of stock or not enough money
            if (customer.getBalance() < userChoice.getValue().getPrice()) {
                System.out.println("\nYou need " + currency.format(userChoice.getValue().getPrice() - customer.getBalance())
                        + " in order to purchase this item.");
                throw new InsufficientFundsException();
            }
            if (vm.getItemBySlot().get(userChoice.getKey()).getQuantity() == 0) {
                throw new SoldOutException();
            }
            if (customer.getBalance() >= userChoice.getValue().getPrice() && vm.getItemBySlot().get(userChoice.getKey()).getQuantity() > 0) {
                Transaction purchase = new PurchaseTransaction(LocalDateTime.now(), customer.getBalance(), userChoice);
                // update customer balance
                customer.updateCustomerBalance(purchase);
                // updates stock
                UpdateQuantity update = new UpdateQuantity();
                update.updateItemQuantity(vm, userChoice);
                // display product name, price, remaining balance, and associated message
                System.out.println("\n" + userChoice.getValue().getProductName()
                        + " | "
                        + currency.format(userChoice.getValue().getPrice()));
                System.out.println("Remaining Balance: " + currency.format(customer.getBalance()));
                System.out.println(userChoice.getValue().getMessage() + "\n");
                Log log = new Log();
                log.logTransaction(purchase);
            }
        } catch (InsufficientFundsException ife) {
            System.out.println("You do not have enough money, please insert more!");
        } catch (SoldOutException soe) {
            System.out.println("Item Sold Out! Please choose another option.");
        }
    }

    // create a transaction to update balance to 0 while dispersing with item name and cost
    // return money in change/coins with remaining balance and log transaction
    public void finishPurchase(Customer customer) {
        CurrencyReturnSystem crs = new CurrencyReturnSystem();
        Transaction finish = new FinishTransaction(LocalDateTime.now(), customer.getBalance(), customer.getBalance());
        crs.moneyReturn(customer.getBalance());
        CurrencyReturnSystem returnMoney = new CurrencyReturnSystem();
        Map<Coins, Integer> result = returnMoney.moneyReturn(customer.getBalance());
        System.out.println("Here is your change!");
        System.out.printf("Quarter %d | Dime %d | Nickel %d | Penny %d\n",
                result.get(Coins.QUARTER), result.get(Coins.DIME),
                result.get(Coins.NICKEL), result.get(Coins.PENNY));
        customer.updateCustomerBalance(finish);
        Log log = new Log();
        log.logTransaction(finish);
    }

    // call to display all items from the items map within the vending machine object
    public void displayItems(VendingMachine vm) {
        for (var item : vm.getItemBySlot().entrySet()) {
            System.out.printf("%s %s %s\n", item.getKey(), item.getValue().getProductName(),
                    currency.format(item.getValue().getPrice()));
        }
    }

}
