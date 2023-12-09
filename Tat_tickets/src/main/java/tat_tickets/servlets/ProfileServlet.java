package tat_tickets.servlets.profile;

import tat_tickets.dto.BookingDto;
import tat_tickets.dto.UserDto;
import tat_tickets.models.FileInfo;
import tat_tickets.models.User;
import tat_tickets.services.ProfileService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@WebServlet("/profile")
@MultipartConfig
public class ProfileServlet extends HttpServlet {
    private ProfileService profileService;
    private List<BookingDto> activeBookings;
    private List<BookingDto> expiredBookings;
    private int countOfError;

    @Override
    public void init(ServletConfig config) throws ServletException {
        profileService = (ProfileService) config.getServletContext().getAttribute("profileService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user = (UserDto) req.getSession().getAttribute("user");
        UserDto userToAttribute = profileService.getUserByEmail(user.getEmail());

        if (req.getSession().getAttribute("error") != null && countOfError != 1){
            countOfError = 1;
        } else if (countOfError == 1) {
            req.getSession().removeAttribute("error");
            countOfError = 0;
        }

        req.setAttribute("user", Objects.requireNonNullElse(userToAttribute, user));

        List<BookingDto> allBookings = profileService.getBookingsByUserId(user.getId());

        activeBookings = new ArrayList<>();
        expiredBookings = new ArrayList<>();

        Date currentDate = new Date();

        for (BookingDto booking : allBookings) {
            if (booking.getBookingDate().after(currentDate)) {
                activeBookings.add(booking);
            } else {
                expiredBookings.add(booking);
            }
        }

        req.setAttribute("activeBookings", activeBookings);
        req.setAttribute("expiredBookings", expiredBookings);

        req.getRequestDispatcher("profile.ftl").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("changeProfile".equals(action)) {
            changeProfile(req, resp);
        } else if ("changeAvatar".equals(action)) {
            changeAvatar(req, resp);
        }
    }

    private void changeProfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user = (UserDto) req.getSession().getAttribute("user");
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));

        profileService.updateProfile(user);

        req.setAttribute("activeBookings", activeBookings);
        req.setAttribute("expiredBookings", expiredBookings);


        req.getRequestDispatcher("profile.ftl").forward(req, resp);
    }

    private void changeAvatar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

}
