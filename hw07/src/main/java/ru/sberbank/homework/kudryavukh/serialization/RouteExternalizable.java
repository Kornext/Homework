package ru.sberbank.homework.kudryavukh.serialization;

import ru.sberbank.homework.common.City;
import ru.sberbank.homework.common.Route;

import java.io.*;
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
        out.writeObject(getCities());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        setRouteName((String) in.readObject());
        setCities((List<T>) in.readObject());
    }

    @Override
    public String toString() {
        return super.toString();
    }

//    private void writeObject(ObjectOutputStream oos) throws IOException {
//        oos.writeObject(getRouteName());
//        oos.writeObject(getCities());
//    }
//
//    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
//        setRouteName((String) ois.readObject());
//        setCities((List<T>) ois.readObject());
//    }
}
