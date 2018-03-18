package ru.sberbank.homework.kudryavukh.serialization;
import ru.sberbank.homework.common.*;

import java.time.LocalDate;
import java.util.Locale;

public class MainTest {

    public static void main(String[] args) {

        City firstCity = new City(1, "Donetsk", LocalDate.now(), 500_500);
        CachePathProvider cachePathProvider = new CacheImpl("C://TestSer");
        Serialization ser = new Serialization(cachePathProvider, false);


    }
}
