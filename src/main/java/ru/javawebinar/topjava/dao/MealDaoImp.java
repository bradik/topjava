package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.List;

/**
 * Created by Brad on 16.07.2017.
 */
public class MealDaoImp implements MealDao {
    @Override
    public List<MealWithExceed> getAllItems() {

        List<MealWithExceed> mealsWithExceeded = MealsUtil.getFilteredWithExceeded(
                MealsUtil.getTestMeal(), LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);

        return mealsWithExceeded;
    }
}
