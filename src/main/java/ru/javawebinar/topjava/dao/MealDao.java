package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;

/**
 * Created by Brad on 16.07.2017.
 */
public interface MealDao {

    List<MealWithExceed> getAllItems();
    Meal getItem(int id);
    void addItem(Meal item);
    void updateItem(Meal item);
    void deleteItem(int id);

}
