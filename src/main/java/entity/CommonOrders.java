package entity;

import java.util.List;

import java.util.HashMap;

public class CommonOrders<E> {
    private HashMap<String, List<CommonOrder>> map;

    public CommonOrders(HashMap<String, List<CommonOrder>> map) {
        this.map = map;
    }

    public HashMap<String, List<CommonOrder>> getMap() {

        return map;
    }

    public void setMap(HashMap<String, List<CommonOrder>> map) {
        this.map = map;
    }
}
