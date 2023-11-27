package tat_tickets.services;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public interface SignOutService {
    void closeSession(HttpServletRequest request);
}
