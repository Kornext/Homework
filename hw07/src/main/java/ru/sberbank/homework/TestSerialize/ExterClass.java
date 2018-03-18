package ru.sberbank.homework.TestSerialize;

import java.io.*;

public class ExterClass implements Externalizable {

    private String message = "jas";
    private MainClass mainClass = null;

    public static void main(String[] args) throws Exception{
        ExterClass exter = new ExterClass();
        FileOutputStream fos = new FileOutputStream("easd");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        exter.writeExternal(oos);

    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        //out.writeObject(mainClass);
        out.writeChars(message);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        mainClass = (MainClass) in.readObject();
        message = in.readLine();
    }
}