package com.freemindcafe.concurrency.sample1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

public class Test {
	
	@org.junit.Test
	public void simple_pool_having_runnable() throws InterruptedException{
		
		List<Integer> list = new ArrayList<>();
		
		Runnable r1 = () -> list.add(1);
		Runnable r2 = () -> list.add(1);
		
		ExecutorService executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.HOURS, new LinkedBlockingQueue<Runnable>());
		executor.execute(r1);
		executor.execute(r2);
		executor.shutdown();
		executor.awaitTermination(1, TimeUnit.HOURS);
		
		Assert.assertEquals(2, list.stream().mapToInt(Integer::intValue).sum());
		
	}
	
	//If you run it multiple times (4-5), it will fail in one or two runs.
	//We should not use non thread safe collections.
	@org.junit.Test
	public void pool_having_multiple_threads_random_behaviour_as_list_is_unsynchronized() throws InterruptedException{
		
		List<Integer> list = new ArrayList<>();
		Set<Long> threadIds = new HashSet<>();
		
		Runnable r1 = () -> {list.add(1);threadIds.add(Thread.currentThread().getId());};
		Runnable r2 = () -> {list.add(1);threadIds.add(Thread.currentThread().getId());};
		
		ExecutorService executor = new ThreadPoolExecutor(5, 100, 0, TimeUnit.HOURS, new LinkedBlockingQueue<Runnable>());
		executor.execute(r1);
		executor.execute(r2);
		executor.shutdown();
		Assert.assertTrue(executor.awaitTermination(1, TimeUnit.HOURS));
		
		Assert.assertNotNull(list.get(0));
		Assert.assertNotNull(list.get(1));
		Assert.assertEquals(2, list.stream().mapToInt(Integer::intValue).sum());
		Assert.assertEquals(2, threadIds.size());
		
	}
	
	//Using thread safe collection
	@org.junit.Test
	public void pool_having_multiple_threads_success_as_list_is_copy_on_write() throws InterruptedException{
		
		List<Integer> list = new CopyOnWriteArrayList<>();
		Set<Long> threadIds = new HashSet<>();
		
		Runnable r1 = () -> {list.add(1);threadIds.add(Thread.currentThread().getId());};
		Runnable r2 = () -> {list.add(1);threadIds.add(Thread.currentThread().getId());};
		
		ExecutorService executor = new ThreadPoolExecutor(5, 100, 0, TimeUnit.HOURS, new LinkedBlockingQueue<Runnable>());
		executor.execute(r1);
		executor.execute(r2);
		executor.shutdown();
		Assert.assertTrue(executor.awaitTermination(1, TimeUnit.HOURS));
		
		Assert.assertNotNull(list.get(0));
		Assert.assertNotNull(list.get(1));
		Assert.assertEquals(2, list.stream().mapToInt(Integer::intValue).sum());
		Assert.assertEquals(2, threadIds.size());
		
	}
	
	//Thread safe collection
	@org.junit.Test
	public void pool_having_multiple_threads_success_as_list_is_synchronized() throws InterruptedException{
		
		List<Integer> list = Collections.synchronizedList(new ArrayList<>());
		Set<String> threadNames = new HashSet<>();
		
		Runnable r1 = () -> {list.add(1);try{Thread.sleep(1000);}catch(Exception ex){}threadNames.add(Thread.currentThread().getName());};
		Runnable r2 = () -> {list.add(1);threadNames.add(Thread.currentThread().getName());};
		
		ExecutorService executor = new ThreadPoolExecutor(5, 100, 0, TimeUnit.HOURS, new LinkedBlockingQueue<Runnable>());
		executor.execute(r1);
		executor.execute(r2);
		executor.shutdown();
		Assert.assertTrue(executor.awaitTermination(1, TimeUnit.HOURS));
		
		Assert.assertNotNull(list.get(0));
		Assert.assertNotNull(list.get(1));
		Assert.assertEquals(2, list.stream().mapToInt(Integer::intValue).sum());
		Assert.assertEquals(2, threadNames.stream().filter(s->s.startsWith("pool-")).count());
		Assert.assertEquals(0, threadNames.stream().filter(s->!s.startsWith("pool-")).count());
		
	}	
	
	@org.junit.Test(expected=RejectedExecutionException.class)
	public void in_absebse_of_buffer_second_task_is_rejected() throws InterruptedException{
		
		List<Integer> list = new ArrayList<>();
		Set<Long> threadIds = new HashSet<>();
		
		Runnable r1 = () -> {list.add(1);try {
			Thread.sleep(2000);
			threadIds.add(Thread.currentThread().getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}};
		Runnable r2 = () -> {list.add(1);try {
			Thread.sleep(2000);
			threadIds.add(Thread.currentThread().getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}};
		
		ExecutorService executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.HOURS, new SynchronousQueue<Runnable>());
		executor.execute(r1);
		executor.execute(r2);
		executor.shutdown();
		Assert.assertTrue(executor.awaitTermination(1, TimeUnit.HOURS));
		
		Assert.assertEquals(2, list.stream().mapToInt(Integer::intValue).sum());
		Assert.assertEquals(1, threadIds.size());
		
	}
	
	@org.junit.Test
	public void rejected_task_runs_in_the_caller_thread() throws InterruptedException{
		
		List<Integer> list = new ArrayList<>();
		Set<String> threadNames = new HashSet<>();
		
		Runnable r1 = () -> {list.add(1);try {
			Thread.sleep(2000);
			threadNames.add(Thread.currentThread().getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}};
		Runnable r2 = () -> {list.add(1);try {
			Thread.sleep(2000);
			threadNames.add(Thread.currentThread().getName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}};
		
		ExecutorService executor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.HOURS, new SynchronousQueue<Runnable>(), (r, t)->r.run());
		executor.execute(r1);
		executor.execute(r2);
		executor.shutdown();
		Assert.assertTrue(executor.awaitTermination(1, TimeUnit.HOURS));
		
		Assert.assertEquals(2, list.stream().mapToInt(Integer::intValue).sum());
		Assert.assertEquals(2, threadNames.size());
		Assert.assertEquals(1, threadNames.stream().filter(s->s.startsWith("pool-")).count());
		Assert.assertEquals(1, threadNames.stream().filter(s->!s.startsWith("pool-")).count());
		
	}		

}
