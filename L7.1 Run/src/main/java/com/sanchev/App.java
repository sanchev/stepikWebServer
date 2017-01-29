package com.sanchev;

import com.sanchev.threads.RandomSequenceExample;
import com.sanchev.threads.SerialSequenceExample;

public class App{
	private static final int THREADS_CONT = 10;

	public static void main(String[] args) {
		for (int i = 0; i < THREADS_CONT; i++) {
			//Thread thread = new RandomSequenceExample();
			Thread thread = new SerialSequenceExample(i);
			//System.out.println("Start: " + thread.getName());
			thread.start();
		}
	}
}