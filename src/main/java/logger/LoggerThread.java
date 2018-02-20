package logger;

import entity.CommonOrder;
import entity.MyList;
import org.apache.log4j.Logger;

public class LoggerThread extends Thread {
    private Logger logger;
    private MyList<CommonOrder> list;

    public LoggerThread(Logger logger) {
        super();
        this.logger = logger;

    }

    public LoggerThread(MyList<CommonOrder> commonOrders, Logger logger) {
        this.list = commonOrders;
        this.logger = logger;
    }

    public void init(MyList<CommonOrder> list) {
        this.list = list;
    }
    @Override
    public void run() {
        logger.info(list.toString());
    }
}
