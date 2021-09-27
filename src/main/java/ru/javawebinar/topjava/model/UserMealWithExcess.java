package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public class UserMealWithExcess {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean excess;

    private Map<LocalDate, Integer> caloriesPerDayMap;

    private int maxDayCalories;

    public UserMealWithExcess(LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

    public void setCaloriesPerDayMap(Map<LocalDate, Integer> caloriesPerDayMap) {
        this.caloriesPerDayMap = caloriesPerDayMap;
    }

    public boolean setExcess() {
        if (caloriesPerDayMap == null) {
            return excess;
        } else {
            return caloriesPerDayMap.get(dateTime.toLocalDate()) > maxDayCalories;
        }
    }

    public void setMaxDayCalories(int maxDayCalories) {
        this.maxDayCalories = maxDayCalories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {
        return "UserMealWithExcess{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + setExcess() +
                '}';
    }
}
