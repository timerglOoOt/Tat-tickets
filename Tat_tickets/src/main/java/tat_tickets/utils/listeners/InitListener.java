<<<<<<< HEAD
package tat_tickets.utils.listeners;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import tat_tickets.dao.UserRepository;
import tat_tickets.dao.impl.UserRepositoryImpl;
import tat_tickets.services.PasswordEncoder;
import tat_tickets.services.SignInService;
import tat_tickets.services.SignOutService;
import tat_tickets.services.SignUpService;
import tat_tickets.services.impl.*;
import tat_tickets.services.validation.Validator;
import tat_tickets.utils.ConnectionProvider;
import tat_tickets.utils.exceptions.DbException;
import tat_tickets.utils.mappers.UserMapper;
import tat_tickets.utils.mappers.impl.UserMapperImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.DriverManager;

@WebListener
public class InitListener implements ServletContextListener {
    private ConnectionProvider connectionProvider;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
             connectionProvider = ConnectionProvider.getInstance();
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(connectionProvider.getDRIVER());
        driverManagerDataSource.setPassword(connectionProvider.getPASS());
        driverManagerDataSource.setUrl(connectionProvider.getHOST());
        driverManagerDataSource.setUsername(connectionProvider.getPASS());

        PasswordEncoder passwordEncoder = new PasswordEncoderImpl();
        UserMapper userMapper = new UserMapperImpl();
        UserRepository userRepository = new UserRepositoryImpl(driverManagerDataSource);
        Validator validator = new ValidatorImpl(userRepository);
        SignInService signInService = new SignInServiceImpl(userRepository, passwordEncoder);
        SignUpService signUpService = new SignUpServiceImpl(userRepository, passwordEncoder, validator);
        SignOutService signOutService = new SignOutServiceImpl();

        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("userRepository", userRepository);
        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("signOutService", signOutService);
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
}
=======
package tat_tickets.utils.listeners;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import tat_tickets.dao.*;
import tat_tickets.dao.impl.*;
import tat_tickets.services.*;
import tat_tickets.services.impl.*;
import tat_tickets.services.validation.Validator;
import tat_tickets.utils.ConnectionProvider;
import tat_tickets.utils.exceptions.DbException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitListener implements ServletContextListener {
    private ConnectionProvider connectionProvider;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
             connectionProvider = ConnectionProvider.getInstance();
        } catch (DbException e) {
            throw new RuntimeException(e);
        }
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(connectionProvider.getDRIVER());
        driverManagerDataSource.setPassword(connectionProvider.getPASS());
        driverManagerDataSource.setUrl(connectionProvider.getHOST());
        driverManagerDataSource.setUsername(connectionProvider.getUSER());

        PasswordEncoder passwordEncoder = new PasswordEncoderImpl();
        UserRepository userRepository = new UserRepositoryImpl(driverManagerDataSource);
        FileInfoRepository fileInfoRepository = new FileInfoRepositoryImpl(driverManagerDataSource);
        GameRepository gameRepository = new GameRepositoryImpl(driverManagerDataSource);
        SectionRepository sectionRepository = new SectionRepositoryImpl(driverManagerDataSource);
        SeatRepository seatRepository = new SeatRepositoryImpl(driverManagerDataSource);
        TicketRepository ticketRepository = new TicketRepositoryImpl(driverManagerDataSource);
        BookingRepository bookingRepository = new BookingRepositoryImpl(driverManagerDataSource);
        Validator validator = new ValidatorImpl(userRepository);
        SignInService signInService = new SignInServiceImpl(userRepository, passwordEncoder);
        SignUpService signUpService = new SignUpServiceImpl(userRepository, passwordEncoder, validator);
        SignOutService signOutService = new SignOutServiceImpl();
        ProfileService profileService = new ProfileServiceImpl(userRepository, fileInfoRepository, bookingRepository);
        CalendarService calendarService = new CalendarServiceImpl(gameRepository);
        EventService eventService = new EventServiceImpl(sectionRepository, seatRepository, ticketRepository, bookingRepository, gameRepository);
        AvatarService avatarService = new AvatarServiceImpl(userRepository, fileInfoRepository);

        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("userRepository", userRepository);
        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("signOutService", signOutService);
        servletContext.setAttribute("profileService", profileService);
        servletContext.setAttribute("calendarService", calendarService);
        servletContext.setAttribute("eventService", eventService);
        servletContext.setAttribute("avatarService", avatarService);

    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {}
}
>>>>>>> d23f224 (feat: add done project)
