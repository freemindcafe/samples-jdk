package com.freemindcafe.concurrency.sample4;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Test {
	
	@org.junit.Test
	public void test1() throws Exception{
		//Can send 2 requests per minute each having max 2 events
		ISender<MyEvent> sender = new ThrottlingSender<MyEvent>(1, 30, TimeUnit.SECONDS, 1, l -> {System.out.println(new Date());System.out.println(l);});
		for(int i=1; i<=5; i++){
			sender.send(new MyEvent(i));
		}

		synchronized(sender){
			sender.wait();
		}
	}
	
	@org.junit.Test
	public void test2() throws Exception{
		//Can send 2 requests per minute each having max 1 events
		ISender<MyEvent> sender = new ThrottlingSender<MyEvent>(2, 10, TimeUnit.SECONDS, 2, l -> {System.out.println(new Date());System.out.println(l);});
		for(int i=1; i<=15; i++){
			sender.send(new MyEvent(i));
		}

		synchronized(sender){
			sender.wait();
		}
	}	
	
	private class MyEvent{
		private final int num;
		
		private MyEvent(int num){
			this.num = num;
		}
		
		public String toString(){
			return "[ "+num+" ]";
		}
	}

}
