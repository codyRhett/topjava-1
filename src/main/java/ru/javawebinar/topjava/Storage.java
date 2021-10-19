package ru.javawebinar.topjava;

import java.util.List;

public interface Storage {
    void clear();

    void update();

    void get(String uuid);

    void delete(String uuid);

    void save();


    int size();
}
