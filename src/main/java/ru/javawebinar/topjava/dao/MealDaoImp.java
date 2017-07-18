package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.GeneratorID;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Brad on 16.07.2017.
 */
public class MealDaoImp implements MealDao {

    private static Map<Integer, Meal> items;

    static {

        items = Collections.synchronizedMap(new HashMap<>());

        for (Meal meal : MealsUtil.getTestMeal()) {
            items.put(meal.getId(), meal);
        }
    }

    @Override
    public List<MealWithExceed> getAllItems() {

        List<MealWithExceed> mealsWithExceeded = MealsUtil.getFilteredWithExceeded(
                MealDaoImp.items.values().stream().collect(Collectors.toList()),
                LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);

        return mealsWithExceeded;
    }

    @Override
    public Meal getItem(int id) {

        return items.get(id);
    }

    @Override
    public void addItem(Meal item) {

        items.put(item.getId(), item);
    }

    @Override
    public void updateItem(Meal item) {

        items.put(item.getId(), item);
    }

    @Override
    public void deleteItem(int id) {

        items.remove(id);

    }
}
