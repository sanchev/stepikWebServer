package com.sanchev.threads;

public class RandomSequenceExample extends Thread {
	public void run() {
		System.out.println("Run: " + this.getName());
	};
}