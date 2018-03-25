package ru.sberbank.homework.kudryavukh.serialization;

import ru.sberbank.homework.common.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class ExternalizationServiceRoute extends RouteService<City, Route<City>> {

    private List<String> routeMap = new ArrayList<>();


    public ExternalizationServiceRoute(CachePathProvider pathProvider, boolean devMode) {

        super(pathProvider, devMode);
    }

    @Override
    public Route<City> getRoute(String from, String to) {
        String key = from + " - " + to;
        Route<City> route = null;
        if(routeMap.contains(key)) {
            return route = deserialize(key);
        }
        else {
            RouteExternalizable<City> routeSerial = (RouteExternalizable<City>) super.getRoute(from, to);
            routeMap.add(key);
            serialize(key, routeSerial);
            return routeSerial;
        }
    }

    public void serialize(String fileName, RouteExternalizable<City> obj) {
        String pathFile = pathProvider.getCacheDirectoryPath() + "\\" + fileName;
        try (FileOutputStream fos = new FileOutputStream(pathFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Route<City> deserialize(String fileName) {
        String pathFile = pathProvider.getCacheDirectoryPath() + "\\" + fileName;
        try (FileInputStream input = new FileInputStream(pathFile);
             ObjectInputStream obj = new ObjectInputStream(input)) {
            return (RouteExternalizable<City>) obj.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected City createCity(int id, String cityName, LocalDate foundDate, long numberOfInhabitants) {
        return new City(id, cityName, foundDate, numberOfInhabitants);
    }

    @Override
    protected RouteExternalizable createRoute(List<City> cities) {
        return new RouteExternalizable(UUID.randomUUID().toString(), cities);
    }
}