package com.sanchev.accountServer;

public interface AccountServerControllerMBean {
	public void setUsersLimit(int usersLimit);
	
	public int getUsersLimit();
}