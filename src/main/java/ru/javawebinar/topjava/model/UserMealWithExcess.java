package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.UserCalories;
import java.time.LocalDateTime;

public class UserMealWithExcess {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean excess;

    private UserCalories userCalories;

    public UserMealWithExcess(LocalDateTime dateTime, String description, int calories, boolean excess, UserCalories userCalories) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.excess = excess;
        this.userCalories = userCalories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
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
