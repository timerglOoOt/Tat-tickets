<<<<<<< HEAD
package tat_tickets.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/event")
public class CalendarServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        String date = request.getParameter("date");

        // Replace this with your logic to fetch event details based on the date

        String eventDetails = "Event details for " + date;

        // Render event details on the page
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Event Details</title></head><body>");
        out.println("<h2>" + eventDetails + "</h2>");
        out.println("<a href='calendar.html'>Back to Calendar</a>");
        out.println("</body></html>");
    }
}
=======
package tat_tickets.servlets;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import tat_tickets.dto.GameDto;
import tat_tickets.services.CalendarService;
import tat_tickets.services.ProfileService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static tat_tickets.utils.Config.HOMECOMMAND_NAME;

@WebServlet("/calendar")
public class CalendarServlet extends HttpServlet {
    private CalendarService calendarService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        calendarService = (CalendarService) config.getServletContext().getAttribute("calendarService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<GameDto> games = calendarService.getAllGames();

        List<Map<String, String>> matches = new ArrayList<>();
        for (GameDto game : games){
            String formattedDate = dateFormat.format(game.getDate().getTime());
            matches.add(createMatch(formattedDate, HOMECOMMAND_NAME + " vs " + game.getOpposingTeamName(), game.getNumberOfAvailableSeats().toString(), game.getId().toString()));
        }
        req.setAttribute("matches", matches);
        req.getRequestDispatcher("calendar.ftl").forward(req, resp);

    }

    private Map<String, String> createMatch(String date, String teams, String numberOfSeats, String gameId) {
        Map<String, String> match = new HashMap<>();
        match.put("date", date);
        match.put("teams", teams);
        match.put("numberOfSeats", numberOfSeats);
        match.put("url", "event?id=" + gameId);
        return match;
    }

}
>>>>>>> d23f224 (feat: add done project)
