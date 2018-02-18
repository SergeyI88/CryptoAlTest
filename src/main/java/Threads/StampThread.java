package Threads;

import bitstamp.Stamp;
import bitstamp.Trade;
import entity.CommonOrder;
import exchanger.Exchanger;
import handlers.HandlerResponse;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class StampThread extends Thread implements Exchanger {
    HandlerResponse handlerResponse;
    List<String> pairs;
    List<String> pairsCommon;

    public StampThread(HandlerResponse handlerResponse, List<String> pairs) {
        this.handlerResponse = handlerResponse;
        List<String> pairsTemp = new ArrayList<>();
        this.pairs = pairs;

        for(String s : pairs) {
            pairsTemp.add(new StringBuilder(s).insert(3, '_').toString());
            System.out.println();
        }
        this.pairsCommon = pairsTemp;

    }

    @Override
    public void run() {
        try {
            while (true) {
                for (int i = 0; i < pairs.size(); i++) {
                    System.out.println(pairs.get(i) + " stamp");
                    Response<List<Trade>> response = Stamp.get().getData(pairs.get(i)).execute();
                    handlerResponse.execute(convertToCommon(response.body(), pairsCommon.get(i)), pairsCommon.get(i));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CommonOrder> getOrders(List<?> orders) {
        return null;
    }

    @Override
    public List<CommonOrder> convertToCommon(List<?> orders, String pair) {
        List<Trade> trades = (List<Trade>) orders;
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
                .collect(Collectors.toList());
    }
}
