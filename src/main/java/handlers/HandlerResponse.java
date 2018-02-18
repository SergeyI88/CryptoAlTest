package handlers;

import entity.CommonOrder;
import entity.CommonOrders;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import java.util.stream.Collectors;

public class HandlerResponse extends Thread {
    CommonOrders<List<CommonOrder>> top;
    CommonOrders<List<CommonOrder>> all;
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

    private void init() {
        for (String s: pair) {
            all.getMap().put(s, new CopyOnWriteArrayList<>());
            top.getMap().put(s, new CopyOnWriteArrayList<>());
        }
    }

    private void showTop() {
        for (String s : pair) {
            if (top.getMap().get(s).size() > 0){
                top.getMap().get(s).stream().forEach((o) -> System.out.println(o.toString()));
            }
            System.out.println("=====================================================================");
        }
    }

    public void execute(List<CommonOrder> commonOrders, String pair) {
        all.getMap().put(pair,
                merge(
                        commonOrders, all.getMap().get(pair)));

        top.getMap().put(pair,  all.getMap().get(pair).stream()
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

    private synchronized List<CommonOrder> merge(List<CommonOrder> commonOrders
            , List<CommonOrder> commonOrders1) {
        Collection<CommonOrder> collection = new CopyOnWriteArrayList<>(commonOrders1);
        for (CommonOrder c : commonOrders) {
            if (!collection.contains(c)) {
                collection.add(c);
            }
        }
        return (List<CommonOrder>) collection;
    }
}
