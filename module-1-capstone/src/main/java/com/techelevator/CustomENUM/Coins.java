package com.techelevator.CustomENUM;

/*
    Customized ENUM to represent change in coin along with its value
    Quarter - 25 cents
    Dime - 10 cents
    Nickle - 5 cents
    Penny - 1 cents
 */
public enum Coins {
    PENNY(1),
    NICKEL(5),
    DIME(10),
    QUARTER(25);

    public int value;

    // constructor
    Coins(int value) {
        this.value = value;
    }
}
