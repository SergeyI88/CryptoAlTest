package Threads;

import bitFlyer.Flyer;
import bitFlyer.Trade;
import entity.CommonOrder;
import exchanger.Exchanger;
import handlers.HandlerResponse;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


public class FlyerThread extends Thread implements Exchanger {
    HandlerResponse handlerResponse;
    List<String> pairs;

    public FlyerThread(HandlerResponse handlerResponse, List<String> pairs) {
        this.handlerResponse = handlerResponse;
        this.pairs = pairs;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (String s : pairs) {
                    Response<List<Trade>> response = Flyer.get().getData(s).execute();
                    handlerResponse.execute(convertToCommon(response.body(), s), s);
                }
            }
        } catch (IOException e) {
        }
    }

    public List<CommonOrder> getOrders(List<?> orders) {
        return null;
    }

    public List<CommonOrder> convertToCommon(List<?> orders, String pair) {
        List<Trade> trades = (List<Trade>) orders;
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
                .collect(Collectors.toList());
    }
}
