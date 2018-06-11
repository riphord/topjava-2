package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private Integer counter = 0;

    {
        MealsUtil.MEALS.forEach(meal -> save(meal.getUserId(),meal));
    }

    @Override
    public Meal save(int userId, Meal meal) {
        if (userId != meal.getUserId()){
            return null;
        }
        if (meal.isNew()) {
            meal.setId(counter++);
            repository.put(meal.getId(), meal);
            return meal;
        }
        // treat case: update, but absent in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public void delete(int userId, int id) {
        if (get(userId, id).getUserId() != userId){
            return;
        }
        repository.remove(id);
    }

    @Override
    public Meal get(int userId, int id) {
        Meal meal = repository.get(id);
        return userId == meal.getUserId() ? meal : null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository
                .values()
                .stream()
                .filter(meal -> meal.getUserId()==userId)
                .sorted((m1,m2) -> m2.getDateTime().compareTo(m1.getDateTime()))
                .collect(Collectors.toList());
    }
}

