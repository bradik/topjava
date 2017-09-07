package ru.javawebinar.topjava.web;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.matcher.BeanMatcher;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.JpaUtil;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.javawebinar.topjava.UserTestData.*;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class RootControllerTest extends AbstractControllerTest {

    public static final BeanMatcher<MealWithExceed> MEAL_WITH_EXCEED_BEAN_MATCHER = BeanMatcher.of(MealWithExceed.class);


    @Test
    public void testUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
                .andExpect(model().attribute("users", hasSize(2)))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("id", is(START_SEQ)),
                                hasProperty("name", is(USER.getName()))
                        )
                )));
    }

    @Test
    public void testMeals () throws Exception {

        AuthorizedUser.setId(USER_ID);

        mockMvc.perform(get("/meals"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("meals"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/meals.jsp"))
                //.andExpect(model().attribute("meals", hasSize(2)))
                .andExpect(model().attribute("meals",
                        new BaseMatcher<List<MealWithExceed>>() {
                            @Override
                            public boolean matches(Object item) {
                                MEAL_WITH_EXCEED_BEAN_MATCHER.assertListEquals(
                                        MealsUtil.getWithExceeded(MealTestData.MEALS,
                                                AuthorizedUser.getCaloriesPerDay()), (List<MealWithExceed>)item);
                                return true;
                            }

                            @Override
                            public void describeTo(Description description) {

                                description.appendText("testMeals() is failed!");
                            }
                        }
                ));




    }

}