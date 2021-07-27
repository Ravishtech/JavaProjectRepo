package com.home.test;

import java.util.List;

public class PriceBlenderImpl  implements PriceBlender{

    double bid;
    double ask;
    double mid;


    String ticker;
    MarketSource source;
    public PriceBlenderImpl(String ticker)
    {
        this.ticker = ticker;
        bid = 0;
        ask = 0;
        mid = 0;
    }

    public double getBestBid() {
        //check crossing
        if(this.bid > this.ask  ) //check crossing
        {
            this.bid = 0;
            this.ask = 0;
            this.mid =  0;
        }
        return  bid;
    }

    public double getBestAsk() {
        //check crossing
        if(this.bid > this.ask  ) //check crossing
        {
            this.bid = 0;
            this.ask = 0;
            this.mid =  0;
        }
        return ask;
    }

    public double getBestMid() {
        if (bid == 0)  //if bid is zero form any source mid will be 0
             mid = 0;
        else {
            mid = (bid + ask) / 2;
            //check crossing
        }
        return  mid;
    }

    public void UpdatePrice(double bid, double ask, MarketSource source) {

        if(bid > ask)
        {
            return;
        }

        //Apply Price Blending rules
        if(bid == 0  && source == this.source) {
            this.mid = 0;
            this.bid = 0;
            return;
        }
        if(bid > this.bid ) {
            this.bid = bid;
            this.source = source;
        }
       if(this.ask  == 0)  //lowest ask
       {
           this.ask = ask;
       }
       else if (ask < this.ask) {
            this.ask = ask;
           this.source = source;
       }

    }
}
