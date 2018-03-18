package ru.sberbank.homework.TestSerialize;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MainClass extends SerialClass {

    private String message = "OneTwoThree";
    private int number = 1;
    private transient int numberTwo = 2;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public static void main(String[] args) {

        SerialClass serialClass = new SerialClass();
        serialClass.setOne("Number");
        serialClass.setTwo(13);

        System.out.println(serialClass.getOne());
        System.out.println(serialClass.getTwo());

        List<SerialClass> list = new ArrayList<>();
        list.add(serialClass);

        serialize("serial", list);

        SerialClass copySerialClass;
        List<SerialClass> dfg = deserialize("serial");
        copySerialClass = dfg.get(0);
        System.out.println(copySerialClass.getOne());
        System.out.println(copySerialClass.getTwo());
    }

    public static <T extends List> void serialize(String fileName, T obj) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Serialize Close");

    }

    public static List deserialize(String fileName) {
        System.out.println("Deserialize Open");
        try (FileInputStream input = new FileInputStream(fileName);
             ObjectInputStream obj = new ObjectInputStream(input)) {
            System.out.println("Deserialize Close / Obj");
            return (List) obj.readObject();

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            System.out.println("Deserialize Close / null");
            return null;
        }
    }
}