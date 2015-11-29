package com.freemindcafe.concurrency.sample3;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import junit.framework.Assert;

public class Test {
	
	@org.junit.Test
	public void runnable_with_result_as_future_task() throws InterruptedException, ExecutionException{
		Runnable r1 = () -> System.out.println("r1");
		
		FutureTask<Integer> ft = new FutureTask<Integer>(r1, 1);
		ft.run();
		Assert.assertEquals(1, ft.get().intValue());
	}
	
	@org.junit.Test
	public void callable_as_future_task() throws InterruptedException, ExecutionException{
		Callable<Integer> c1 = () -> 1;
		
		FutureTask<Integer> ft = new FutureTask<Integer>(c1);
		ft.run();
		Assert.assertEquals(1, ft.get().intValue());
	}
	
	@org.junit.Test(expected=CancellationException.class)
	public void calling_get_on_cancelled_task_throws_cancellation_exception() throws InterruptedException, ExecutionException{
		Callable<Integer> c1 = () -> {
			try{
				Thread.sleep(5000);
			}
			catch(InterruptedException ex){
				//some cleanup
				throw ex;
			}
			return 1;
		};
		
		FutureTask<Integer> ft = new FutureTask<Integer>(c1);
		new Thread(()->ft.run()).start();
		ft.cancel(true);
		Assert.assertEquals(1, ft.get().intValue());
	}
	
	@org.junit.Test(expected=CancellationException.class)
	public void calling_get_on_cancelled_task_throws_cancellation_exception_even_if_task_thorows_other_exception() throws InterruptedException, ExecutionException{
		Callable<Integer> c1 = () -> {
			try{
				Thread.sleep(5000);
			}
			catch(InterruptedException ex){
				//some cleanup
				throw new RuntimeException();
			}
			return 1;
		};
		
		FutureTask<Integer> ft = new FutureTask<Integer>(c1);
		new Thread(()->ft.run()).start();
		ft.cancel(true);
		Assert.assertEquals(1, ft.get().intValue());
	}
	
	@org.junit.Test(expected=CancellationException.class)
	public void calling_get_on_cancelled_task_throws_cancellation_exception_even_if_task_does_not_cancel() throws InterruptedException, ExecutionException{
		Callable<Integer> c1 = () -> {
			try{
				Thread.sleep(5000);
			}
			catch(InterruptedException ex){
				//some cleanup
			}
			return 1;
		};
		
		FutureTask<Integer> ft = new FutureTask<Integer>(c1);
		new Thread(()->ft.run()).start();
		ft.cancel(true);
		Assert.assertEquals(1, ft.get().intValue());
	}
	
	@org.junit.Test(expected=CancellationException.class)
	public void calling_get_on_cancelled_task_throws_cancellation_exception_even_if_cancel_was_non_interruptible() throws InterruptedException, ExecutionException{
		Callable<Integer> c1 = () -> {
			try{
				Thread.sleep(5000);
			}
			catch(InterruptedException ex){
				//some cleanup
			}
			return 1;
		};
		
		FutureTask<Integer> ft = new FutureTask<Integer>(c1);
		new Thread(()->ft.run()).start();
		ft.cancel(false);
		Assert.assertEquals(1, ft.get().intValue());
	}
	
	@org.junit.Test(expected=ExecutionException.class)
	public void if_task_throws_any_exceptions_then_calling_get_throws_execution_exception() throws InterruptedException, ExecutionException{
		Callable<Integer> c1 = () -> {
			throw new RuntimeException();
		};
		
		FutureTask<Integer> ft = new FutureTask<Integer>(c1);
		new Thread(()->ft.run()).start();
		Assert.assertEquals(1, ft.get().intValue());
	}	
	
	@org.junit.Test
	public void if_task_throws_any_exceptions_then_calling_get_throws_execution_exception_(){
		Callable<Integer> c1 = () -> {
			throw new RuntimeException();
		};
		
		FutureTask<Integer> ft = new FutureTask<Integer>(c1);
		new Thread(()->ft.run()).start();
		try{
			ft.get();
			Assert.assertTrue(false);
		}
		catch(InterruptedException | CancellationException ex){
			//Empty, this will not be thrown
			Assert.assertTrue(false);
		}
		catch(ExecutionException ex){
			Throwable th = ex.getCause();
			Assert.assertTrue(th instanceof RuntimeException);
		}
	}	

}
