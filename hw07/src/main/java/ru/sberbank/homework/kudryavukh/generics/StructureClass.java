package ru.sberbank.homework.kudryavukh.generics;

import ru.sberbank.homework.kudryavukh.generics.CountMap;

import java.util.*;

public class StructureClass<T> implements CountMap<T> {
    private Map<Integer, T> map = new HashMap<>();
    private int keyMap = 0;


    @Override
    public void add(T type) {
        map.put(keyMap, type);
        keyMap++;
    }

    @Override
    public int getCount(T type) {
        int count = 0;
        for (Map.Entry<Integer, T> e : map.entrySet()) {
            T value = e.getValue();

            if (value.equals(type)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int remove(T type) {
        int countRemoveValue = getCount(type);
        if (countRemoveValue == 0) {
            return countRemoveValue;
        }
        Set<Map.Entry<Integer, T>> entrySet = map.entrySet();
        List<Integer> keyValue = new ArrayList<>();
        for (Map.Entry<Integer, T> pair : entrySet) {
            if (type.equals(pair.getValue())) {
                keyValue.add(pair.getKey());
            }
        }
        for (int key : keyValue) {
            map.remove(key);
        }
        return countRemoveValue;
    }

    @Override
    public int size() {
        return map.size();
    }

    /**
     * Метод собирает в два листа ключи и значения добавляемой мапы, после чего
     * ищет совпадение по ключу в текущей мапе. Если находит - умножает ключ на 2
     * и опять сверяется на возможность совпадения. Если ключ 0 - преобразует его
     * в еденицу.
     *
     * @param source
     */
    @Override
    public void addAll(CountMap<T> source) {
        Set<Map.Entry<Integer, T>> entrySet = map.entrySet();
        List<Integer> keysSource = new ArrayList<>();
        List<T> valuesSource = new ArrayList<>();

        for (Map.Entry<Integer, T> pair : entrySet) {
            keysSource.add(pair.getKey());
            valuesSource.add(pair.getValue());
        }
        for (int i = 0; i < keysSource.size(); i++) {
            int key = keysSource.get(i);
            T value = valuesSource.get(i);
            if (map.get(key) != null && key == 0) {
                key++;
            }
            while (map.get(key) != null) {
                key += key;
            }
            map.put(key, value);
        }
        //map.putAll((Map<? extends Integer, ? extends T>) source);
    }

    @Override
    public Map toMap() {
        Map<T, Integer> resultMap = new HashMap<>();

        for (Map.Entry<Integer, T> e : map.entrySet()) {
            T value = e.getValue();
            if (!resultMap.containsValue(value)) {
                int countValue = getCount(value);
                resultMap.put(value, countValue);
            }
        }

        return resultMap;
    }

    @Override
    public void toMap(Map destination) {
        destination = toMap();
    }
}
