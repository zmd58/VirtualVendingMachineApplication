package com.techelevator.CustomExceptions;

/*
        Exception to be thrown when customer picks an item that is SOLD OUT
 */
public class SoldOutException extends Exception {
    // constructor
    public SoldOutException() {
    }

    public SoldOutException(String message) {
        super(message);
    }
}
