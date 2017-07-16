package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImp;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

            forward = LIST_USER;
            request.setAttribute("mealsList", dao.getAllItems());
            request.setAttribute("dateFormater", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        } else if (action.equalsIgnoreCase("new")) {

            forward = INSERT_OR_EDIT;

        } else if (action.equalsIgnoreCase("edit")) {

            forward = INSERT_OR_EDIT;
        }

        request.getRequestDispatcher(forward).forward(request, response);

        log.debug("redirect to " + forward);
    }
}
