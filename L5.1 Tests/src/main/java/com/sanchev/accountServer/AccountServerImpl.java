package com.sanchev.accountServer;

public class AccountServerImpl implements AccountServer {
    private int usersCount;
    private int usersLimit;

    public AccountServerImpl(int usersLimit) {
        this.usersCount = 0;
        this.usersLimit = usersLimit;
    }

    @Override
    public void removeUser() {
        usersCount--;
    }

    @Override
    public int getUsersLimit() {
        return usersLimit;
    }

    @Override
    public int getUsersCount() {
        return usersCount;
    }

    @Override
    public void addNewUser() {
        usersCount++;
    }

    @Override
    public void setUsersLimit(int usersLimit) {
        this.usersLimit = usersLimit;
    }
}