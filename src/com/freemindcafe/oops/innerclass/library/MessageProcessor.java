package com.freemindcafe.oops.innerclass.library;

import com.freemindcafe.oops.innerclass.mypackage.A;



public class MessageProcessor {
	
	public void process(IMessageProvider iMessageProvider){
		System.out.println(iMessageProvider.provideMessage());
	}

}
