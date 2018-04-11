package ru.sberbank.jschool.homework.kudryavukh;


import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PluginManager {

    // directory that contains plugin folders
    private final String rootDirectory;

    public PluginManager(String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }


    /**
     * method takes as a parameter a folder name in the root plugin directory,
     * loads the plugin .class file from the folder if present,
     * and returns a Plugin object
     *
     * @param pluginName - name of the plugin folder
     * @return Plugin
     * @throws PluginNotFoundException - when folder named 'pluginName' is missing,
     *                                 or it contains no .class files
     */
    public Plugin loadPlugin(String pluginName) throws PluginNotFoundException {
        //TODO implement
        File[] filesOnPluginDir = new File[0];
        try {
            Path path = Paths.get(rootDirectory + "\\" + pluginName);
            if (!Files.exists(path)) {         //Проверка, существует ли дирректория.
                throw new FolderNotFoundException("Папка с плагином " + pluginName + "не найдена! " +
                        "Проверьте соответствие имени плагина и названия папки");
            }
            File dir = new File(rootDirectory + "\\" + pluginName);
            filesOnPluginDir = dir.listFiles();
            if (filesOnPluginDir.length == 0) { //Проверка, существуют ли файлы.
                throw new FileNotFoundException("Папка с плагином " + pluginName + " не содержит файлов!");
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (FolderNotFoundException e) {
            System.out.println(e.getMessage());
        }

        //Папка корректна с именем плагина
        //Файлы/файл там есть
        //Проблема - файлов в листе нет.

        for (int i = 0; i < filesOnPluginDir.length; i++) {
            //ClassLoader cl = new PluginClassloader(filesOnPluginDir[i].getPath(), this.getClass().getClassLoader());
            String s = cutClassName(filesOnPluginDir[i].getPath());


            //ClassLoader customeClassloader = new PluginClassloader(filesOnPluginDir[i].getPath());
            ClassLoader customeClassloader = new PluginClassloader(filesOnPluginDir[i].getPath());
            String nameClass = getNameClass(filesOnPluginDir[i].getPath());
            try {
                return (Plugin) Class.forName(nameClass, true, customeClassloader).newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        throw new PluginNotFoundException("couldn't locate plugin " + pluginName);
    }

    public static String getNameClass(String path) {
        int firstIndex;
        int lastIndex;
        lastIndex = path.lastIndexOf('.');
        firstIndex = path.lastIndexOf('\\');
        return path.substring(firstIndex + 1, lastIndex);
    }

    public static String cutClassName(String path) {
        int lastindex = path.lastIndexOf('\\');
        String s = path.substring(0, lastindex);
        return s;
    }

    class FolderNotFoundException extends Exception {
        public FolderNotFoundException(String message) {
            super(message);
        }
    }
}
