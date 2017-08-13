package ru.javawebinar.topjava.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.MATCHER;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by Brad on 13.08.2017.
 */
@Profile("datajpa")
public class UserServiceTestDataJPA extends UserServiceTest {

    @Autowired
    protected MealService mealService;

    @Test
    public void testGetWithMeals() throws Exception {

        List<Meal> meals1 = mealService.getAll(USER_ID);

        service.evictCache();
        User user = service.get(USER_ID);
        List<Meal> meals2 = user.getMeals();

        //Assert.assertEquals(meals1.size(),meals2.size());
        MealTestData.MATCHER.assertCollectionEquals(meals1, meals2);
        MATCHER.assertEquals(USER, user);
    }
}
