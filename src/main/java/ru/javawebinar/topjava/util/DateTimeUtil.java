package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static boolean isBetweenHalfOpen(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) < 0;
    }

    public static boolean isBetweenHalfOpenByDateTime(LocalDateTime lt, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return (lt.toLocalDate().compareTo(startDateTime.toLocalDate()) >= 0 && lt.toLocalDate().compareTo(endDateTime.toLocalDate()) <= 0) &
                (lt.toLocalTime().compareTo(startDateTime.toLocalTime()) >= 0 && lt.toLocalTime().compareTo(endDateTime.toLocalTime()) < 0);
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }
}

