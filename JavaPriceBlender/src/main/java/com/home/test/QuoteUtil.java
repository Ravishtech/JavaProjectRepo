package com.home.test;

import java.util.Random;

/**
 * Utility Class to generte Random number in range bound
 **/
public class QuoteUtil {

    public static Random randomQuotes = new Random();

    public static int  GenerateQuoteBid( int min, int max) {
        int randomNumBid = randomQuotes.nextInt((max - min) + 1) + min;
        return  randomNumBid;
    }

    public static int  GenerateQuoteAsk( int min, int max) {
        int randomNumAsk = randomQuotes.nextInt((max - min) + 1) + min;
        return randomNumAsk;
    }

}
