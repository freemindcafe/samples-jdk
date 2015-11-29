package com.freemindcafe.concurrency.sample4;


public interface ISender<E> {
	
	public void send(E event);

}
