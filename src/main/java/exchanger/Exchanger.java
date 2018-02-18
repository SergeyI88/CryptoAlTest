package exchanger;

import entity.CommonOrder;
import entity.MyList;

import java.util.List;

public interface Exchanger {
    List<CommonOrder> getOrders(List<?> orders);


    MyList<CommonOrder> convertToCommon(MyList<?> orders, String pair);
}
