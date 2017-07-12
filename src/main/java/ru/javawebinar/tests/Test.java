package ru.javawebinar.tests;

import org.openjdk.jmh.annotations.*;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Brad on 11.07.2017.
 */

@State(Scope.Benchmark)
@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.NANOSECONDS)
@Measurement(iterations = 1, time = 1, timeUnit = TimeUnit.NANOSECONDS)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class Test {
    private List<UserMeal> getTestData(){
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );

        return mealList;
    }

    @Benchmark
    public void testCycles() {

        List<UserMealWithExceed> filteredWithExceeded = UserMealsUtil.getFilteredWithExceeded(getTestData(), LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

    }

    @Benchmark
    public void testStreams() {

        List<UserMealWithExceed> filteredWithExceeded = UserMealsUtil.getFilteredWithExceededWithApi8(getTestData(), LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);

    }
}
