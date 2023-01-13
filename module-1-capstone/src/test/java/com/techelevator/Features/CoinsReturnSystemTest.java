package com.techelevator.Features;

import com.techelevator.CustomENUM.Coins;
import com.techelevator.Features.CurrencyReturnSystem;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CoinsReturnSystemTest extends TestCase {

    @Test
    public void testMoneyReturn() {
        double money = 3.18;
        CurrencyReturnSystem returnMoney = new CurrencyReturnSystem();
        Map<Coins, Integer> expect = new HashMap<>();
        expect.put(Coins.QUARTER, 12);
        expect.put(Coins.DIME, 1);
        expect.put(Coins.NICKEL, 1);
        expect.put(Coins.PENNY, 3);
        assertEquals(expect, returnMoney.moneyReturn(money));
    }
}