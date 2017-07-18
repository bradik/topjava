package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImp;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by Brad on 16.07.2017.
 */
public class MealController extends HttpServlet {
    private static final Logger log = getLogger(MealController.class);

    private static String INSERT_OR_EDIT = "/mealItem.jsp";
    private static String LIST_USER = "/mealsList.jsp";

    private MealDao dao;

    public MealController() {

        dao = new MealDaoImp();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("listItem")) {

            forward = LIST_USER;
            request.setAttribute("mealsList", dao.getAllItems());
            request.setAttribute("dateFormater", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        } else if (action.equalsIgnoreCase("delete")) {
            int id  = Integer.parseInt(request.getParameter("id"));
            dao.deleteItem(id);

            request.setAttribute("mealsList", dao.getAllItems());
            request.setAttribute("dateFormater", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            forward = LIST_USER;

        } else if (action.equalsIgnoreCase("new")) {

            forward = INSERT_OR_EDIT;

        } else if (action.equalsIgnoreCase("edit")) {

            int id  = Integer.parseInt(request.getParameter("id"));
            Meal item  = dao.getItem(id);
            request.setAttribute("item",item);
            request.setAttribute("localDateTimeFormat", new SimpleDateFormat("yyyy-MM-dd'T'hh:mm"));

            forward = INSERT_OR_EDIT;
        }

        request.getRequestDispatcher(forward).forward(request, response);

        log.debug("doGet: redirect to " + forward);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String forward = LIST_USER;

        Meal item;

        if (request.getParameter("id").isEmpty()){
            //new item
            item = new Meal(
                    LocalDateTime.now(),
                    request.getParameter("description"),
                    Integer.parseInt(request.getParameter("calories"))
            );

            dao.addItem(item);

        } else {
            int id = Integer.parseInt(request.getParameter("id"));

            item = dao.getItem(id);

            item.setDescription(request.getParameter("description"));
            item.setCalories(Integer.parseInt(request.getParameter("calories")));

            dao.updateItem(item);
        }

        request.setAttribute("mealsList", dao.getAllItems());
        request.setAttribute("dateFormater", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        request.getRequestDispatcher(forward).forward(request, response);

        log.debug("doPost: redirect to " + forward);
    }
}
