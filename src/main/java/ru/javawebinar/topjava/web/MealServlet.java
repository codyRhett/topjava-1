package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealMapRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    private MealMapRepository mealMapRepository;

    @Override
    public void init() {
        mealMapRepository = new MealMapRepository();
        List<Meal> meal = MealsUtil.createMealsList();
        meal.forEach(mealTo -> mealMapRepository.save(mealTo));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        String dateTime = request.getParameter("Datetime");
        String description = request.getParameter("Description");
        String calories = request.getParameter("Calories");

        Meal meal;
        if (Objects.equals(id, "0")) {
            meal = new Meal(LocalDateTime.parse(dateTime), description, Integer.parseInt(calories));
            mealMapRepository.save(meal);
        } else {
            meal = new Meal(LocalDateTime.parse(dateTime), description, Integer.parseInt(calories), Integer.parseInt(id));
            mealMapRepository.update(meal);
        }
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        log.debug("redirect to users");
        if (action == null) {
            request.setAttribute("meals", MealsUtil.filteredByCalories(mealMapRepository.getAll(), MealsUtil.MAX_CALORIES_PER_DAY));
            request.getRequestDispatcher("/meals.jsp").forward(request, response);
            return;
        }
        int id = request.getParameter("id") == null ? 0 : Integer.parseInt(request.getParameter("id"));
        Meal meal;
        switch (action) {
            case "delete":
                mealMapRepository.delete(id);
                response.sendRedirect("meals");
                return;
            case "edit":
                meal = mealMapRepository.get(id);
                break;
            case "add":
                meal = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
                break;
            default:
                throw new IllegalStateException("Action " + action + " is illegal");
        }
        request.setAttribute("meal", meal);
        request.getRequestDispatcher(("/edit.jsp")).forward(request, response);
    }
}
