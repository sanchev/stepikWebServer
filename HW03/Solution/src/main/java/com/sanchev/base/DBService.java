package com.sanchev.base;

import com.sanchev.db.DBException;
import com.sanchev.db.UserProfile;

public interface DBService {
    void addUser(UserProfile userProfile) throws DBException;
    UserProfile getUser(String login) throws DBException;
}