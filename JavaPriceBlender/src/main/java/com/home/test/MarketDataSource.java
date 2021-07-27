package com.home.test;

import com.sun.org.apache.xpath.internal.operations.Quo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;


/***
 MarketData Source Class Implements  MarketDataSource Implement and Runnable
 Stores the Quotes for a ticker in ConcurrentHashMap
 Generating Quotes using Random Generator.
 * **/
public class MarketDataSource implements IMarketDataSource, Runnable {

    private ConcurrentHashMap<String, Quote>  tickerQuoteMap = new ConcurrentHashMap<String, Quote>();
    private String marketSourceName;
    private List<String> strTickerList = new ArrayList<String>();


    public  MarketDataSource(String marketSourceName) {
        this.marketSourceName = marketSourceName;
    }

    public void RegisterTickersForQuote(List<String> tickersNames) {
        this.strTickerList = tickersNames;
    }

    public Quote GetTickerQuote(String ticker) {
        return  tickerQuoteMap.get(ticker);
    }

    public Map<String, Quote> GetQuotes() {
        return tickerQuoteMap;
    }

    public String GetMarketDataSourceName() {
        return marketSourceName;
    }


    /*
        The run method of runnable will be a continuous running in real world
        makes a call to service to receive price or subscribe  and listen for price update
        For Test Simplicity It will run once for all tickers and Use Random generator
    */
    public void run() {

        int priceGapCnt = 0;
        //Simulate the thread for 5 run with time interval 10 ms but in
        for (String ticker : strTickerList) {
            int bid = QuoteUtil.GenerateQuoteBid(25 + priceGapCnt, 27 + priceGapCnt);
            int ask = QuoteUtil.GenerateQuoteAsk(26 + priceGapCnt, 28 + priceGapCnt);
            Quote tickerQuote = new Quote(ticker, bid, ask);
            tickerQuoteMap.put(ticker, tickerQuote);
            priceGapCnt = priceGapCnt + 10;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
