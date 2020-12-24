package ru.itis.servlets;

import ru.itis.dto.SignInForm;
import ru.itis.dto.UserDto;
import ru.itis.services.signIn.SignInService;


import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {

    private SignInService signInService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        signInService = (SignInService) config.getServletContext().getAttribute("signInService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/sign-in.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignInForm form = new SignInForm();

        form.setEmail(req.getParameter("email"));
        form.setPassword(req.getParameter("password"));

        Optional<UserDto> user = signInService.signIn(form);

        if (user.isPresent()) {
            HttpSession session = req.getSession();
            session.setAttribute("auth", true);
            session.setAttribute("user", user.get());
            resp.sendRedirect("/profile");
        } else {
            resp.sendRedirect("/sign-in");
        }
    }
}