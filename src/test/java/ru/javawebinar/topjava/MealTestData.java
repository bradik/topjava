package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.BeanMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.util.Objects;

import static ru.javawebinar.topjava.model.BaseEntity.START_SEQ;

public class MealTestData {

    public static final BeanMatcher<Meal> MATCHER = new BeanMatcher<>((expected, actual) ->
            expected == actual ||
            (Objects.equals(expected.getCalories(), actual.getCalories())
                    && Objects.equals(expected.getDateTime(), actual.getDateTime())
                    && Objects.equals(expected.getDescription(), actual.getDescription())
                    && Objects.equals(expected.getId(), actual.getId())));

}
