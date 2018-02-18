

import Threads.FlyerThread;
import Threads.GdaxThread;
import handlers.HandlerResponse;
import Threads.StampThread;

import java.util.HashMap;
import java.util.List;

import entity.CommonOrders;
import exchanger.Exchanger;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void start() throws InterruptedException {
        List<String> pairs1 = new ArrayList<>();
        pairs1.add("btc_usd");
        pairs1.add("eth_usd");
        pairs1.add("eth_btc");
        pairs1.add("LTC_BTC");

        List<String> pairs2 = new ArrayList<>();
        pairs2.add("btcusd");
        pairs2.add("ethusd");
        pairs2.add("ethbtc");

        List<String> pairs3 = new ArrayList<>();
        pairs3.add("btc-usd");
        pairs3.add("eth-usd");
        pairs3.add("eth-btc");
        pairs3.add("LTC-BTC");

        HandlerResponse handlerResponse = new HandlerResponse(
                new CommonOrders(new HashMap<>())
                , new CommonOrders(new HashMap<>())
                , pairs1);
        Exchanger flyerThread = new FlyerThread(handlerResponse, pairs1);
        Exchanger stampThread = new StampThread(handlerResponse, pairs2);
        Exchanger gdax = new GdaxThread(handlerResponse, pairs3);
        handlerResponse.start();
        ((FlyerThread)flyerThread).start();
        ((StampThread)stampThread).start();
        ((GdaxThread)gdax).start();
        handlerResponse.join();
    }
}
