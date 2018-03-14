package ru.sberbank.jschool.homework.kudryavukh;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        PluginManager pluginManager = new PluginManager("resources");
        try {
            Plugin plugin = pluginManager.loadPlugin("FirstTestPlugin");
        } catch (PluginNotFoundException e) {
            e.printStackTrace();
        }

    }
}

