package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;

/**
 * Created by Brad on 16.07.2017.
 */
public interface MealDao {

    List<MealWithExceed> getAllItems();

}
