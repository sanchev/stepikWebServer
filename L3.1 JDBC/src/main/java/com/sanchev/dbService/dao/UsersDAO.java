package com.sanchev.dbService.dao;

import com.sanchev.dbService.dataSets.UsersDataSet;
import com.sanchev.dbService.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

public class UsersDAO {

    private Executor executor;

    public UsersDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public void createTable() throws SQLException {
        executor.execUpdate("create table if not exists users (id bigint auto_increment, user_name varchar(256), primary key (id))");
    }

    public void insertUser(String name) throws SQLException {
        executor.execUpdate("insert into users (user_name) values ('" + name + "')");
    }

    public long getUserId(String name) throws SQLException {
        return executor.execQuery("select * from users where user_name='" + name + "'", resultSet -> {
            resultSet.next();
            return resultSet.getLong(1);
        });
    }

    public void dropTable() throws SQLException {
        executor.execUpdate("drop table users");
    }

    public UsersDataSet get(long userId) throws SQLException {
        return executor.execQuery("select * from users where id=" + userId, resultSet -> {
            resultSet.next();
            return new UsersDataSet(resultSet.getLong(1), resultSet.getString(2));
        });
    }
}