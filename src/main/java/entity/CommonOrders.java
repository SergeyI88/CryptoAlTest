package entity;



import java.util.HashMap;

public class CommonOrders<E> {

    private HashMap<String, E> map;

    public CommonOrders(HashMap<String, E> map) {
        this.map = map;
    }

    public HashMap<String, E> getMap() {
        return map;
    }


    public void setMap(HashMap<String, E> map) {
        this.map = map;

    }
}
