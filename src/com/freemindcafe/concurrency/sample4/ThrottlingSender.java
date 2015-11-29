package com.freemindcafe.concurrency.sample4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class ThrottlingSender<E> implements ISender<E> {
	
	private final int maxRequestsPerUnitOfTime;
	private final int unitOfTime;
	private final TimeUnit timeUnit;
	private final int maxElementsPerRequest;
	private final Consumer<List<E>> requestConsumer;
	private final BlockingQueue<RequestToken> throttlingQueue;
	private final BlockingQueue<E> incomingElementsQueue;
	private final ExecutorService incomingElementsQueueExecutorService;
	private Consumer<E> rejectedRequestHandler = (e) -> {};
	private volatile boolean shutdown = false;

	
	public ThrottlingSender(int maxRequestsPerUnitOfTime, int unitOfTime, TimeUnit timeUnit, int maxElementsPerRequest, Consumer<List<E>> requestConsumer){
		this.maxRequestsPerUnitOfTime = maxRequestsPerUnitOfTime;
		this.unitOfTime = unitOfTime;
		this.timeUnit = timeUnit;
		this.maxElementsPerRequest = maxElementsPerRequest;
		this.requestConsumer = requestConsumer;
		this.throttlingQueue = new ArrayBlockingQueue<>(this.maxRequestsPerUnitOfTime);
		this.incomingElementsQueue = new LinkedBlockingQueue<E>(); //unbounded thread-safe, we don't want to discard any requests
		this.incomingElementsQueueExecutorService = new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>());
		this.incomingElementsQueueExecutorService.execute(new ThrottledSenderRunnable());
	}
	
	
	@Override
	public void send(E event) {
		try {
			if(shutdown){
				rejectedRequestHandler.accept(event);
			}
			else{
				incomingElementsQueue.put(event);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}	
	}
	
	public void shutdown(){
		//set shutdown flag
		//Reject further work
		//Call shutdown on thread pool
		//Wait till queue is empty
		//Exit then
		shutdown = true;
		incomingElementsQueueExecutorService.shutdown();
	}
	
	public List<E> shutdownNow(){
		//set shutdown flag
		//Reject further work
		//Exit
		//Return all pending events
		shutdown = true;
		incomingElementsQueueExecutorService.shutdownNow();
		List<E> allEvents = new ArrayList<>();
		incomingElementsQueue.drainTo(allEvents);
		return allEvents;
	}	
	
	private class ThrottledSenderRunnable implements Runnable{
		
		@Override
		public void run() {
			while(true){
				try {
					if(!incomingElementsQueue.isEmpty()){
						RequestToken token = new RequestToken();
						throttlingQueue.put(token);
						token.start();
						List<E> elements = new ArrayList<E>();
						incomingElementsQueue.drainTo(elements, maxElementsPerRequest);
						requestConsumer.accept(elements);
					}
					else{
						if(shutdown){
							break;
						}
					}
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
					//In case of interrupt, come out of the loop
					//As the thread pool executor will interrupt the thread in case of shutdown
					break;
				}				
			}
		
		}
		
	}
	
	private class RequestToken{
		
		private void start(){
			ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
			service.schedule(()->throttlingQueue.remove(this), unitOfTime, timeUnit);
				
		}
		
	}
	
	public void setrRejectedRequestHandler(Consumer<E> rejectedRequestHandler){
		this.rejectedRequestHandler = rejectedRequestHandler;
	}
	
}
