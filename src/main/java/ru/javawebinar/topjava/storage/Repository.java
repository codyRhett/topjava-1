package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;
import java.util.List;

public interface Repository {
    void update(Meal meal);

    Meal get(String uuid);

    void delete(String uuid);

    void save(Meal meal);

    List<Meal> getAll();
}
