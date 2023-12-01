package tat_tickets.servlets;

import tat_tickets.dto.SignInForm;
import tat_tickets.dto.UserDto;
import tat_tickets.services.SignInService;
import tat_tickets.utils.exceptions.TicketsException;
import tat_tickets.utils.exceptions.ValidationException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-in")
public class SignInServlet extends HttpServlet {
    private SignInService signInService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        signInService = (SignInService) config.getServletContext().getAttribute("signInService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("sign-in.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            SignInForm form = SignInForm.builder()
                    .email(req.getParameter("email"))
                    .password(req.getParameter("password"))
                    .build();
            UserDto user = signInService.signIn(form);
            req.getSession().setAttribute("user", user);
        } catch (TicketsException e) {
//            req.setAttribute("errorMessage", e.getMessage());
            resp.sendRedirect("sign-in");
        }
        resp.sendRedirect("profile");
    }
}
