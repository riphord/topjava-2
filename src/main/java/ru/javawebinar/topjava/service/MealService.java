package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

public interface MealService {
    void update(int userId, Meal meal);

    void delete(int userId, int Id) throws NotFoundException;

    Meal create(int userId, Meal meal);

    Meal get(int userId, int id) throws NotFoundException;

    List<Meal> getAll(int userId);
}