package com.freemindcafe.concurrency.sample6;

import java.util.concurrent.CyclicBarrier;

public class Test {
	
	@org.junit.Test
	public void test_using_custom_barrier() throws Exception{
		Barrier barrier = new Barrier(2);
		
		Runnable r1 = ()->{
			try{
				System.out.println("before in r1");
				barrier.await();
				System.out.println("after in r1");
			}
			catch(Exception ex){
				
			}
		};
		
		Runnable r2 = ()->{
			try{
				System.out.println("before in r2");
				barrier.await();
				System.out.println("after in r2");
			}
			catch(Exception ex){
				
			}
		};
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
	}
	
	@org.junit.Test
	public void test_using_jdk_barrier() throws Exception{
		CyclicBarrier barrier = new CyclicBarrier(2);
		
		Runnable r1 = ()->{
			try{
				System.out.println("before in r1");
				barrier.await();
				System.out.println("after in r1");
			}
			catch(Exception ex){
				
			}
		};
		
		Runnable r2 = ()->{
			try{
				System.out.println("before in r2");
				barrier.await();
				System.out.println("after in r2");
			}
			catch(Exception ex){
				
			}
		};
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
	}	

}
