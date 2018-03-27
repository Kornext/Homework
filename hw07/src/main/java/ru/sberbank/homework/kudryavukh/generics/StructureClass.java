package ru.sberbank.homework.kudryavukh.generics;

import ru.sberbank.homework.kudryavukh.generics.CountMap;

import java.util.*;

public class StructureClass<T> implements CountMap<T> {
    private Map<T, Integer> map = new HashMap<>();
    private int keyMap = 0;


    @Override
    public void add(T type) {
        Integer n = map.get(type);
        if (n != null) {
            map.put(type, n++);
        } else {
            map.put(type, 1);
        }
    }

    @Override
    public int getCount(T type) {
        return map.getOrDefault(type, 0);
    }

    @Override
    public int remove(T type) {
        return map.remove(type);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void addAll(CountMap<T> source) {
        Set<Map.Entry<T, Integer>> entrySet = source.toMap().entrySet();
        List<T> keysSource = new ArrayList<>();
        List<Integer> valuesSource = new ArrayList<>();

        for (Map.Entry<T, Integer> pair : entrySet) {
            keysSource.add(pair.getKey());
            valuesSource.add(pair.getValue());
        }

        for (int i = 0; i < keysSource.size(); i++) {
            T key = keysSource.get(i);
            Integer value = map.get(key);
            if (value != null) {
                map.put(key, value + valuesSource.get(i));
            } else {
                map.put(key, valuesSource.get(i));
            }
        }
    }

    @Override
    public Map<T, Integer> toMap() {
        Map<T, Integer> resultMap = new HashMap<>();
        toMap(resultMap);
        return resultMap;
    }

    @Override
    public void toMap(Map<T, Integer> destination) {

        destination.putAll(map);
    }
}
