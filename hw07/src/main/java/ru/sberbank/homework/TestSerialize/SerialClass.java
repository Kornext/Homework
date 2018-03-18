package ru.sberbank.homework.TestSerialize;

import java.io.Serializable;

public class SerialClass implements Serializable {
    private String one;
    private int two;

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public int getTwo() {
        return two;
    }

    public void setTwo(int two) {
        this.two = two;
    }
}