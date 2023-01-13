package com.techelevator.CustomExceptions;

/*
    Exception to be thrown when balance is insufficient when purchasing an item
 */
public class InsufficientFundsException extends Exception {
    // constructor
    public InsufficientFundsException() {
    }

    public InsufficientFundsException(String message) {
        super(message);
    }
}
