package ru.sberbank.homework.kudryavukh.serialization;

import ru.sberbank.homework.common.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class RouteSerialization<T extends City> extends Route<T> implements Serializable {
    private int prostopole;

    public RouteSerialization(String routeName, List<T> cities) {
        super(routeName, cities);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public void setProstopole(int prostopole) {
        this.prostopole = prostopole;
    }

    public int getProstopole() {
        return prostopole;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeObject(prostopole);
        oos.writeObject(getRouteName());
        oos.writeObject(getCities());
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        prostopole = (int) ois.readObject();
        setRouteName((String) ois.readObject());
        setCities((List<T>) ois.readObject());
    }


}
