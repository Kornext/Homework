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
        for (City c : getCities()) {                           //на этапе записи
            CityExternalizable cc = new CityExternalizable(c);
            out.writeObject(cc);
            out.writeInt(cc.getId());
            out.writeObject(cc.getCityName());
            out.writeObject(cc.getFoundDate());
            out.writeLong(cc.getNumberOfInhabitants());
            out.writeObject(cc.getNearCities());
        }
    }


    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setRouteName((String) in.readObject());
        int size = in.readInt();
        List<City> city = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            CityExternalizable c = (CityExternalizable) in.readObject();
            c.setId(in.readInt());
            c.setCityName((String) in.readObject());
            c.setFoundDate((LocalDate) in.readObject());
            c.setNumberOfInhabitants(in.readLong());
            c.setNearCities((List<City>)in.readObject());
            city.add(c);
        }
        setCities((List<T>) city);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}