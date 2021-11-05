package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public static final List<User> users = Arrays.asList(
            new User(2, "Sergey", "sergey@yandex.ru", "12345", Role.USER, Role.values()),
            new User(3, "Artem", "Artem@yandex.ru", "12345", Role.USER, Role.values()),
            new User(4, "Tanya", "Tanya@yandex.ru", "12345", Role.USER, Role.values()),
            new User(5, "Sasha", "Sasha@yandex.ru", "12345", Role.USER, Role.values()),
            new User(6, "Lena", "Lena@yandex.ru", "12345", Role.USER, Role.values()),
            new User(7, "Katya", "Katya@yandex.ru", "12345", Role.USER, Role.values()),
            new User(8, "John", "John@yandex.ru", "12345", Role.USER, Role.values())
    );
}
