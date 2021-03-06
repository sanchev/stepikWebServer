package com.sanchev.dbService;

import com.sanchev.dbService.dao.UsersDAO;
import com.sanchev.dbService.dataSets.UsersDataSet;
import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {
    private final Connection connection;

    public DBService() {
        //this.connection = getH2Connection();
        this.connection = getMysqlConnection();
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.cj.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:mysql://").    //DB type
                    append("lamp:").            //Host
                    append("3306/").            //Port
                    append("test?").            //DB name
                    append("user=root&").       //Login
                    append("password=root");    //Password

            System.out.println("URL: " + url);

            //Connect to DB
            return DriverManager.getConnection(url.toString());
        } catch (SQLException | InstantiationException | ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Connection getH2Connection() {
        try {
            String url = "jdbc:h2:./h2db";
            String user = "root";
            String password = "root";

            //Create DB
            JdbcDataSource dataSource = new JdbcDataSource();
            dataSource.setURL(url);
            dataSource.setUser(user);
            dataSource.setPassword(password);

            //Connect to DB
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void printConnectionInfo() {
        try {
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public long addUser(String name) throws DBException {
        try {
            connection.setAutoCommit(false);
            UsersDAO usersDAO = new UsersDAO(connection);
            usersDAO.createTable();
            usersDAO.insertUser(name);
            connection.commit();
            return usersDAO.getUserId(name);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignore) {}
            throw new DBException(e);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ignore) {}
        }
    }

    public void cleanUp() throws DBException {
        try {
            new UsersDAO(connection).dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public UsersDataSet getUser(long userId) throws DBException {
        try {
            return (new UsersDAO(connection).get(userId));
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }
}