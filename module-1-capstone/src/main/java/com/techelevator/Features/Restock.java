package com.techelevator.Features;

import com.techelevator.VendingMachine.VendingMachine;
import com.techelevator.Inventory.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
    Completely restock all items when called
    Assign quantity to the max of 5 per item
 */
public class Restock {
    // method
    public void restockAllItems(VendingMachine vm, File input) {
        // treemap to keep items sorted
        Map<String, Item> items = new TreeMap<>();
        int maxQuantity = 5;
        // import all items from text file
        try (Scanner inputFile = new Scanner(input)) {
            while (inputFile.hasNextLine()) {
                String line = inputFile.nextLine();
                String[] part = line.split("\\|");
                String slotID = part[0];
                String productName = part[1];
                double price = Double.parseDouble(part[2]);
                String productType = part[3];
                Item item;

                // create items based on its type
                switch (productType.toLowerCase()){
                    case "candy":
                        item = new Candy(price,productType,productName,maxQuantity);
                        items.put(slotID, item);
                        break;
                    case "chip":
                        item = new Chip(price, productType, productName, maxQuantity);
                        items.put(slotID, item);
                        break;
                    case "gum":
                        item = new Gum(price, productType, productName, maxQuantity);
                        items.put(slotID, item);
                        break;
                    case "drink":
                        item = new Beverages(price, productType, productName, maxQuantity);
                        items.put(slotID, item);
                        break;
                    default:
                        break;
                }
                // add items map to vending machine object
                vm.setItemBySlot(items);
            }
        } catch (Exception e){
            System.out.println("An error was found during stock initialization");
        }
    }


}
