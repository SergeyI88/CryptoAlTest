package handlers;

import entity.CommonOrder;
import entity.MyList;

import java.util.List;

public interface HandlerResponseI {
    void init();

    void showTop();

    void execute(MyList<CommonOrder> commonOrders, String pair);

//    MyList<CommonOrder> merge(MyList<CommonOrder> commonOrders
//            , MyList<CommonOrder> commonOrders1);

    MyList<CommonOrder> merge(MyList<CommonOrder> newOrders
            , MyList<CommonOrder> oldOrders, String pair);
}
