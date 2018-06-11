package ru.javawebinar.topjava.web.meal;

import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;

public class MealRestController {

    private MealService service;

    public List<MealWithExceed> getAll() {
        return MealsUtil.getWithExceeded(service.getAll(AuthorizedUser.id()), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public Meal get(int id) {
        return service.get(AuthorizedUser.id(), id);
    }

    public Meal create(Meal meal) {
        return service.create(AuthorizedUser.id(), meal);
    }

    public void delete(int id) {
        service.delete(AuthorizedUser.id(), id);
    }

    public void update(Meal meal) {
        service.update(AuthorizedUser.id(), meal);
    }
}