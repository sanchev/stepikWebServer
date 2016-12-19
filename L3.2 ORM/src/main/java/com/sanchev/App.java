package com.sanchev;

import com.sanchev.dbService.DBException;
import com.sanchev.dbService.DBService;
import com.sanchev.dbService.dataSets.UsersDataSet;

public class App {
	
	public static void main(String[] args) {
		DBService dbService = new DBService();
		dbService.printConnectionInfo();
		try {
			String name = "root";
			long userId = dbService.addUser(name);
			System.out.println("Added user id: " + userId);

			UsersDataSet usersDataSet = dbService.getUser(userId);
			System.out.println("User data set: " + usersDataSet);
		} catch (DBException e) {
			e.printStackTrace();
		}
	}
}