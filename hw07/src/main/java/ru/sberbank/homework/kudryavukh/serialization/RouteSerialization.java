package ru.sberbank.homework.kudryavukh.serialization;

import ru.sberbank.homework.common.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class RouteSerialization<T extends City> extends Route<T> implements Serializable {

    static final long serialVersionUID = 10275539472837495L;
    public RouteSerialization(String routeName, List<T> cities) {
        super(routeName, cities);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeObject(getRouteName());
        oos.writeObject(getCities());
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        setRouteName((String) ois.readObject());
        setCities((List<T>) ois.readObject());
    }


}
