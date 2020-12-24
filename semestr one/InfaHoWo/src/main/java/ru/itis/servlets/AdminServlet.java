package ru.itis.servlets;

import ru.itis.dto.AddCarDto;
import ru.itis.services.addCar.AddCarService;
import ru.itis.services.files.FilesService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/admin")
@MultipartConfig
public class AdminServlet extends HttpServlet {

    private AddCarService addCarService;
    private FilesService filesService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        addCarService = (AddCarService) config.getServletContext().getAttribute("addCarService");
        filesService = (FilesService) config.getServletContext().getAttribute("filesService");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/admin.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AddCarDto addCarDto = new AddCarDto();

        addCarDto.setTitle(req.getParameter("titleCar"));
        addCarDto.setModel(req.getParameter("modelCar"));
        addCarDto.setDescription(req.getParameter("desc"));
        addCarDto.setPrice(Integer.parseInt(req.getParameter("priceCar")));
        addCarDto.setCountry(req.getParameter("countryCar"));

        addCarService.addCar(addCarDto);


        Part part = req.getPart("file");

        filesService.saveFileToStorage(
                part.getInputStream(),
                part.getSubmittedFileName(),
                part.getContentType());

        resp.sendRedirect("/");
    }
}
