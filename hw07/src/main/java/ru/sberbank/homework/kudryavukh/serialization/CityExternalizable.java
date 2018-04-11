package ru.sberbank.homework.kudryavukh.serialization;

import ru.sberbank.homework.common.City;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.time.LocalDate;
import java.util.List;

public class CityExternalizable extends City implements Externalizable {

    public CityExternalizable(City city) {
        this.setId(city.getId());
        this.setCityName(city.getCityName());
        this.setFoundDate(city.getFoundDate());
        this.setNumberOfInhabitants(city.getNumberOfInhabitants());
        this.setNearCities(city.getNearCities());
    }

    public CityExternalizable() {

    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(getId());
        out.writeObject(getCityName());
        out.writeObject(getFoundDate());
        out.writeLong(getNumberOfInhabitants());
        out.writeObject(getNearCities());
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        int id = in.readInt();
        String cityName = (String) in.readObject();
        LocalDate foundDate = (LocalDate) in.readObject();
        long numberOfInhabitants = in.readLong();
        List<City> nearCities = (List<City>) in.readObject();
        addCity(new City(id, cityName, foundDate, numberOfInhabitants, nearCities));
    }
}
