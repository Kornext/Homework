package ru.sberbank.jschool.homework.kudryavukh;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EncryptedClassLoader extends ClassLoader {

    private static String path;
    private static int offset;

    public EncryptedClassLoader(String path, int offset) {
        this.path = path;
        this.offset = offset;
    }

    @Override
    protected Class<?> findClass(String name) {
        byte[] bytes = new byte[0];

        try {
            bytes = Files.readAllBytes(Paths.get(new File(path).toURI()));
            char temp;
            for(int i=0; i<bytes.length; i++) {
                temp = (char) (bytes[i] - offset);
                bytes[i] = (byte) temp;
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        Class<?> cs = defineClass(name, bytes, 0, bytes.length);
        return cs;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }
}
