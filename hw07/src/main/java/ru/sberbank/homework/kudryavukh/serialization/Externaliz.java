package ru.sberbank.homework.kudryavukh.serialization;

import ru.sberbank.homework.common.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Externaliz extends RouteService<City, Route<City>> {

    private Map<String, Route<City>> routeMap = new HashMap<>();


    public Externaliz(CachePathProvider pathProvider, boolean devMode) {
        super(pathProvider, devMode);
    }

    @Override
    public Route<City> getRoute(String from, String to) {
        String key = from + " - " + to;
        Route<City> route = routeMap.get(key);
        if (route == null) {
            RouteExternalizable routeSerial = (RouteExternalizable) super.getRoute(from, to);
            routeMap.put(key, routeSerial);
            serialize(key, routeSerial);
            return routeSerial;
        } else {
            return route = deserialize(key);
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