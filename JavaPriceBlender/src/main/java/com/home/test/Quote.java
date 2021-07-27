package com.home.test;

import java.math.BigDecimal;

/**
 * Quote Class meber being ticker and Bid, ask and Mid;
 * **/

public class Quote {

    private String stkTicker;
    private int bid;
    private int ask;
    private int mid;

    public Quote(String stkTicker, int bid, int ask) {
        this.stkTicker = stkTicker;
        this.bid = bid;
        this.ask = ask;
    }

    public String getStkTicker() {
        return stkTicker;
    }

    public void setStkTicker(String stkTicker) {
        this.stkTicker = stkTicker;
    }

    public int getBid() {
        return bid;
    }

    public int getAsk() {
        return ask;
    }

    public int getMid() {
         mid = (bid + ask)/2;
         return  mid;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "stkTicker='" + stkTicker + '\'' +
                ", bid=" + bid +
                ", ask=" + ask +
                ", mid=" + mid +
                '}';
    }


}
