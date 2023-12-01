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
