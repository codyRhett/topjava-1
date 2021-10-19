package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to users");

        List<MealTo> mealsTo = MealsUtil.filteredByStreams(MealTestData.createMealsList(), LocalTime.of(7, 0), LocalTime.of(23, 0), MealTestData.MAX_CALORIESPERDAY);
        request.setAttribute("meals", mealsTo);

        request.getRequestDispatcher("/meals.jsp").forward(request, response);
        //response.sendRedirect("meals.jsp");
    }
}
