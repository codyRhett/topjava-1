package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class MealTestData {
    public static final int MAX_CALORIESPERDAY = 2000;

    private static final UUID uuid_1 = UUID.randomUUID();
    private static final UUID uuid_2 = UUID.randomUUID();
    private static final UUID uuid_3 = UUID.randomUUID();
    private static final UUID uuid_4 = UUID.randomUUID();
    private static final UUID uuid_5 = UUID.randomUUID();
    private static final UUID uuid_6 = UUID.randomUUID();
    private static final UUID uuid_7 = UUID.randomUUID();

    public static List<Meal> createMealsList() {
        return Arrays.asList(
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500, uuid_1.toString()),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000, uuid_2.toString()),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500, uuid_3.toString()),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100, uuid_4.toString()),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000, uuid_5.toString()),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500, uuid_6.toString()),
                new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410, uuid_7.toString())
        );
    }
}
