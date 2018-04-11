package ru.sberbank.jschool.homework.kudryavukh;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PluginClassloader extends ClassLoader {

    private static Map<String, Class> cach = new HashMap<>();
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

        Class classs = cach.get(name);
        if (classs != null) { //Проверка есть ли имя класса в кеше
            return classs;
        }


        byte[] byteClass = new byte[0];

        try {
            byteClass = Files.readAllBytes(Paths.get(new File(pathClass).toURI()));
            classs = defineClass(name, byteClass, 0, byteClass.length);
            cach.put(name, classs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classs;
        //return getaClass(name, byteClass);
    }

//    private Class<?> getaClass(String name, byte[] byteClass) {
//        try {
//            return defineClass(name, byteClass, 0, byteClass.length);
//        } catch (Throwable e) {
//            try {
//                return super.findClass(name);
//            } catch (ClassNotFoundException e1) {
//                e1.printStackTrace();
//            }
//        }
//        return null;
//    }

    class ClassNameAlreadyExistException extends Exception {
        public ClassNameAlreadyExistException(String message) {
            super(message);
        }
    }
}
