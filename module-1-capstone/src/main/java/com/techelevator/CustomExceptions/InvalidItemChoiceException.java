package com.techelevator.CustomExceptions;

/*
    Exception to be thrown when customer picks an invalid item
 */
public class InvalidItemChoiceException extends Exception {
    // constructor
    public InvalidItemChoiceException() {
    }

    public InvalidItemChoiceException(String message) {
        super(message);
    }
}
