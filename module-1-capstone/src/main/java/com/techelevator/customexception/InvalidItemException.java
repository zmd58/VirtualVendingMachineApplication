package com.techelevator.customexception;

public class InvalidItemException extends Exception {
    public InvalidItemException() {
    }

    public InvalidItemException(String message) {
        super(message);
    }
}
