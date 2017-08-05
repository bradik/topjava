package ru.javawebinar.topjava.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

/**
 * Created by Brad on 02.08.2017.
 */

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
public class MealServiceTest {


    private static final int USER_MEAL_ID = 100002;
    private static final int ADMIN_ID = UserTestData.ADMIN_ID;
    private static final int USER_ID = UserTestData.USER_ID;

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();


    }


    @Test(expected = NotFoundException.class)
    public void get() throws Exception {
        Meal meal = service.get(USER_MEAL_ID, ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void delete() throws Exception {
        service.delete(USER_MEAL_ID, ADMIN_ID);
    }

    @Test
    public void getBetweenDates() throws Exception {
        List<Meal> mealList = service.getBetweenDates(
                LocalDateTime.parse("2017-05-30T10:00:00").toLocalDate(),
                LocalDateTime.parse("2017-05-30T20:00:00").toLocalDate(), ADMIN_ID);
        Assert.assertEquals(mealList.size(),3);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        List<Meal> mealList = service.getBetweenDateTimes(
                LocalDateTime.parse("2017-05-30T10:00:00"),
                LocalDateTime.parse("2017-05-30T19:00:00"), ADMIN_ID);
        Assert.assertEquals(mealList.size(),2);
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> mealList = service.getAll(ADMIN_ID);
        Assert.assertEquals(mealList.size(),6);
    }

    @Test(expected = NotFoundException.class)
    public void update() throws Exception {
        Meal meal = service.get(USER_MEAL_ID, USER_ID);
        meal.setDescription("New Description");
        service.update(meal, ADMIN_ID);
        MATCHER.assertEquals(meal,service.get(meal.getId(), ADMIN_ID));
    }

    @Test
    public void save() throws Exception {
        Meal meal = new Meal(LocalDateTime.now(),"new",100);
        MATCHER.assertEquals(meal,service.save(meal, USER_ID));
    }

}