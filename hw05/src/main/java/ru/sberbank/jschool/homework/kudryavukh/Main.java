package ru.sberbank.jschool.homework.kudryavukh;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        String pathToRoot = "C:\\Users\\Kornext_N\\Desktop\\Java HW\\Homework\\hw05\\src\\main\\resources";
        PluginManager pluginManager = new PluginManager(pathToRoot);
        try {
            Plugin plugin = pluginManager.loadPlugin("FirstTestPlugin");
            plugin.run(null);
        } catch (PluginNotFoundException e) {
            e.printStackTrace();
        }


    }
}

