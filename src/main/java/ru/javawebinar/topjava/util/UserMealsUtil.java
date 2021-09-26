package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.function.Function;
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

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 500);
        mealsTo.forEach(System.out::println);

        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> mapDate = new HashMap<>();
        Map<LocalDate, UserMealWithExcess> mealWithExcessMap = new HashMap<>();

        for(UserMeal uM : meals) {
            LocalDateTime userMealDate = uM.getDateTime();
            if (TimeUtil.isBetweenHalfOpen(userMealDate.toLocalTime(), startTime, endTime)) {
                mealWithExcessMap.put(uM.getDateTime().toLocalDate(), new UserMealWithExcess(userMealDate, uM.getDescription(), uM.getCalories(), false));
            }
            mapDate.merge(userMealDate.toLocalDate(), uM.getCalories(), (a, b) -> {
                mealWithExcessMap.get(userMealDate.toLocalDate()).setExcess(a+b > caloriesPerDay);
                return (a+b);
            });
        }
        return new ArrayList<>(mealWithExcessMap.values());
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
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


//
//        localDateListMap.entrySet().stream().map(entry -> {
//           entry.getValue().stream().map(userMeal -> {
//               if (TimeUtil.isBetweenHalfOpen(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
//                   userMealWithExcesses.add(new UserMealWithExcess(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(),
//                           mapCalories.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay));
//               }
//               return userMealWithExcesses;
//           });
//            return userMealWithExcesses;
//        });

        return userMealWithExcesses;
    }
}
