package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MyRepository implements Repository {
    protected final ConcurrentMap<String, Meal> mapMealStorage = new ConcurrentHashMap<>();

    @Override
    public void update(Meal meal) {
        mapMealStorage.replace(meal.getUuid(), meal);
    }

    @Override
    public Meal get(String uuid) {
        return mapMealStorage.get(uuid);
    }

    @Override
    public void delete(String uuid) {
        mapMealStorage.remove(uuid);
    }

    @Override
    public void save(Meal meal) {
        mapMealStorage.put(meal.getUuid(), meal);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(mapMealStorage.values());
    }
}
