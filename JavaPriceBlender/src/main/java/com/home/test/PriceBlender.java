package com.home.test;

public interface PriceBlender {

    double  getBestBid();

    double getBestAsk();

    double getBestMid();

    void UpdatePrice(double bid, double ask, MarketSource source);

}
