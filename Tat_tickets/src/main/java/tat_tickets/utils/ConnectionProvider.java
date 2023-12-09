<<<<<<< HEAD
package tat_tickets.utils;

import lombok.Getter;
import tat_tickets.utils.exceptions.DbException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
public class ConnectionProvider {
    private final String HOST = "jdbc:postgresql://localhost:5432/tat_tickets";
    private final String USER = "postgres";
    private final String PASS = "postgres";
    private final String DRIVER = "org.postgresql.Driver";
    private static ConnectionProvider _instance;

    public static ConnectionProvider getInstance() throws DbException {
        if(_instance == null){
            _instance = new ConnectionProvider();
        }
        return _instance;
    }

    private Connection con;

    private ConnectionProvider() throws DbException {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            con = DriverManager.getConnection(HOST, USER, PASS);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new DbException("Can't connect to DB.", e);
        }
    }

    public Connection getCon() {
        return con;
    }
}
=======
package tat_tickets.utils;

import lombok.Getter;
import tat_tickets.utils.exceptions.DbException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
public class ConnectionProvider {
    private final String HOST = "jdbc:postgresql://localhost:5432/tat_tickets";
    private final String USER = "postgres";
    private final String PASS = "postgres";
    private final String DRIVER = "org.postgresql.Driver";
    private static ConnectionProvider _instance;

    public static ConnectionProvider getInstance() throws DbException {
        if(_instance == null){
            _instance = new ConnectionProvider();
        }
        return _instance;
    }

    private Connection con;

    private ConnectionProvider() throws DbException {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            con = DriverManager.getConnection(HOST, USER, PASS);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw new DbException("Can't connect to DB.", e);
        }
    }

    public Connection getCon() {
        return con;
    }
}
>>>>>>> d23f224 (feat: add done project)
