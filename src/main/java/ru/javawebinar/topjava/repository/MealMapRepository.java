package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealMapRepository implements Repository {
    protected final ConcurrentMap<Integer, Meal> mapMealStorage = new ConcurrentHashMap<>();
    private AtomicInteger atomicInt = new AtomicInteger(0);

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

    private int createID() {
        return atomicInt.incrementAndGet();
    }
}
