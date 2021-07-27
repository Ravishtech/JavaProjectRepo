package com.home.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Iterator;

public class PriceBlenderMain {

    public static void main(String[] args) {

        //Market data Source producer
        ExecutorService  marketDataService  = Executors.newFixedThreadPool(MarketSource.values().length);
        List<IMarketDataSource>  marketDataSources = new ArrayList<IMarketDataSource>();
        List<String> tickers = new ArrayList<String>(Arrays.asList("APPL", "AMZN", "MSFT"));  //List of Tickers

        //PriceBlender
        PriceBlender  priceBlemder ;

        //start market source services
        for (MarketSource mktSvc : MarketSource.values() ) {
            IMarketDataSource mktDatSource = new MarketDataSource(mktSvc.toString());
            //Registers
            mktDatSource.RegisterTickersForQuote(tickers);
            marketDataSources.add(mktDatSource);
            //Register tickers for the quote sto be retrieved
            mktDatSource.RegisterTickersForQuote(tickers);
            //start a  market data source thread
            marketDataService.submit(mktDatSource);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        /**
         * The below code should be run in a separate thread  like an consumer running  and waiting for the price update from MarketDataSource thread
         * or running in a loop with periodic pauses
         * For simpliciti adter market data source the code is run in main thread after market data thread started running
        ***/

        for(String ticker: tickers) {
            //Determine best bid and ask for each ticker
            priceBlemder= new PriceBlenderImpl(ticker); //Instantiate Price blender for each ticker for  various data sources
            System.out.println("Determine the best bid ask for ticker " + ticker);
            for (IMarketDataSource mrktDS : marketDataSources) {
                System.out.println("Market Data Source  Name :" + mrktDS.GetMarketDataSourceName());
                Quote quote = mrktDS.GetTickerQuote(ticker);
                //invoke Update Price belender of the ticker to determine best bid and ask;
                System.out.println("Bid Price " + quote.getBid() +  " Ask Price " + quote.getAsk());
                priceBlemder.UpdatePrice(quote.getBid(), quote.getAsk(), MarketSource.valueOf(mrktDS.GetMarketDataSourceName()));
            }
            System.out.println("For Ticker " +  ticker + "  Best Bid  " + priceBlemder.getBestBid()  + " and Best Ask " +  priceBlemder.getBestAsk()
                     + " and Mid " +  priceBlemder.getBestMid());
        }

        marketDataService.shutdown();

    }

}
