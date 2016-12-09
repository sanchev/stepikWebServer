package com.sanchev;

import com.sanchev.dbService.DBService;

public class App {
	
	public static void main(String[] args) {
		DBService dbService = new DBService();
		dbService.printConnectionInfo();
	}
}