package handlers.impl;

import entity.CommonOrder;
import entity.CommonOrders;
import entity.MyList;
import handlers.HandlerResponseI;
import logger.LoggerThread;
import org.apache.log4j.Logger;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class HandlerResponseTOP extends Thread implements HandlerResponseI {
    private CommonOrders<List<CommonOrder>> top;
    private CommonOrders<MyList<CommonOrder>> all;
    private List<String> pair;
    private Logger logger;

    public HandlerResponseTOP(CommonOrders top, CommonOrders all, List<String> pair) {
        this.top = top;
        this.all = all;
        this.pair = pair;
        logger = Logger.getLogger(HandlerResponseTOP.class);
    }

    @Override
    public void run() {
        init();
        while (true) {
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            showTop();
        }
    }

    @Override
    public void init() {
        for (String s : pair) {
            all.getMap().put(s, new MyList<>());
            top.getMap().put(s, new CopyOnWriteArrayList<>());
        }
    }

    @Override
    public void showTop() {
        for (String s : pair) {
            if (top.getMap().get(s).size() > 0) {
                top.getMap().get(s).stream().forEach((o) -> System.out.println(o.toString()));
            }
            System.out.println("=====================================================================");
        }
    }

    @Override
    public void execute(MyList<CommonOrder> commonOrders, String pair) {
        new LoggerThread(commonOrders, logger).start();
        all.getMap()
                .put(pair
                        , merge(commonOrders, all.getMap().get(pair), pair));

        top.getMap()
                .put(pair, all
                        .getMap()
                        .get(pair)
                        .stream()
                        .sorted((c, c1) -> {
                            if (c.getPrice() < c1.getPrice()) {
                                return 1;
                            } else if (c.getPrice() > c1.getPrice()) {
                                return -1;
                            } else {
                                return 0;
                            }
                        })
                        .limit(5)
                        .collect(Collectors.toList()));
    }

    @Override
    public synchronized MyList<CommonOrder> merge (MyList<CommonOrder> newOrders
            , MyList<CommonOrder> oldOrders, String pair) {

        List<CommonOrder> collection = new MyList<>(oldOrders);
        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getStockExchanger().equals(newOrders.get(0).getStockExchanger())) {
                collection.remove(i);
            }
        }
        for (int i = 0; i < newOrders.size(); i++) {
            if (!collection.contains(newOrders.get(i))) {
                collection.add(newOrders.get(i));
            }
        }

        MyList<CommonOrder> list = (MyList<CommonOrder>) collection;
        list.setSizeAll(((MyList<CommonOrder>) collection).getSizeAll());
        return list;
    }
}
