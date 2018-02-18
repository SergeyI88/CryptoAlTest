package exchanger.impl;

import entity.CommonOrder;
import entity.MyList;
import exchanger.Exchanger;
import gdax.Gdax;
import gdax.Trade;
import handlers.impl.HandlerResponse;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GdaxThread extends Thread implements Exchanger {
    HandlerResponse handlerResponse;
    List<String> pairs;
    List<String> pairsCommon;


    public GdaxThread(HandlerResponse handlerResponse, List<String> pairs) {
        this.handlerResponse = handlerResponse;
        List<String> pairsTemp = new ArrayList<>();
        this.pairs = pairs;
        for (String s : pairs) {
            StringBuilder stringBuilder = new StringBuilder(s);
            stringBuilder.setCharAt(3, '_');
            pairsTemp.add(stringBuilder.toString());
        }
        this.pairsCommon = pairsTemp;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (int i = 0; i < pairs.size(); i++) {
                    Response<MyList<Trade>> response = Gdax.get().getData(pairs.get(i)).execute();
                    handlerResponse.execute(convertToCommon(response.body(), pairsCommon.get(i)), pairsCommon.get(i));
                }
            }
        } catch (IOException e) {
        }
    }

    @Override
    public List<CommonOrder> getOrders(List<?> orders) {
        return null;
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
