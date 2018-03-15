package ru.sberbank.jschool.homework.kudryavukh;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PluginClassloader extends ClassLoader {

    private static List<String> cach = new ArrayList<>();

    private String pathClass;

    public PluginClassloader(String pathClass) {
        this.pathClass = pathClass;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return findClass(name);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if(cach.contains(name)) {
            try {
                throw new ClassNameAlreadyExistException("Класс с данным именем уже загружен!");
            } catch (ClassNameAlreadyExistException e) {
                e.getMessage();
            }
        }
        byte[] byteClass = new byte[0];
        try {
            byteClass = Files.readAllBytes(Paths.get(new File(pathClass).toURI()));
            cach.add(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defineClass(name, byteClass, 0, byteClass.length);
    }

    class ClassNameAlreadyExistException extends Exception {
        public ClassNameAlreadyExistException(String message) {
            super(message);
        }
    }
}
