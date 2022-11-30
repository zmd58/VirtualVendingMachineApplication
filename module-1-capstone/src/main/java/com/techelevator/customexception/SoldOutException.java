package com.techelevator.customexception;

public class SoldOutException extends Exception {
    public SoldOutException(String message) {
        super(message);
    }

    public SoldOutException() {
    }
}
