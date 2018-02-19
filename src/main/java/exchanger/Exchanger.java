package exchanger;

import entity.CommonOrder;
import entity.MyList;

import java.util.List;

public interface Exchanger {

    boolean checkOrders(MyList<?> newOrders, String pair);

    MyList<CommonOrder> convertToCommon(MyList<?> orders, String pair);
}
