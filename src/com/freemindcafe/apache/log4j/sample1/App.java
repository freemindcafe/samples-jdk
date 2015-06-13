package com.freemindcafe.apache.log4j.sample1;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class App {
	private static final Logger logger = Logger.getLogger(App.class);

	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.load(App.class.getResourceAsStream("/com/freemindcafe/apache/log4j/sample1/log4j.properties"));
		PropertyConfigurator.configure(props);
		
		FooBean fooBean = new FooBean();
		BarBean barBean = new BarBean();

		logger.debug("Hello there from App class!");

		fooBean.sayHello();
		barBean.sayHello();
	}
}
