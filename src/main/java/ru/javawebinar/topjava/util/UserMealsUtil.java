package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        System.out.println("By Cycles:");
        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(14, 0), 2000);
        mealsTo.forEach(System.out::println);

        System.out.println("By Stream:");
        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(14, 0), 2000));

        System.out.println("By Cycle:");
        List<UserMealWithExcess> mealsTo1 = filteredByCycle(meals, LocalTime.of(7, 0), LocalTime.of(14, 0), 2000);
        mealsTo1.forEach(System.out::println);
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesPerDayMap = new HashMap<>();

        for(UserMeal meal : meals) {
            caloriesPerDayMap.merge(meal.getDateTime().toLocalDate(), meal.getCalories(), Integer::sum);
        }

        List<UserMealWithExcess> userMealWithExcesses = new ArrayList<>();
        for(UserMeal meal : meals) {
            if (TimeUtil.isBetweenHalfOpen(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                userMealWithExcesses.add(new UserMealWithExcess(meal.getDateTime(), meal.getDescription(), meal.getCalories(), caloriesPerDayMap.get(meal.getDateTime().toLocalDate()) > caloriesPerDay));
            }
        }
        return userMealWithExcesses;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesPerDayMap = new HashMap<>();
        meals.stream().forEach(userMeal -> caloriesPerDayMap.merge(userMeal.getDateTime().toLocalDate(), userMeal.getCalories(), Integer::sum));

        List<UserMealWithExcess> userMealWithExcesses = new ArrayList<>();
        meals.stream().filter(userMeal -> TimeUtil.isBetweenHalfOpen(userMeal.getDateTime().toLocalTime(), startTime, endTime))
                .forEach(userMeal -> userMealWithExcesses.add(new UserMealWithExcess(userMeal.getDateTime(),
                        userMeal.getDescription(),
                        userMeal.getCalories(),
                        caloriesPerDayMap.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay)));

        return userMealWithExcesses;
    }

    public static List<UserMealWithExcess> filteredByCycle(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesPerDayMap = new HashMap<>();
        Map<LocalDateTime, UserMealWithExcess> mealWithExcessMap = new HashMap<>();

        for(UserMeal meal : meals) {
            LocalDateTime userMealDate = meal.getDateTime();
            if (TimeUtil.isBetweenHalfOpen(userMealDate.toLocalTime(), startTime, endTime)) {
                mealWithExcessMap.put(userMealDate, new UserMealWithExcess(meal.getDateTime(),
                        meal.getDescription(),
                        meal.getCalories(),
                        false));
            }

            caloriesPerDayMap.merge(userMealDate.toLocalDate(), meal.getCalories(), Integer::sum);

            mealWithExcessMap.values().forEach(entry -> {
                if (caloriesPerDayMap.containsKey(entry.getDateTime().toLocalDate())) {
                    entry.setExcess(caloriesPerDayMap.get(entry.getDateTime().toLocalDate()) > caloriesPerDay);
                }
            });
        }
        return new ArrayList<>(mealWithExcessMap.values());
    }

    public static List<UserMealWithExcess> filteredByStream(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> mapCalories = new HashMap<>();
        List<UserMealWithExcess> userMealWithExcesses = new ArrayList<>();
        Map<LocalDateTime, List<UserMeal>> localDateListMap = new HashMap<>();
        Map<LocalDateTime, List<UserMeal>> localDateListMap1 = new HashMap<>();
        Map<LocalDate, UserMealWithExcess> mealWithExcessMap = new HashMap<>();
        List<UserMeal> listExcess;
        Map<LocalDateTime, UserMealWithExcess> listExcessMeal;

        listExcessMeal = meals.stream()
                .filter(userMeal -> TimeUtil.isBetweenHalfOpen(userMeal.getDateTime().toLocalTime(), startTime, endTime))
                .map(userMeal -> new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), false))
                .collect(Collectors.toMap(UserMealWithExcess::getDateTime, a -> a));

        meals.stream().collect(Collectors.groupingBy(userMeal -> {
            mapCalories.merge(userMeal.getDateTime().toLocalDate(), userMeal.getCalories(), Integer::sum);
            return userMeal.getDateTime();
        }));

        return userMealWithExcesses;
    }
}
