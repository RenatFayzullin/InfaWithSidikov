package ru.itis.servlets;


import ru.itis.bd.DB;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/registration")
public class Registration extends HttpServlet {

    UsersRepositoryImpl usersRepository;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
        try {
            usersRepository = new UsersRepositoryImpl(
                    DriverManager.getConnection(DB.DB_URL, DB.DB_USER, DB.DB_PASSWORD)
            );
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/registration.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String password = request.getParameter("password");
        Integer age = Integer.parseInt(request.getParameter("age"));
//        System.out.println(firstName + " " + lastName + " " + age + " " + password);
        User newUser = new User(firstName, lastName, password, age);
        usersRepository.save(newUser);
        response.sendRedirect("/users");
    }
}
