package ru.sberbank.homework.kudryavukh.serialization;
import ru.sberbank.homework.common.*;

import java.time.LocalDate;

public class MainTest {

    public static void main(String[] args) {

        City firstCity = new City(1, "Donetsk", LocalDate.now(), 500_500);
        CachePathProvider cachePathProvider = new CacheImpl("C://TestSer");
        SerializationServiceRoute ser = new SerializationServiceRoute(cachePathProvider, false);
    }
}
