package exchanger.impl;

import api.bitFlyer.Flyer;
import api.bitFlyer.Trade;
import entity.CommonOrder;
import entity.MyHashMap;
import entity.MyList;
import exchanger.Exchanger;
import handlers.impl.HandlerResponseTOP;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class FlyerThread extends Thread implements Exchanger {
    private HandlerResponseTOP handlerResponse;
    private List<String> pairs;
    private MyHashMap<String, MyList<CommonOrder>> map;

    public FlyerThread(HandlerResponseTOP handlerResponse, List<String> pairs) {
        this.handlerResponse = handlerResponse;
        this.pairs = pairs;
        map = new MyHashMap<>();
        for (String p : pairs) {
            map.put(p, new MyList<>());
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (int i = 0; i < pairs.size(); i++) {
                    Response<MyList<Trade>> response = Flyer.get().getData(pairs.get(i)).execute();
                    MyList<CommonOrder> temp = convertToCommon(response.body(), pairs.get(i));
                    if (checkOrders(temp, pairs.get(i))) {
                        map.put(pairs.get(i), temp);
                        handlerResponse.execute(temp, pairs.get(i));
                    };
                }
            }
        } catch (IOException e) {
        }
    }

    @Override
    public boolean checkOrders(MyList<?> newOrders, String pair) {
        return newOrders.getSizeAll() != map.get(pair).getSizeAll();
    }

    public MyList<CommonOrder> convertToCommon(MyList<?> orders, String pair) {
        MyList<Trade> trades = (MyList<Trade>) orders;
        return trades.stream()
                .map((o) -> {
                    CommonOrder commonOrder = new CommonOrder();
                    commonOrder.setId(Integer.valueOf(o.getId()));
                    commonOrder.setPrice(Double.valueOf(o.getPrice()));
                    commonOrder.setSide(o.getSide());
                    commonOrder.setExecDate(o.getExecDate());
                    commonOrder.setSize(Double.valueOf(o.getSize()));
                    commonOrder.setStockExchanger("Flyer");
                    commonOrder.setPair(pair);
                    return commonOrder;
                })
                .collect(Collectors.toCollection(MyList::new));
    }
}
