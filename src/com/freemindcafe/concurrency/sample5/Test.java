package com.freemindcafe.concurrency.sample5;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

public class Test {
	
	@org.junit.Test
	public void test_with_out_coordination() throws Exception{
		//Semaphore mutex = new Semaphore(1);
		List<Integer> firstTenInts = new CopyOnWriteArrayList<>();
		
		Runnable r1 = () -> {
			for(int i=0; i <=4; i++){
				firstTenInts.add(2*i+1);
			}
		};
		
		Runnable r2 = () -> {
			for(int i=1; i <=5; i++){
				firstTenInts.add(2*i);
			}
		};
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println(firstTenInts);
		
		
	}
	
	@org.junit.Test
	public void test_with_coordination_using_semaphore() throws Exception{
		Semaphore firstOneDone = new Semaphore(0);
		Semaphore secondOneDone = new Semaphore(0);
		List<Integer> firstTenInts = new CopyOnWriteArrayList<>();
		
		Runnable r1 = () -> {
			try{
				for(int i=0; i <=4; i++){
					//System.out.println("first");
					firstTenInts.add(2*i+1);
					firstOneDone.release();
					secondOneDone.acquire();
				}				
			}
			catch(Exception ex){
				throw new RuntimeException(ex);
			}
		};		
		
		Runnable r2 = () -> {
			try{
				for(int i=1; i <=5; i++){
					//System.out.println("second");
					firstOneDone.acquire();
					firstTenInts.add(2*i);
					secondOneDone.release();
				}				
			}
			catch(Exception ex){
				throw new RuntimeException(ex);
			}
		};
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println(firstTenInts);
		
		
	}
	
	//[FIXME] Can we write is using condition vatiables
	@org.junit.Test
	public void test_with_coordination_using_condition() throws Exception{
		Lock lock = new ReentrantLock();
		Condition firstOneDone = lock.newCondition();
		Condition secondOneDone = lock.newCondition();
		List<Integer> firstTenInts = new CopyOnWriteArrayList<>();
		
		Runnable r1 = () -> {
			try{
				for(int i=0; i <=4; i++){
					//System.out.println("first");
					firstTenInts.add(2*i+1);
					firstOneDone.signal();
					secondOneDone.wait();
				}				
			}
			catch(Exception ex){
				throw new RuntimeException(ex);
			}
		};		
		
		Runnable r2 = () -> {
			try{
				for(int i=1; i <=5; i++){
					//System.out.println("second");
					firstOneDone.wait();
					firstTenInts.add(2*i);
					secondOneDone.signal();
				}				
			}
			catch(Exception ex){
				throw new RuntimeException(ex);
			}
		};
		
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
		
		System.out.println(firstTenInts);
		
		
	} <T> Block<T> exceptionWrappingBlock(Block<T> b) {
	     return () -> {
	         try { b.accept(); }
	         catch (Exception ex) { throw new RuntimeException(ex); }
	     };
	}

}

interface Block<T>{
	void accept() throws Exception;
}
