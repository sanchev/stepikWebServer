package com.sanchev.db;

import com.sanchev.base.DBService;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.PersistenceException;

public class DBServiceImpl implements DBService {

    private static final String HIBERNATE_SHOW_SQL = "false";
    private static final String HIBERNATE_HBM2DDL_AUTO = "create";

    private final SessionFactory sessionFactory;

    public DBServiceImpl() {
        Configuration configuration;
        configuration = getH2Configuration();
        //configuration = getMySQLConfiguration();
        sessionFactory = createSessionFactory(configuration);
    }

    private SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
        standardServiceRegistryBuilder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = standardServiceRegistryBuilder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private Configuration getH2Configuration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserProfile.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:h2:./h2db");
        configuration.setProperty("hibernate.connection.username", "test");
        configuration.setProperty("hibernate.connection.password", "test");
        configuration.setProperty("hibernate.show_sql", HIBERNATE_SHOW_SQL);
        configuration.setProperty("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
        return configuration;
    }

    @SuppressWarnings("UnusedDeclaration")
    private Configuration getMySQLConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserProfile.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://lamp:3306/test");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.show_sql", HIBERNATE_SHOW_SQL);
        configuration.setProperty("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
        return configuration;
    }

    @Override
    public void addUser(UserProfile userProfile) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UsersDAO usersDAO = new UsersDAO(session);
            usersDAO.insertUser(userProfile);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    @Override
    public UserProfile getUser(String login) throws DBException{
        try {
            Session session = sessionFactory.openSession();
            UsersDAO usersDAO = new UsersDAO(session);
            UserProfile userProfile = usersDAO.getByLogin(login);
            session.close();
            return userProfile;
        } catch (PersistenceException e) {
            throw new DBException(e);
        }
    }
}