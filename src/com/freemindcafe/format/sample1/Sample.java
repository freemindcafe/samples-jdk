package com.freemindcafe.format.sample1;

import org.junit.Test;

public class Sample {
	
	@Test
	public void test1(){

		//Controlling integer width with printf
		System.out.printf("%3d%n", 0);
		System.out.printf("%3d%n", 123456789);
		System.out.printf("%3d%n", -10);
		System.out.printf("%3d%n", -123456789);
		
		//Left-justifying printf integer output
		System.out.printf("%-3d%n", 0);
		System.out.printf("%-3d%n", 123456789);
		System.out.printf("%-3d%n", -10);
		System.out.printf("%-3d%n", -123456789);
		
		//The printf zero-fill option
		
	}
	
}
