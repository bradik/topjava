package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal mealAction(String id, String dateTime, String description, String calories) {

        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(dateTime),
                description,
                Integer.valueOf(calories));


        log.info(id.isEmpty() ? "Create {}" : "Update {}", meal);
        service.save(meal);

        return meal;
    };

    public Meal mealsActionCreate(){
        log.info("Create");
        return new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
    }

    public Meal mealsActionUpdate(String id){
        log.info("Update");
        return service.get(getId(id));
    }

    public void mealsActionDelete(String id){
        log.info("Delete {}", id);
        service.delete(getId(id));
    }

    public List<MealWithExceed> mealsActionAll() {
        log.info("getAll");

        return MealsUtil.getWithExceeded(service.getAll(), MealsUtil.DEFAULT_CALORIES_PER_DAY);
    };

    private int getId(String id) {
        String paramId = Objects.requireNonNull(id);
        return Integer.valueOf(paramId);
    }
}