package com.freemindcafe.apache.log4j.sample2;

import org.apache.log4j.Logger;

public class FooBean {
	private static final Logger logger = Logger.getLogger("c");
	
	public void sayHello() {
		logger.debug("Hello there from FooBean class!");		
		logger.info("Hello there from FooBean class!");
	}
}
