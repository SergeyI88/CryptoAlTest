

import entity.MyHashMap;
import exchanger.impl.FlyerThread;
import exchanger.impl.GdaxThread;
import handlers.impl.HandlerResponse;
import exchanger.impl.StampThread;

import java.util.HashMap;
import java.util.List;

import entity.CommonOrders;
import exchanger.Exchanger;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void start() throws InterruptedException {
        List<String> pairsCommon = new ArrayList<>();
        pairsCommon.add("btc_usd");
        pairsCommon.add("eth_usd");
        pairsCommon.add("eth_btc");

        List<String> pairs1 = new ArrayList<>();
        pairs1.add("btc_usd");
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
                new CommonOrders(new MyHashMap<>(pairsCommon))
                , new CommonOrders(new MyHashMap<>(pairsCommon))
                , pairsCommon);
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
