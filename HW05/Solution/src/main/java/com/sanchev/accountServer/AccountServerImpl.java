package com.sanchev.accountServer;

public class AccountServerImpl implements AccountServer {
	private static AccountServerImpl instance;
	private static final int DEFAULT_USERS_LIMIT = 10;
	private int usersLimit;
	
	public static AccountServerImpl getInstance() {
		if (instance == null)
			instance = new AccountServerImpl();
		return instance;
	}
	
	private AccountServerImpl() {
		this.usersLimit = DEFAULT_USERS_LIMIT;
	}
	
	public void setUsersLimit(int usersLimit) {
		this.usersLimit = usersLimit;
	}
	
	public int getUsersLimit() {
		return usersLimit;
	}
}