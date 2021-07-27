package com.home.test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *Market data Source Interface
 **/
public interface IMarketDataSource  extends Runnable{

    void RegisterTickersForQuote(List<String> tickersName);
    Quote GetTickerQuote(String ticker);
    Map<String, Quote> GetQuotes();
    String GetMarketDataSourceName();
}
