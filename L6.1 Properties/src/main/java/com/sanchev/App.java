package com.sanchev;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class App {
	public static void main(String[] args) {
		Properties properties = new Properties();
		try (InputStream input = new FileInputStream("config.properties")) {
			properties.load(input);
			
			System.out.println(properties.getProperty("database"));
			System.out.println(properties.getProperty("dbuser"));
			System.out.println(properties.getProperty("dbpassword"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}