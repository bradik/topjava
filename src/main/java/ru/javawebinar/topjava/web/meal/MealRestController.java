package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.javawebinar.topjava.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(MealRestController.REST_URL)
public class MealRestController extends AbstractMealController {
    static final String REST_URL = "/rest/meals";

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }


    @Override
    @GetMapping(value = "/between", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getBetween(
            @RequestParam(name="startDate", required = false) LocalDate startDate,
            @RequestParam(name="startTime", required = false) LocalTime startTime,
            @RequestParam(name="endDate", required = false) LocalDate endDate,
            @RequestParam(name="endTime", required = false) LocalTime endTime)
    {

        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}