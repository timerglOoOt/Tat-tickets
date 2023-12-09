package tat_tickets.servlets;

import tat_tickets.dto.SectionDto;
import tat_tickets.services.CalendarService;
import tat_tickets.services.EventService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/event")
public class EventServlet extends HttpServlet {
    private EventService eventService;


    @Override
    public void init(ServletConfig config) throws ServletException {
        eventService = (EventService) config.getServletContext().getAttribute("eventService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("id", req.getParameter("id"));
        List<SectionDto> sections = eventService.getAllSections();
        List<String> sectionString = new ArrayList<>();
        for (SectionDto section : sections){
            sectionString.add("Секция " + section.getId());
        }

        req.setAttribute("sections", sectionString.toArray());
        req.getRequestDispatcher("event.ftl").forward(req, resp);
    }
}

