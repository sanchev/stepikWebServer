package com.sanchev.accountServer;

@SuppressWarnings("UnusedDeclaration")
public interface AccountServerControllerMBean {
	void setUsersLimit(int usersLimit);

	int getUsersLimit();
}