package ru.sberbank.jschool.homework.kudryavukh;

public class PluginNotFoundException extends ClassNotFoundException {
    public PluginNotFoundException(String s) {
        super(s);
    }

    public PluginNotFoundException(String s, Throwable ex) {
        super(s, ex);
    }
}