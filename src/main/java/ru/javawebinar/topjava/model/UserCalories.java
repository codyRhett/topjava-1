package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.util.Map;

public class UserCalories {
    private final Map<LocalDate, Integer> caloriesPerDayMap;

    private final int maxDayCalories;

    public UserCalories(Map<LocalDate, Integer> caloriesPerDayMap, int maxDayCalories){
        this.caloriesPerDayMap = caloriesPerDayMap;
        this.maxDayCalories = maxDayCalories;
    }

    public int getMaxDayCalories(){
        return maxDayCalories;
    }

    public int getCaloriesPerDay(LocalDate localDate) {
        return caloriesPerDayMap.get(localDate);
    }
}
