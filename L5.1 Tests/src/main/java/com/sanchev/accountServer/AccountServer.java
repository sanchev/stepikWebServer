package com.sanchev.accountServer;

public interface AccountServer {
    void removeUser();

    int getUsersLimit();

    int getUsersCount();

    void addNewUser();

    void setUsersLimit(int usersLimit);
}