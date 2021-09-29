package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;

public class UserMealWithExcess {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private boolean excess;

    private UserCalories userCalories;

    public UserMealWithExcess(LocalDateTime dateTime, String description, int calories, UserCalories userCalories) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.userCalories = userCalories;
    }

    public UserMealWithExcess(LocalDateTime dateTime, String description, int calories, boolean excess) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
    }

    public boolean isExcess() {
        if (userCalories == null) {
            return excess;
        } else {
            return userCalories.getCaloriesPerDay(dateTime.toLocalDate()) > userCalories.getMaxDayCalories();
        }
    }

    @Override
    public String toString() {
        return "UserMealWithExcess{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", excess=" + isExcess() +
                '}';
    }
}
