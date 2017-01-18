package com.sanchev.accountServer;

public class AccountServerController implements AccountServerControllerMBean {
    private final AccountServer accountServer;

    public AccountServerController(AccountServer accountServer) {
        this.accountServer = accountServer;
    }

    @Override
    public int getUsersCount() {
        return accountServer.getUsersCount();
    }

    @Override
    public int getUsersLimit() {
        return accountServer.getUsersLimit();
    }

    @Override
    public void setUsersLimit(int usersLimit) {
        accountServer.setUsersLimit(usersLimit);
    }
}