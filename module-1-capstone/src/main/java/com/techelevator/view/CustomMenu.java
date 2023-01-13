package com.techelevator.view;

import com.techelevator.CustomExceptions.InvalidItemChoiceException;
import com.techelevator.Inventory.Item;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Map;
import java.util.Scanner;

/*
    The first custom menu to display options to select using slot location instead of numerical
    getChoiceFromOptions return a single map entry of the item instead of an object
    CustomerMenu is in use to display purchase menu
    The second custom menu is to display a hidden sale report option at the end
 */
public class CustomMenu {
    private PrintWriter out;
    private Scanner in;
    private NumberFormat currency = NumberFormat.getCurrencyInstance();


    // constructor
    public CustomMenu(InputStream input, OutputStream output) {
        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
    }


    // method
    public Map.Entry<String, Item> getChoiceFromOptions(Map<String, Item> options) {
        Map.Entry<String, Item> choice = null;
        while (choice == null) {
            displayMenuOptions(options);
            choice = getChoiceFromUserInput(options);
        }
        return choice;
    }

    private Map.Entry<String, Item> getChoiceFromUserInput(Map<String, Item> options) {
        Map.Entry<String, Item> choice = null;
        String userInput = in.nextLine();
        try {
            for (var item : options.entrySet()) {
                if (item.getKey().equalsIgnoreCase(userInput)) {
                    choice = item;
                }
            }
        } catch (NumberFormatException e) {
            // eat the exception, an error message will be displayed below since choice will be null
        }
        if (choice == null) {
            out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
        }
        return choice;
    }

    private void displayMenuOptions(Map<String, Item> options) {
        out.println();
        for (var item : options.entrySet()) {
            out.println("(" + item.getKey() + ") " + item.getValue().getProductName()
                    + " " + currency.format(item.getValue().getPrice()));
        }
        out.print(System.lineSeparator() + "Please choose an option >>> ");
        out.flush();
    }


    //-----------------Sale Report Menu--------------------------------------------------------------------
    public Object getChoiceFromOptionsWithSaleReport(Object[] options) {
        Object choice = null;
        while (choice == null) {
            displayMenuOptionsWithSaleReport(options);
            choice = getChoiceFromUserInputWithSaleReport(options);
        }
        return choice;
    }

    private Object getChoiceFromUserInputWithSaleReport(Object[] options) {
        Object choice = null;
        String userInput = in.nextLine();
        try {
            int selectedOption = Integer.valueOf(userInput);
            if (selectedOption > 0 && selectedOption <= options.length) {
                choice = options[selectedOption - 1];
            }
        } catch (NumberFormatException e) {
            // eat the exception, an error message will be displayed below since choice will be null
            System.out.println("Invalid choice");
        }
        if (choice == null) {
            out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
        }
        return choice;
    }

    private void displayMenuOptionsWithSaleReport(Object[] options) {
        out.println();
        for (int i = 0; i < options.length - 1; i++) {
            int optionNum = i + 1;
            out.println(optionNum + ") " + options[i]);
        }
        out.print(System.lineSeparator() + "Please choose an option >>> ");
        out.flush();
    }

}
