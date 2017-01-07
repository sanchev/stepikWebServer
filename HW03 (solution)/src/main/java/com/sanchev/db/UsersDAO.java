package com.sanchev.db;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class UsersDAO {

    private final Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public void insertUser(UserProfile userProfile) throws HibernateException {
        session.save(userProfile);
    }

    public UserProfile getByLogin(String login) throws PersistenceException {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<UserProfile> cq = cb.createQuery(UserProfile.class);
        Root<UserProfile> from = cq.from(UserProfile.class);
        cq.where(cb.equal(from.get("login"), login));
        TypedQuery<UserProfile> q = session.createQuery(cq);
        return q.getSingleResult();
    }
}