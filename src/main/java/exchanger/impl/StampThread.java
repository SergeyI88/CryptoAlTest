package exchanger.impl;

import api.bitstamp.Stamp;
import api.bitstamp.Trade;
import entity.CommonOrder;
import entity.MyHashMap;
import entity.MyList;
import exchanger.Exchanger;
import handlers.impl.HandlerResponse;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class StampThread extends Thread implements Exchanger {
    private HandlerResponse handlerResponse;
    private List<String> pairs;
    private List<String> pairsCommon;
    private MyHashMap<String, MyList<CommonOrder>> map;

    public StampThread(HandlerResponse handlerResponse, List<String> pairs) {
        this.handlerResponse = handlerResponse;
        List<String> pairsTemp = new ArrayList<>();
        this.pairs = pairs;
        map = new MyHashMap<>();
        for(String s : pairs) {
            String temp = new StringBuilder(s).insert(3, '_').toString();
            pairsTemp.add(temp);
            map.put(temp, new MyList<>());
        }
        this.pairsCommon = pairsTemp;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (int i = 0; i < pairs.size(); i++) {
                    Response<MyList<Trade>> response = Stamp.get().getData(pairs.get(i)).execute();
                    MyList<CommonOrder> temp = convertToCommon(response.body(), pairsCommon.get(i));
                    if (checkOrders(temp, pairsCommon.get(i))) {
                       map.put(pairsCommon.get(i), temp);
                       handlerResponse.execute(temp, pairsCommon.get(i));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkOrders(MyList<?> newOrders, String pair) {
        long i = newOrders.getSizeAll();
        long j = map.get(pair).getSizeAll();
        System.out.println(i + "  новое" + j + " старое"  + currentThread().getName());
        return newOrders.getSizeAll() > map.get(pair).getSizeAll();
    }


    @Override
    public MyList<CommonOrder> convertToCommon(MyList<?> orders, String pair) {
        MyList<Trade> trades = (MyList<Trade>) orders;
        return trades.stream()
                .map((o) -> {
                   CommonOrder commonOrder = new CommonOrder();
                   commonOrder.setId(Integer.valueOf(o.getTid()));
                   commonOrder.setPrice(Double.valueOf(o.getPrice()));
                   commonOrder.setSide(o.getType());
                   commonOrder.setExecDate(new Date((Long.parseLong(o.getDate()) * 1000)).toString());
                   commonOrder.setSize(Double.valueOf(o.getAmount()));
                   commonOrder.setStockExchanger("Stamp");
                   commonOrder.setPair(pair);
                   return commonOrder;
                })
                .collect(Collectors.toCollection(MyList::new));
    }
}
