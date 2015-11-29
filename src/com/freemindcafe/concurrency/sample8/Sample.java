package com.freemindcafe.concurrency.sample8;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Sample {
	
	//ScheduledExecutorService's thread don't terminate by itself
	//It is a non daemon thread and will prevent the jvm from existing
	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		service.schedule(()->{System.out.println("hi");}, 5, TimeUnit.SECONDS);
		service.shutdown();
		System.out.println("after thread pool shutdown");

	}

}
