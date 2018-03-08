package ru.sberbank.jschool.homework.kudryavukh;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    private static String pathtobin;
    private static int offset = 5;

    public static void main(String[] args) throws InterruptedException {

        try {
            encryptClassFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(pathtobin);


        Class<?> testClasses = null;
        try {
            String nameClasses = getNameClass(pathtobin);
            testClasses = Class.forName(nameClasses, true,
                    new EncryptedClassLoader(pathtobin, offset));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(testClasses.getClassLoader());
    }

    /**
     * Тестовый метод для вывода информации byte файла
     * @param bytes
     * @param info
     */

    public static void outputInfo(byte[] bytes, String info) {

        System.out.println("/////////" + info + "/////////");
        for(int i=0; i < bytes.length; i++) {
            System.out.print((char)bytes[i]);
        }
    }

    public static void encryptClassFile() throws IOException {
        JFileChooser fileopen = new JFileChooser();
        fileopen.showDialog(null, "Открыть файл");
        File file = fileopen.getSelectedFile();
        if(file == null) {
            System.exit(0);
        }
        pathtobin = file.getPath();
        byte[] byteEncrypted = Files.readAllBytes(Paths.get(file.toURI()));
        char temp;

        //outputInfo(byteEncrypted, "Файл до шифрования");

        for(int i=0; i<byteEncrypted.length; i++) {
            temp = (char) (byteEncrypted[i] + offset);
            byteEncrypted[i] = (byte) temp;
        }
        //outputInfo(byteEncrypted, "Файл после шифровки");

        FileWriter fileWriter = new FileWriter(file, false);
        char[] buffer = new char[byteEncrypted.length];
        for(int i=0; i<byteEncrypted.length; i++) {
            buffer[i] = (char)byteEncrypted[i];
        }
        fileWriter.write(buffer);
        fileWriter.close();
    }

    public static String getNameClass(String path) {
        int firstIndex;
        int lastIndex;
        lastIndex = path.lastIndexOf('.');
        firstIndex = path.lastIndexOf('\\');
        return path.substring(firstIndex + 1, lastIndex);
    }
}