package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);


    private MealRestController restController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            restController = appCtx.getBean(MealRestController.class);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String servletPath = request.getServletPath();

        switch (servletPath == null ? "" : servletPath) {
            case "/meals":
                log.info("mealAction");
                restController.mealAction(request.getParameter("id"),
                        request.getParameter("dateTime"),
                        request.getParameter("description"),
                        request.getParameter("calories"));

                response.sendRedirect("meals");
                break;
            case "/login":
                log.info("login");
                response.sendRedirect("meals");
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                restController.mealsActionDelete(request.getParameter("id"));
                response.sendRedirect("meals");
                break;
            case "create":
                request.setAttribute("meal", restController.mealsActionCreate());
                request.getRequestDispatcher("/meal.jsp").forward(request, response);
                break;
            case "update":
                request.setAttribute("meal", restController.mealsActionUpdate(request.getParameter("id")));
                request.getRequestDispatcher("/meal.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("mealsActionAll");
                request.setAttribute("meals",
                        restController.mealsActionAll());
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

}
