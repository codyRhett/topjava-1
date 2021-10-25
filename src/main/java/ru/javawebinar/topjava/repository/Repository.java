package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import java.util.List;

public interface Repository {
    void update(Meal meal);

    Meal get(int id);

    void delete(int id);

    void save(Meal meal);

    List<Meal> getAll();
}
