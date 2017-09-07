package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.http.MediaType;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.matcher.BeanMatcher;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.AbstractControllerTest;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.javawebinar.topjava.MealTestData.MEAL1;
import static ru.javawebinar.topjava.MealTestData.MEAL2;
import static ru.javawebinar.topjava.MealTestData.MEAL3;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

/**
 * Created by Brad on 06.09.2017.
 */
public class MealRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = MealRestController.REST_URL;

    public static final BeanMatcher<MealWithExceed> MEAL_WITH_EXCEED_BEAN_MATCHER = BeanMatcher.of(MealWithExceed.class);

    @Test
    public void testGetAll() throws Exception {
        AuthorizedUser.setId(USER_ID);

        List<MealWithExceed> mealWithExceeds = MealsUtil.getWithExceeded(MealTestData.MEALS,
                AuthorizedUser.getCaloriesPerDay());

        mockMvc.perform(get(REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MEAL_WITH_EXCEED_BEAN_MATCHER.contentListMatcher(mealWithExceeds));


    }

    @Test
    public void testGetBetween() throws Exception {

        AuthorizedUser.setId(USER_ID);


        LocalDateTime startLdt = LocalDateTime.of(2015, Month.MAY, 30, 0, 0, 0);
        LocalDateTime endLdt = LocalDateTime.of(2015, Month.MAY, 30, 23, 59, 59);

        String test_URL = REST_URL
                + "/between?startDate=" + startLdt.toLocalDate()
                + "&startTime=" + startLdt.toLocalTime()
                + "&endDate=" + endLdt.toLocalDate()
                + "&endTime=" + endLdt.toLocalTime();

        mockMvc.perform(get(test_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MEAL_WITH_EXCEED_BEAN_MATCHER.contentListMatcher(
                        MealsUtil.createWithExceed(MEAL3,false),
                        MealsUtil.createWithExceed(MEAL2,false),
                        MealsUtil.createWithExceed(MEAL1,false)));


//        mockMvc.perform(get(REST_URL + "/between?end="+endDtm))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

    }
}
