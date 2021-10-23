package ru.javawebinar.topjava.repositories;

import ru.javawebinar.topjava.model.Meal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MealMapRepository implements Repository {
    protected final ConcurrentMap<Integer, Meal> mapMealStorage = new ConcurrentHashMap<>();
    private static int idCounter = 0;

    @Override
    public void update(Meal meal) {
        mapMealStorage.replace(meal.getId(), meal);
    }

    @Override
    public Meal get(int id) {
        return mapMealStorage.get(id);
    }

    @Override
    public void delete(int id) {
        mapMealStorage.remove(id);
    }

    @Override
    public void save(Meal meal) {
        meal.setId(createID());
        mapMealStorage.put(meal.getId(), meal);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(mapMealStorage.values());
    }

    private static synchronized int createID() {
        return ++idCounter;
    }
}
