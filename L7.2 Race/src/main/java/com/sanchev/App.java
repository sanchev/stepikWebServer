package com.sanchev;

import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class App {
	private static final int THREADS_COUNT = 2;

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(THREADS_COUNT);
		
		CallCounter callCounter = new CallCounter();
		AtomicInteger realCallCounter = new AtomicInteger();
		
		RaceExample callableOne = new RaceExample(callCounter, realCallCounter);
		RaceExample callableTwo = new RaceExample(callCounter, realCallCounter);
		
		long startTime = (new Date()).getTime();
		
		Future<Integer> futureOne = executor.submit(callableOne);
		Future<Integer> futureTwo = executor.submit(callableTwo);
		
		System.out.println("Future 1: " + futureOne.get());
		System.out.println("Future 2: " + futureTwo.get());
		System.out.println("Real Call Counter: " + realCallCounter);
		
		long finishTime = (new Date()).getTime();
		System.out.println("Time spent: " + (finishTime - startTime));
		executor.shutdown();
	}
}