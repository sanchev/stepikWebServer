package com.sanchev;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RaceExample implements Callable<Integer> {
	private CallCounter callCounter;
	private AtomicInteger realCallCounter;
	private static final int HUNDRED_MILLION = 100_000_000;
	
	public RaceExample(CallCounter callCounter, AtomicInteger realCallCounter) {
		this.callCounter = callCounter ;
		this.realCallCounter = realCallCounter;
	}
	
	@Override
	public Integer call() {
		while (realCallCounter.incrementAndGet() < HUNDRED_MILLION) {
			callCounter.increment();
		}
		return callCounter.getCount();
	}
}