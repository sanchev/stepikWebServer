package com.sanchev.dbService.executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {
    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void execUpdate(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(sql);
        statement.close();
    }

    public <T> T execQuery(String sql, ResultHandler<T> resultHandler) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(sql);
        ResultSet resultSet = statement.getResultSet();
        T value = resultHandler.handle(resultSet);
        resultSet.close();
        statement.close();
        return value;
    }
}