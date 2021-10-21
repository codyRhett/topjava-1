package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.storage.MyRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    MyRepository myRepository;

    @Override
    public void init() {
        myRepository = new MyRepository();
        List<Meal> meal = MealTestData.createMealsList();
        meal.forEach(mealTo -> {
            myRepository.save(mealTo);
        });
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String dateTime = request.getParameter("Datetime");
        String description = request.getParameter("Description");
        String calories = request.getParameter("Calories");

        Meal meal = new Meal(LocalDateTime.parse(dateTime), description, Integer.parseInt(calories), uuid);
        myRepository.update(meal);
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String uuid = request.getParameter("uuid");
        Meal meal;
        log.debug("redirect to users");

        if (action == null) {
            request.setAttribute("meals", MealsUtil.filteredByStreams(myRepository.getAll(), LocalTime.of(7, 0), LocalTime.of(23, 0), MealTestData.MAX_CALORIESPERDAY));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
            return;
        }

        switch (action) {
            case "delete":
                myRepository.delete(uuid);
                response.sendRedirect("meals");
                return;
            case "edit":
                meal = myRepository.get(uuid);
                break;
            case "add":
                meal = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500, UUID.randomUUID().toString());
                myRepository.save(meal);
                break;
            default:
                throw new IllegalStateException("Action " + action + " is illegal");
        }
        request.setAttribute("meal", meal);
        request.getRequestDispatcher(("/edit.jsp")).forward(request, response);
    }
}
