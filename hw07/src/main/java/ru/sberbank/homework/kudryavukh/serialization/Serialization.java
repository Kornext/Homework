package ru.sberbank.homework.kudryavukh.serialization;

import ru.sberbank.homework.common.*;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Serialization extends RouteService<City, Route<City>> {

    private Map<String, Route<City>> routeMap = new HashMap<>();

    //TODO эксперементы!!!

    public Serialization(CachePathProvider pathProvider, boolean devMode) {
        super(pathProvider, devMode);
    }

    @Override
    public Route<City> getRoute(String from, String to) {
        String key = from + " - " + to;
        Route<City> route = routeMap.get(key);
        //System.out.println("Map " + routeMap.size());
        //System.out.println(key);
        if(route == null) {
            //System.out.println("Popali v serial");
            RouteSerialization routeSerial = (RouteSerialization) super.getRoute(from, to);
            routeMap.put(key, routeSerial);
            serialize(key, routeSerial);
            return routeSerial;
        }
        else {
            //System.out.println("Popali v deserial");
            return route = deserialize(key);
        }
    }

    public void serialize(String fileName, RouteSerialization<City> obj) {
        String pathFile = pathProvider.getCacheDirectoryPath() + "\\" + fileName;
        try (FileOutputStream fos = new FileOutputStream(pathFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            obj.setProstopole(13);
            System.out.println(obj.getRouteName());

            oos.writeObject(obj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println("Serialize Close");

    }

    public Route<City> deserialize(String fileName) {
        String pathFile = pathProvider.getCacheDirectoryPath() + "\\" + fileName;
        try (FileInputStream input = new FileInputStream(pathFile);
             ObjectInputStream obj = new ObjectInputStream(input)) {
            //System.out.println("Deserialize Close / Obj");

            RouteSerialization<City> s = (RouteSerialization<City>) obj.readObject();
            System.out.println(s.getProstopole());
            System.out.println("Des " + s.getRouteName());


            return s;

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
    protected RouteSerialization createRoute(List<City> cities) {
        return new RouteSerialization(UUID.randomUUID().toString(), cities);
    }

//    private void writeObject(ObjectOutputStream oos)
//            throws IOException {
//        oos.writeObject(getRouteName());
//        oos.writeObject(getCities());
//    }
//
//    private void readObject(ObjectInputStream ois)
//            throws IOException, ClassNotFoundException {
//        setRouteName((String) ois.readObject());
//        setCities((List<C>) ois.readObject());
//    }
}


//+    private void serialize(String filename, SerializableRoute<City> route) {
//        +        try (FileOutputStream fos = new FileOutputStream(filename);
//        +             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
//        +            oos.writeObject(route);
//        +        } catch (IOException e) {
//        +            e.printStackTrace();
//        +        }
//        +    }
//        +
//        +    private SerializableRoute<City> deserialize(String filename) {
//        +        SerializableRoute<City> route;
//        +        try (FileInputStream fis = new FileInputStream(filename);
//        +             ObjectInputStream ois = new ObjectInputStream(fis)) {
//        +            route = (SerializableRoute<City>) ois.readObject();
//        +        } catch (IOException | ClassNotFoundException e) {
//        +            throw new RouteFetchException(e);
//        +        }
//        +        return route;
//        +
//        +    }