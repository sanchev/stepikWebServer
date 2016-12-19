package com.sanchev.dbService.dao;

import com.sanchev.dbService.dataSets.UsersDataSet;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class UsersDAO {

    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public long insertUser(String name) throws HibernateException {
        return (Long) session.save(new UsersDataSet(name));
    }

    public UsersDataSet get(long userId) throws HibernateException {
        return session.get(UsersDataSet.class, userId);
    }
}