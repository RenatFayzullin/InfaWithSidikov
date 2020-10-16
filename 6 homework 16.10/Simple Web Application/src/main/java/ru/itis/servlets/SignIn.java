package ru.itis.servlets;

import ru.itis.bd.DB;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/sign_in")
public class SignIn extends HttpServlet {

    private UsersRepositoryImpl usersRepository;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/sign-in.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("first_name");
        String password = req.getParameter("password");

        Optional<User> user = usersRepository.findUsersByLogin(login);

        if (user.isPresent()) {
            if (user.get().getFirstName().equals(login) &&
                    user.get().getPassword().equals(password)) {
                Cookie cookie = new Cookie("Auth",user.get().getUuid());
                resp.addCookie(cookie);
                resp.sendRedirect("/users");
            }
            else{
                resp.sendRedirect("/sign_in");
            }
        }
        else {
            resp.sendRedirect("/sign_in");
        }

    }

}
