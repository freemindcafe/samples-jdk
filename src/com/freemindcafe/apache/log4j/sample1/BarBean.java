package com.freemindcafe.apache.log4j.sample1;

import org.apache.log4j.Logger;

public class BarBean {
	private static final Logger logger = Logger.getLogger(BarBean.class);

	public void sayHello() {
		logger.info("Hello there from BarBean class!");
		logger.warn("Hello there from BarBean class!");
	}
}
