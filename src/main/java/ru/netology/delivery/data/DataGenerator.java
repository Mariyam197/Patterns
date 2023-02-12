package ru.netology.delivery.data;


import com.github.javafaker.Faker;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;


public class DataGenerator {
    private DataGenerator() {
    }

    static Faker faker = new Faker(new Locale("ru"));

    public static String generateDate(int shift) {
        return LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateCity() {
        var cities = new String[]{"Майкоп", "Москва", "Саранск", "Санкт-Петербург", "Самара", "Уфа", "Махачкала",
        "Симферополь", "Казань", "Липецк", "Тюмень", "Тула", "Владивосток", "Саратов", "Омск", "Челябинск", "Хабаровск",
        "Пенза", "Оренбург", "Псков", "Кострома", "Мурманск"};
        return cities[new Random().nextInt(cities.length)];
    }

    public static String generateName() {
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generatePhone() {
        return faker.phoneNumber().phoneNumber();
    }

}
