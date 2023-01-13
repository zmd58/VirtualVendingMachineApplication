package com.techelevator.Features;

import com.techelevator.Inventory.Item;
import com.techelevator.VendingMachine.VendingMachine;

import java.util.Map;

/*
    Each time a customer make a purchase, updateItemQuantity needs to be called
    To decrease the item's quantity by 1
 */
public class UpdateQuantity {
    // method
    public void updateItemQuantity(VendingMachine vm, Map.Entry<String, Item> choice) {
        for (var item : vm.getItemBySlot().entrySet()) {
            // if user choice is equal to items map in vending machine then quantity--
            if (item.getKey().equalsIgnoreCase(choice.getKey())) {
                int previousQuantity = item.getValue().getQuantity();
                item.getValue().setQuantity(previousQuantity - 1);
            }
        }
    }
}
