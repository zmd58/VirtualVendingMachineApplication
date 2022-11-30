package com.techelevator.view;

import com.techelevator.customexception.InvalidItemException;
import com.techelevator.item.Item;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.List;
import java.util.Scanner;

public class Menu {

	private PrintWriter out;
	private Scanner in;
	private NumberFormat currency = NumberFormat.getCurrencyInstance();

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println("(" + optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}
//-------------------------CUSTOM MENU----------------------------------------------------------------------------
	public Item getChoiceFromOptionsCustom(List<Item> options) {
		Item choice = null;
		while (choice == null) {
			displayMenuOptionsCustom(options);
			choice = getChoiceFromUserInputCustom(options);
		}
		return choice;
	}

	private Item getChoiceFromUserInputCustom(List<Item> options) {
		Item choice = null;
		try {
			String userInput = in.nextLine();
			for (Item item : options) {
				if (!item.getSlotLocation().equalsIgnoreCase(userInput)) {
					continue;
				} else if (item.getSlotLocation().equalsIgnoreCase(userInput)){
					choice = item;
					break;
				} else {
					throw new InvalidItemException();
				}
			}
			if (choice == null) {
				out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
			}
		} catch (InvalidItemException e){
			System.out.println("Invalid item slot");
		}
		return choice;
	}

	private void displayMenuOptionsCustom(List<Item> options) {
		out.println();
		for (Item item : options) {
			out.println("(" + item.getSlotLocation() + ") " + item.getProductName() + " | " + currency.format(item.getPrice()) + " | Qt. " + item.getQuantity());
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}

}
