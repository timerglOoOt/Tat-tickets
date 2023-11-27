//package tat_tickets.filters;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.logging.LogRecord;
//
//@WebFilter("/*")
//public class AuthFilter implements Filter {
//
//
//    public void init(FilterConfig filterConfig) throws ServletException {}
//
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        // преобразуем запросы к нужному виду
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        if(request.getRequestURI().contains("resources")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        // берем сессию у запроса
//        // берем только существующую, если сессии не было - то вернет null
//        HttpSession session = request.getSession(false);
//        // флаг, аутентифицирован ли пользователь
//        boolean isAuthenticated = false;
//        // существует ли сессия вообще?
//        boolean sessionExists = session != null;
//        // идет ли запрос на страницу входа или регистрации?
//        boolean isRequestOnAuthPage = request.getRequestURI().contains("sign-in") ||
//                request.getRequestURI().contains("sign-up");
//
//        // если сессия есть
//        if (sessionExists) {
//            // проверим, есть ли атрибует user?
//            isAuthenticated = session.getAttribute("user") != null;
//        }
//
//        // если авторизован и запрашивает не открытую страницу или если не авторизован и запрашивает открытую
//        if (isAuthenticated && !isRequestOnAuthPage || !isAuthenticated && isRequestOnAuthPage) {
//            // отдаем ему то, что он хочет
//            filterChain.doFilter(request, response);
//        } else if (isAuthenticated && isRequestOnAuthPage) {
//            // пользователь аутенцифицирован и запрашивает страницу входа
//            // - отдаем ему профиль
//            response.sendRedirect("profile");
//        } else {
//            // если пользователь не аутенцицицирован и запрашивает другие страницы
//            response.sendRedirect("sign-in");
//        }
//    }
//
//    public void destroy() {}
//}