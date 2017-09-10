package ru.javawebinar.topjava.web.meal;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.DateTimeUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by Brad on 09.09.2017.
 */

@RestController
@RequestMapping(value = MealAjaxController.AJAX_URL)
public class MealAjaxController extends AbstractMealController {
    static final String AJAX_URL = "/ajax/profile/meals";

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping()
    public void createOrUpdate(@RequestParam(value = "id", required = false) Integer id,
                               @RequestParam(value = "dateTime")
                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
                                       LocalDateTime dateTime,
                               @RequestParam(value = "description") String description,
                               @RequestParam(value = "calories") Integer calories) {

        Meal meal = new Meal(id, dateTime, description, calories);

        if (meal.isNew()) {
            super.create(meal);
        }
    }

}
