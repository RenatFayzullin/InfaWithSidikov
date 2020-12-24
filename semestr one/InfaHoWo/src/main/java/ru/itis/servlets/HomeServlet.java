package ru.itis.servlets;

import com.google.gson.Gson;
import ru.itis.dto.CarDto;
import ru.itis.services.cars.CarsService;
import ru.itis.services.files.FilesService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("")
public class HomeServlet extends HttpServlet {

    private CarsService carsService;
    private List<CarDto> cars;

    @Override
    public void init(ServletConfig config) throws ServletException {
        carsService = (CarsService) config.getServletContext().getAttribute("carsService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cars = carsService.getAll();
        req.setAttribute("cars", cars);
        req.getRequestDispatcher("/jsp/main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("logout") != null) {
            req.getSession().removeAttribute("auth");
            req.getSession().removeAttribute("user");
            resp.sendRedirect("/");
        } else if (req.getParameter("curStr") != null) {
            Gson gson = new Gson();
            String element = gson.toJson(carsService.getFitCars(req.getParameter("curStr")).get());
            PrintWriter printWriter = resp.getWriter();
            printWriter.write(element);
        }

    }
}
