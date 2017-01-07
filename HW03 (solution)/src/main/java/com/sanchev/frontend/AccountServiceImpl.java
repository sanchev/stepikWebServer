package com.sanchev.frontend;

import com.sanchev.base.AccountService;
import com.sanchev.base.DBService;
import com.sanchev.db.DBException;
import com.sanchev.db.UserProfile;

public class AccountServiceImpl implements AccountService{
    private final DBService dbService;

    public AccountServiceImpl(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    public void signUp(String login, String password) {
        try {
            dbService.addUser(new UserProfile(login, password));
        } catch (DBException e) {
            System.out.println("Can't sign up: " + e.getMessage());
        }
    }

    @Override
    public boolean signIn(String login, String password) {
        try {
            UserProfile userProfile = dbService.getUser(login);
            return userProfile != null & password.equals(userProfile.getPassword());
        } catch (DBException | NullPointerException e) {
            System.out.println("Can't sign in: " + e.getMessage());
            return false;
        }
    }
}