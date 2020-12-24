package ru.itis.servlets;

import ru.itis.services.cars.CarsService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/car")
public class CarServlet extends HttpServlet {

    private CarsService carsService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        carsService = (CarsService) config.getServletContext().getAttribute("carsService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer currentCarId = Integer.parseInt(req.getParameter("carId"));

        req.setAttribute("currentCar", carsService.getById(currentCarId));

        req.getRequestDispatcher("/jsp/car.jsp").forward(req, resp);
    }
}
