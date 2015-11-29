package com.freemindcafe.timezone.sample2;

import java.util.TimeZone;

import junit.framework.Assert;

import org.junit.Test;

public class Sample {
	
	@Test
	public void test1(){
		TimeZone tz = TimeZone.getTimeZone("Asia/Calcutta");
		Assert.assertEquals("India Standard Time",tz.getDisplayName());
	}
	
	@Test
	public void test2(){
		TimeZone tz = TimeZone.getTimeZone("IST");
		Assert.assertEquals("India Standard Time",tz.getDisplayName());
	}
	
	@Test
	public void test3(){
		TimeZone tz = TimeZone.getTimeZone("Etc/GMT+5:30");
		Assert.assertEquals("Greenwich Mean Time",tz.getDisplayName());
	}
	
	@Test
	public void test4(){
		TimeZone tz = TimeZone.getTimeZone("Etc/GMT+5");
		Assert.assertEquals("GMT-05:00",tz.getDisplayName());
	}
	
	@Test
	public void test5(){
		TimeZone tz = TimeZone.getTimeZone("Etc/GMT-5");
		Assert.assertEquals("GMT+05:00",tz.getDisplayName());
	}
	
	@Test
	public void test6(){
		TimeZone tz = TimeZone.getTimeZone("GMT+5:30");
		Assert.assertEquals("GMT+05:30",tz.getDisplayName());
	}

}
