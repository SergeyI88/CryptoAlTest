package entity;

import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;

public class MyHashMap <K, V> extends HashMap<K, V> {
    private ArrayList<Long> sizeAll;

    public MyHashMap(List<String> pairsCommon) {
        sizeAll = new ArrayList<>();
    }

    @Override
    public V put(K key, V value) {
        return super.put(key, value);
    }
}
