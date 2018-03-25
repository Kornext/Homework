package ru.sberbank.homework.kudryavukh.generics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        CountMap<String> countMap = new StructureClass<>();
        countMap.add("1S");
        countMap.add("2S");
        countMap.add("3");
        countMap.add("3");
        countMap.add("5S");
        System.out.println("Размер CountMap = " + countMap.size());
        System.out.println("Количество элемента '3' в CountMap = " + countMap.getCount("3"));
        System.out.println("Количество удаляемого элемента '3' = " + countMap.remove("3"));
        System.out.println("Размер CountMap после удаления = " + countMap.size());
        countMap.addAll(countMap);
        System.out.println("Размер CountMap после добавления другого контейнера = " + countMap.size());
        Map mapTemp = countMap.toMap();
    }
}
