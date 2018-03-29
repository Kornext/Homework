package ru.sberbank.homework.TestSerialize;

import java.io.*;

public class CustomObject implements Externalizable {

    public CustomObject() {
    }

    private int asd = 45;

    public int getAsd() {
        return asd;
    }

    public void setAsd(int asd) {
        this.asd = asd;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }
}
