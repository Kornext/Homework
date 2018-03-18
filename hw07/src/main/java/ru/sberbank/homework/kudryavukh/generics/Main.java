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
        ASD<B> asd = new ASD<>(new B());
        ASD<C> asd2 = new ASD<>(new C());
        asd.tre(new C());
        List<? extends B> qwe = new ArrayList<B>(); //B C D
        //qwe.add(new B());

        //B c = fdsfsd(qwe, new B());


    }
}

class ASD <T extends B> {
    T ob;

    public ASD(T ob) {
        this.ob = ob;
    }

    public void tre(T asd) {
        if(this.ob.equals(asd)) {

        }

    }

}

class A {

}
class B extends A implements X {

}
class C extends B {

}
class D extends C {

}
interface X {

}

