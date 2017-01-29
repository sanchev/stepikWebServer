package com.sanchev.threads;

public class SerialSequenceExample extends Thread {
	private static int currentMax = 1;
	private int mainId;
	private final static Object waitObject = new Object();

	public SerialSequenceExample(int id) {
		this.mainId = id;
	}

	public void run() {
		try {
			System.out.println("Run: " + mainId);
			synchronized (waitObject) {
				while (mainId >= currentMax) {
					waitObject.wait();
					System.out.println("Awakened: " + mainId);
				}
				currentMax++;
				System.out.println("Finished: " + mainId);
				waitObject.notifyAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}