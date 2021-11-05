package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

// add userId

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final Map<Integer, Map<Integer, Meal>> mealUserRepository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            mealUserRepository.put(userId, repository);
            return meal;
        }
        // if userId doesn't exist
        if (!mealUserRepository.get(userId).containsKey(meal.getId())) {
            return null;
        } else {
            // handle case: update, but not present in storage
            Meal mealLocal = repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
            mealUserRepository.replace(userId, repository);
            return mealLocal;
        }
    }

    @Override
    public boolean delete(int id, int userId) {
        if (!mealUserRepository.get(userId).containsKey(id)) {
            return false;
        } else {
            return mealUserRepository.get(userId).remove(id) != null;
        }
    }

    @Override
    public Meal get(int id, int userId) {
        return mealUserRepository.get(userId).getOrDefault(id, null);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return mealUserRepository.get(userId).values().stream().sorted(Comparator.comparing(Meal::getDateTime).reversed()).collect(Collectors.toList());
    }
}

