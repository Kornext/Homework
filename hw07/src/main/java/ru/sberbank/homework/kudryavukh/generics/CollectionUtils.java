package ru.sberbank.homework.kudryavukh.generics;

import java.util.*;
import java.util.function.Predicate;

/**
 * Параметризовать методы, используя правило PECS, и реализовать их.
 */
public class CollectionUtils {

    //Super - ограничени снизу  , consumers / write
    //Extends - ограничение сверху, produced / read

    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {

        destination.addAll(source);
    }

    public static <T> List newArrayList() {

        return new ArrayList<T>();
    }

    public static <T> int indexOf(List<? extends T> source, T type) {
        return source.indexOf(type);
    }

    public static <T> List limit(List<? extends T> source, int size) {
        return source.subList(0 ,size);
    }

    public static <T> void add(List<? super T> source, T type) {
        source.add(type);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    public static <T> boolean containsAll(List<? super T> c1, List<? extends T> c2) {
        c1.containsAll(c2);
        return false;
    }

    //true если первый лист содержит хотя-бы 1 второго
    public static <T> boolean containsAny(List<? super T> c1, List<? extends T> c2) {
        for(T type : c2) {
            if(c1.contains(type)) {
                return true;
            }
        }
        return false;
    }

    // Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Пример range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static <T extends Comparable<T>> List<T> range (List<T> list, T min, T max) {
        //List<T> newList = list.stream().filter((x)->( (a,b)->()  ));
        List<T> newList = new LinkedList<>();
        for(T element:list) {
            if(element.compareTo(min)>=0&&element.compareTo(max)<=0) {
                newList.add(element);
            }
        }
        return newList;
    }


    public static <T> List<T> range(List<? extends T> list, T min, T max, Comparator<? super T> comparator) {

        List<T> newList = new LinkedList<>();
        for(T element:list) {
            if(comparator.compare(element, min)>=0 && comparator.compare(element, max)<=0) {
                newList.add(element);
            }
        }
        return newList;
    }

    public static <T> void copy(List<? super T> dest, List<? extends T> src) {
        for (int i = 0; i < src.size(); i++) {
            dest.set(i, src.get(i));
        }
    }

}