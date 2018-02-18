package entity;

import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;


public class MyList<K>  extends CopyOnWriteArrayList<K> {

    private long sizeAll;

    public MyList() {
    }

    public MyList(MyList<CommonOrder> commonOrders1) {
        super((Collection<? extends K>) commonOrders1);
    }

    @Override
    public boolean add(K k) {
        if (size() > 0) {
            sizeAll = (sizeAll + k.hashCode()) / size();
        }
        return super.add(k);
    }

    public long getSizeAll() {
        return sizeAll;
    }

    public void setSizeAll(long sizeAll) {
        this.sizeAll = sizeAll;
    }
}
