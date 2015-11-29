package com.freemindcafe.concurrency.sample2;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

public class Test {
	
	@org.junit.Test
	public void simple_pool_with_callable() throws InterruptedException, ExecutionException{
		Callable<Integer> c1 = ()->1;
		Callable<Integer> c2 = ()->1;
		
		ExecutorService executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.HOURS, new LinkedBlockingQueue<Runnable>());
		Future<Integer> f1 = executor.submit(c1);
		Future<Integer> f2 = executor.submit(c2);
		executor.shutdown();
		System.out.println(executor.awaitTermination(1, TimeUnit.MINUTES));
		
		Assert.assertEquals(2, f1.get()+f2.get());
		
	}
	
	@org.junit.Test
	public void invoke_all() throws InterruptedException, ExecutionException{
		Callable<Integer> c1 = ()->1;
		Callable<Integer> c2 = ()->1;
		
		ExecutorService executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.HOURS, new LinkedBlockingQueue<Runnable>());
		List<Future<Integer>> results = executor.invokeAll(Arrays.asList(c1,c2));
		executor.shutdown();
		System.out.println(executor.awaitTermination(1, TimeUnit.MINUTES));
		
		Assert.assertEquals(2, results.stream().mapToInt(f->{try{return f.get();}catch(Exception ex){return -1;}}).sum());
		
	}
	
	//[FIXME]
	//Why does awaitTermination wait for 10 seconds
	@org.junit.Test
	public void test3() throws InterruptedException, ExecutionException{
		Callable<Integer> c1 = () -> {Thread.sleep(10000);return 1;};
		Callable<Integer> c2 = ()->1;
		
		ExecutorService executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.HOURS, new LinkedBlockingQueue<Runnable>());
		List<Future<Integer>> results = executor.invokeAll(Arrays.asList(c1,c2));
		executor.shutdown();
		System.out.println(executor.awaitTermination(1, TimeUnit.SECONDS));
		System.out.println("hi");
		
		Assert.assertEquals(2, results.stream().mapToInt(f->{try{return f.get();}catch(Exception ex){return -1;}}).sum());
		
	}

}
