package ru.sberbank.homework.kudryavukh.serialization;

import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RouteExternalizable<T extends City> extends Route<T> implements Externalizable {

    static final long serialVersionUID = 10275531232837495L;

    public RouteExternalizable() {

    }

    public RouteExternalizable(String routeName, List<T> cities) {

        super(routeName, cities);
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(getRouteName());
        out.writeInt(getCities().size());
        for (City c : getCities()) {
//            out.writeInt(c.getId());
//            out.writeObject(c.getCityName());
//            out.writeObject(c.getFoundDate());
//            out.writeLong(c.getNumberOfInhabitants());
//            out.writeObject(c.getNearCities());
            out.writeObject(c);
        }

        //out.writeObject(getCities());
    }


    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setRouteName((String) in.readObject());
        int size = in.readInt();
        List<City> city = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
//            int id = in.readInt();
//            String cityName = (String) in.readObject();
//            LocalDate foundDate = (LocalDate) in.readObject();
//            long numberOfInhabitants = in.readLong();
//            List<City> nearCities = (List<City>) in.readObject();
////            city.add(new City(id, cityName, foundDate, numberOfInhabitants, nearCities));
            city.add((City) in.readObject());
        }
        setCities((List<T>) city);
        //setCities((List<T>) in.readObject());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}