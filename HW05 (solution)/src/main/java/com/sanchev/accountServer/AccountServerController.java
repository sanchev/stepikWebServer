package com.sanchev.accountServer;

public class AccountServerController implements AccountServerControllerMBean {
	
	private AccountServer accountServer;
	
	public AccountServerController(AccountServer accountServer) {
		this.accountServer = accountServer;
	}

	public void setUsersLimit(int usersLimit) {
		accountServer.setUsersLimit(usersLimit);
	}
	
	public int getUsersLimit() {
		return accountServer.getUsersLimit();
	}
}