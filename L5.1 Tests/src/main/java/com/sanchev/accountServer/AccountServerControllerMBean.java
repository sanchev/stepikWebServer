package com.sanchev.accountServer;

@SuppressWarnings("UnusedDeclaration")
public interface AccountServerControllerMBean {
    public int getUsersCount();

    public int getUsersLimit();

    public void setUsersLimit(int usersLimit);
}