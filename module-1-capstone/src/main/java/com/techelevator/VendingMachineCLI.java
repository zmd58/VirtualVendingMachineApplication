package com.techelevator;

import com.techelevator.customexception.NotEnoughMoneyException;
import com.techelevator.customexception.SoldOutException;
import com.techelevator.customer.Customer;
import com.techelevator.transaction.Feed;
import com.techelevator.transaction.Purchase;
import com.techelevator.transaction.Transaction;
import com.techelevator.item.*;
import com.techelevator.view.Menu;

import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, EXIT };
	final String PURCHASE_MENU_FEED_MONEY = "Feed Money";
	final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
	final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";
	final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_SELECT_PRODUCT, PURCHASE_MENU_FINISH_TRANSACTION };

	private Menu menu;
	private NumberFormat currency = NumberFormat.getCurrencyInstance();
	private Customer customer = new Customer();
	private List<Item> items = new ArrayList<>();
	private File in = new File("test.txt");
	private File log = new File("log.txt");

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.restock();
		cli.run();
	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				displayItem();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				displayPurchase();
			} else if (choice.equals(EXIT)) {
				break;
			}
		}
	}

	//-------------------------GENERATE ITEMS----------------------------------------------------------------------------

	private void generateItemArray(File pathIn) {
		items.clear();
		try (Scanner scanFile = new Scanner(pathIn)) {
			while (scanFile.hasNextLine()) {
				String temp = scanFile.nextLine();
				String[] line = temp.split("\\|");
				Item item = null;
				switch (line[3].toLowerCase()) {
					case "candy":
						item = new Candy(line[0], line[1], new BigDecimal(line[2]), line[3], Integer.parseInt(line[4]));
						items.add(item);
						break;
					case "chip":
						item = new Chip(line[0], line[1], new BigDecimal(line[2]), line[3], Integer.parseInt(line[4]));
						items.add(item);
						break;
					case "gum":
						item = new Gum(line[0], line[1], new BigDecimal(line[2]), line[3], Integer.parseInt(line[4]));
						items.add(item);
						break;
					case "drink":
						item = new Drink(line[0], line[1], new BigDecimal(line[2]), line[3], Integer.parseInt(line[4]));
						items.add(item);
						break;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	//-------------------------DISPLAY----------------------------------------------------------------------------

	public void displayItem() {
		for (Item item : items) {
			if (item.getQuantity() == 0) {
				System.out.println(item.getSlotLocation() + " | " + item.getProductName() + " | " + currency.format(item.getPrice()) + " | Qt. " + "SOLD OUT");
			} else {
				System.out.println(item.getSlotLocation() + " | " + item.getProductName() + " | " + currency.format(item.getPrice()) + " | Qt. " + item.getQuantity());
			}
		}
	}

	public void displayPurchase() {
		while (true) {
			System.out.println("\nCurrent Money Balance: " + currency.format(customer.getBalance()));
			String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
			if (choice.equals(PURCHASE_MENU_FEED_MONEY)) {
				feed();
			} else if (choice.equals(PURCHASE_MENU_SELECT_PRODUCT)) {
				purchase(menu);
			} else if (choice.equals(PURCHASE_MENU_FINISH_TRANSACTION)) {
				finish();
				break;
			}
		}
	}

	//-------------------------FEED, PURCHASE, FINISH----------------------------------------------------------------------------

	public void feed() {
		System.out.print("Enter Amount To Be Added: ");
		Scanner scan = new Scanner(System.in);
		BigDecimal transactionAmount = new BigDecimal(scan.nextLine());
		Transaction feed = new Feed(LocalDateTime.now(), customer.getBalance(), transactionAmount);
		customer.feedMoney(feed);
		logging(feed);
	}

	public void purchase(Menu menu) {
		try {
			generateItemArray(in);
			System.out.println("\nCurrent Money Balance: " + currency.format(customer.getBalance()));
			Item choice = menu.getChoiceFromOptionsCustom(items);
			if (choice.getQuantity() == 0) {
				throw new SoldOutException();
			}
			if (customer.getBalance().compareTo(choice.getPrice()) == -1) {
				throw new NotEnoughMoneyException();
			}
			Transaction purchase = new Purchase(LocalDateTime.now(), customer.getBalance(), choice.getPrice(), choice);
			customer.purchaseItem(purchase);
			updateStock(choice);
			generateItemArray(in);
			System.out.println("Selected: " + choice.getProductName() + " | Cost: " + currency.format(choice.getPrice()));
			System.out.println("Remaining balance: " + currency.format(customer.getBalance()));
			choice.message();
			logging(purchase);
		} catch (SoldOutException e) {
			System.out.println("Item is SOLD OUT");
		} catch (NotEnoughMoneyException e) {
			System.out.println("Not enough MONEY");
		}
	}

	public void finish() {
		int totalPenny = customer.getBalance().multiply(new BigDecimal(100)).intValue();
		int quarter = 0;
		int dime = 0;
		int nickle = 0;
		int penny = 0;
		while (!(totalPenny == 0)) {
			if (totalPenny >= 25) {
				quarter++;
				totalPenny -= 25;
			} else if (totalPenny >= 10) {
				dime++;
				totalPenny -= 10;
			} else if (totalPenny >= 5) {
				nickle++;
				totalPenny -= 5;
			} else if (totalPenny >= 1) {
				penny++;
				totalPenny -= 1;
			}
		}
		System.out.println("\nReturning: " + quarter + " Quarter" + " | " + dime + " Dime" + " | "
				+ nickle + " Nickle" + " | " + penny + " Penny");
		customer.setBalance(BigDecimal.ZERO);
	}

	//-------------------------LOG----------------------------------------------------------------------------

	public void logging(Transaction t) {
		try (
				PrintWriter out = new PrintWriter(new FileOutputStream("log.txt", true))
				){
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy KK:mm:ss a");
			if (t.transactionType().equalsIgnoreCase("purchase")) {
				out.println(t.getDate().format(formatter) + " "
						+ t.getItem().getProductName() + " " + t.getItem().getSlotLocation()
						+ " " + currency.format(t.getTransactionAmount()) + " "
						+ currency.format(t.getBalance()));
			} else {
				out.println(t.getDate().format(formatter) + " "
						+ t.transactionType() + " " + currency.format(t.getTransactionAmount()) + " "
						+  currency.format(t.getBalance()));
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	//-------------------------UPDATE STOCK----------------------------------------------------------------------------

	public void updateStock(Item item) {
		try (
				PrintWriter output = new PrintWriter(new FileOutputStream(in, false))
		){
			for (Item i : items) {
				if (i.getSlotLocation().equals(item.getSlotLocation())) {
					output.println(i.getSlotLocation() + "|" + i.getProductName() + "|" + i.getPrice() + "|" + i.getItemType() + "|" + (i.getQuantity() - 1));
				} else {
					output.println(i.getSlotLocation() + "|" + i.getProductName() + "|" + i.getPrice() + "|" + i.getItemType() + "|" + 5);
				}
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	//-------------------------RESTOCK----------------------------------------------------------------------------

	public void restock() {
		generateItemArray(in);
		try (
				PrintWriter output = new PrintWriter(new FileOutputStream(in, false))
		){
			for (Item item : items) {
				output.println(item.getSlotLocation() + "|" + item.getProductName() + "|" + item.getPrice() + "|" + item.getItemType() + "|" + 5);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
