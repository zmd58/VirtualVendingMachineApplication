package com.techelevator.Features;

import com.techelevator.CustomENUM.Coins;

import java.util.HashMap;
import java.util.Map;

/*
    Calculate remaining balance in coins and return a map of the result
 */
public class CurrencyReturnSystem {
    // method
    public Map<Coins, Integer> moneyReturn(double money) {
        Map<Coins, Integer> returnMoney = new HashMap<>();
        // convert dollar to cent
        int dollarToPennyConversion = 100;
        int totalMoneyInPenny = (int) (money * dollarToPennyConversion);
        int nQuarter = 0;
        int nDime = 0;
        int nNickle = 0;
        int nPenny = 0;

        while (totalMoneyInPenny != 0) {
            if (totalMoneyInPenny >= Coins.QUARTER.value) {
                // calculate # of quarter
                nQuarter = totalMoneyInPenny / Coins.QUARTER.value;
                totalMoneyInPenny -= nQuarter * Coins.QUARTER.value;
            } else if (totalMoneyInPenny >= Coins.DIME.value) {
                // calculate # of dime
                nDime = totalMoneyInPenny / Coins.DIME.value;
                totalMoneyInPenny -= nDime * Coins.DIME.value;
            } else if (totalMoneyInPenny >= Coins.NICKEL.value) {
                // calculate # of nickel
                nNickle = totalMoneyInPenny / Coins.NICKEL.value;
                totalMoneyInPenny -= nNickle * Coins.NICKEL.value;
            } else if (totalMoneyInPenny >= Coins.PENNY.value) {
                // calculate # of penny
                nPenny = totalMoneyInPenny / Coins.PENNY.value;
                totalMoneyInPenny -= nPenny * Coins.PENNY.value;
            }
            // add all # of quarter, dime, nickel, and penny to map
            returnMoney.put(Coins.QUARTER, nQuarter);
            returnMoney.put(Coins.DIME, nDime);
            returnMoney.put(Coins.NICKEL, nNickle);
            returnMoney.put(Coins.PENNY, nPenny);
        }
        return returnMoney;
    }


}
