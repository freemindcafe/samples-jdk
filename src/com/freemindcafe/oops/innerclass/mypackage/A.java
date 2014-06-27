package com.freemindcafe.oops.innerclass.mypackage;

import com.freemindcafe.oops.innerclass.library.IMessageProvider;
import com.freemindcafe.oops.innerclass.library.MessageProcessor;


public class A {
	
	public static void main(String[] args) {
		MessageProcessor messageProcessor = new MessageProcessor();
		
		//Every classes objects can be passed
		//and can be used in other packages outside of this package.
		//These access qualifiers are applicable only at compile time.
		
		messageProcessor.process(new B());
		messageProcessor.process(new A().new C());
		messageProcessor.process(new D());
	}
	
	//nested static private class
	private static class B implements IMessageProvider{

		@Override
		public String provideMessage() {
			return "in B";
		}
		
	}
	
	//inner class (nested public class)
	public class C implements IMessageProvider{

		@Override
		public String provideMessage() {
			return "in C";
		}
		
	}
	
	//nested static public class
	public static class D implements IMessageProvider{

		@Override
		public String provideMessage() {
			return "in D";
		}
		
	}	

}
