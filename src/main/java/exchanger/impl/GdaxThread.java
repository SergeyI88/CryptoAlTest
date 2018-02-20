package exchanger.impl;

import entity.CommonOrder;
import entity.MyHashMap;
import entity.MyList;
import exchanger.Exchanger;
import api.gdax.Gdax;
import api.gdax.Trade;
import handlers.impl.HandlerResponseTOP;
import retrofit2.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GdaxThread extends Thread implements Exchanger {
    private HandlerResponseTOP handlerResponse;
    private List<String> pairs;
    private List<String> pairsCommon;
    private MyHashMap<String, MyList<CommonOrder>> map;


    public GdaxThread(HandlerResponseTOP handlerResponse, List<String> pairs) {
        this.handlerResponse = handlerResponse;
        List<String> pairsTemp = new ArrayList<>();
        this.pairs = pairs;
        map = new MyHashMap<>();
        for (String s : pairs) {
            StringBuilder stringBuilder = new StringBuilder(s);
            stringBuilder.setCharAt(3, '_');
            pairsTemp.add(stringBuilder.toString());
            map.put(stringBuilder.toString(), new MyList<>());
        }
        this.pairsCommon = pairsTemp;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (int i = 0; i < pairs.size(); i++) {
                    Response<MyList<Trade>> response = Gdax.get().getData(pairs.get(i)).execute();
                    MyList<CommonOrder> temp = convertToCommon(response.body(), pairsCommon.get(i));
                    if (checkOrders(temp, pairsCommon.get(i))) {
                        map.put(pairsCommon.get(i), temp);
                        handlerResponse.execute(temp, pairsCommon.get(i));
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

    @Override
    public MyList<CommonOrder> convertToCommon(MyList<?> orders, String pair) {
        MyList<Trade> trades = (MyList<Trade>) orders;
        return trades.stream()
                .map((o) -> {
                    CommonOrder commonOrder = new CommonOrder();
                    commonOrder.setId(Integer.valueOf(o.getTradeId()));
                    commonOrder.setPrice(Double.valueOf(o.getPrice()));
                    commonOrder.setSide(o.getSide());
                    commonOrder.setExecDate(o.getTime());
                    commonOrder.setSize(Double.valueOf(o.getSize()));
                    commonOrder.setStockExchanger("Gdax");
                    commonOrder.setPair(pair);
                    return commonOrder;
                })
                .map(CommonOrder.class::cast)
                .collect(Collectors.toCollection(MyList::new));
    }
}
