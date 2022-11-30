package com.techelevator.customexception;

public class NotEnoughMoneyException extends Exception {
    public NotEnoughMoneyException(String message) {
        super(message);
    }

    public NotEnoughMoneyException() {
    }
}
