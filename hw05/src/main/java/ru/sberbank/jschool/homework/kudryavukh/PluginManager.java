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
     *                                   or it contains no .class files
     */
    public Plugin loadPlugin(String pluginName) throws PluginNotFoundException {
        //TODO implement
        File[] filesOnPluginDir = new File[0];
        //Проверка, существует ли дирректория и файл.
        try {
            Path path = Paths.get(rootDirectory + "\\" + pluginName);
            if (!Files.exists(path)) {
                throw new FolderNotFoundException("Папка с плагином " + pluginName + "не найдена! " +
                        "Проверьте соответствие имени плагина и названия папки");
            }
            File dir = new File(rootDirectory + "\\" + pluginName);
            filesOnPluginDir = dir.listFiles();
            if (filesOnPluginDir.length == 0) {
                throw new FileNotFoundException("Папка с плагином " + pluginName + " не содержит файлов!");
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (FolderNotFoundException e) {
            e.getMessage();
        }

        for(int i=0; i<filesOnPluginDir.length; i++) {
            ClassLoader cl = new PluginClassloader(filesOnPluginDir[i].getPath(), this.getClass().getClassLoader());
            String nameClass = getNameClass(filesOnPluginDir[i].getPath());
            try {
                return (Plugin) Class.forName(nameClass, true, cl).newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        throw new PluginNotFoundException ("couldn't locate plugin " + pluginName);
    }

    public static String getNameClass(String path) {
        int firstIndex;
        int lastIndex;
        lastIndex = path.lastIndexOf('.');
        firstIndex = path.lastIndexOf('\\');
        return path.substring(firstIndex + 1, lastIndex);
    }

    class FolderNotFoundException extends Exception {
        public FolderNotFoundException(String message) {
            super(message);
        }
    }
}
