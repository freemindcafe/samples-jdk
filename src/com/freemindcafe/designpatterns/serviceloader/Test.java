package com.freemindcafe.designpatterns.serviceloader;

import java.util.ServiceLoader;

public class Test {
	
	public static void main(String[] args) {
		ServiceLoader<ICepEngine> serviceLoader = ServiceLoader.load(ICepEngine.class);
		for (ICepEngine cepEngine : serviceLoader) {	          
			cepEngine.echo();
	      }
	}

}
