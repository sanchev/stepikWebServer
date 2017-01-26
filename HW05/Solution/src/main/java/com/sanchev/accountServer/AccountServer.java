package com.sanchev.accountServer;

public interface AccountServer {
	void setUsersLimit(int usersLimit);
	
	int getUsersLimit();
}