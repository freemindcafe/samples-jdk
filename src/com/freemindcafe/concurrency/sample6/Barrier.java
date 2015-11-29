package com.freemindcafe.concurrency.sample6;

import java.util.concurrent.Semaphore;

public class Barrier {
	
	private int nThreads;
	private int counter;
	private Semaphore semaphore = new Semaphore(0);
	private Semaphore mutex = new Semaphore(1);
	
	public Barrier(int nThreads) {
		this.counter = nThreads;
		this.nThreads = nThreads;
	}
	
	public void await() throws InterruptedException{
		mutex.acquire();
		counter--;
		mutex.release();
		if(counter == 0){
			semaphore.release(nThreads);
		}
		semaphore.acquire();
	}

}
