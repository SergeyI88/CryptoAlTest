package exchanger;

import entity.CommonOrder;

import java.util.List;

public interface Exchanger {
    List<CommonOrder> getOrders(List<?> orders);

    List<CommonOrder> convertToCommon(List<?> orders, String pair);
}
