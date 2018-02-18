package handlers.impl;

import entity.CommonOrder;
import entity.CommonOrders;
import entity.MyList;
import handlers.HandlerResponseI;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class HandlerResponse extends Thread implements HandlerResponseI {
    CommonOrders<List<CommonOrder>> top;
    CommonOrders<MyList<CommonOrder>> all;
    List<String> pair;

    public HandlerResponse(CommonOrders top, CommonOrders all, List<String> pair) {
        this.top = top;
        this.all = all;
        this.pair = pair;
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
        all.getMap()
                .put(pair
                        , merge(commonOrders, all.getMap().get(pair)));

        top.getMap()
                .put(pair, all
                        .getMap()
                        .get(pair).stream()
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
    public synchronized MyList<CommonOrder> merge(MyList<CommonOrder> commonOrders
            , MyList<CommonOrder> commonOrders1) {
        Collection<CommonOrder> collection = new MyList<>(commonOrders1);
        for (CommonOrder c : commonOrders) {
            if (!collection.contains(c)) {
                collection.add(c);
            }
        }
        return (MyList<CommonOrder>) collection;
    }
}
