package com.freemindcafe.timezone.sample3;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import junit.framework.Assert;

import org.junit.Test;

public class Sample {
	
	@Test
	public void test1(){
		TimeZone tz = TimeZone.getTimeZone("Asia/Calcutta");
		Assert.assertEquals("India Standard Time",tz.getDisplayName());
		Calendar cal = Calendar.getInstance(tz);
		System.out.printf("%02d:%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
	}
	
	@Test
	public void test2(){
		TimeZone tz = TimeZone.getTimeZone("IST");
		Assert.assertEquals("India Standard Time",tz.getDisplayName());
		Calendar cal = Calendar.getInstance(tz);
		System.out.printf("%02d:%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
	}
	
	@Test
	public void test3(){
		TimeZone tz = TimeZone.getTimeZone("Etc/GMT+5:30"); //This is wrong format hence falls back to GMT. Correct format is Etc/GMT(+-)n
		Assert.assertEquals("Greenwich Mean Time",tz.getDisplayName());
		Calendar cal = Calendar.getInstance(tz);
		System.out.printf("%02d:%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
	}
	
	@Test
	public void test4(){
		TimeZone tz = TimeZone.getTimeZone("Etc/GMT+5");
		Assert.assertEquals("GMT-05:00",tz.getDisplayName());
		Calendar cal = Calendar.getInstance(tz);
		System.out.printf("%02d:%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
	}
	
	@Test
	public void test5(){
		TimeZone tz = TimeZone.getTimeZone("Etc/GMT-5"); //Gets converted into GMT+5
		Assert.assertEquals("GMT+05:00",tz.getDisplayName());
		Calendar cal = Calendar.getInstance(tz);
		System.out.printf("%02d:%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
	}
	
	@Test
	public void test6(){
		TimeZone tz = TimeZone.getTimeZone("GMT+5:30"); //This is again IST
		Assert.assertEquals("GMT+05:30",tz.getDisplayName());
		Calendar cal = Calendar.getInstance(tz);
		System.out.printf("%02d:%02d:%02d", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));		
	}
	
	//There was an bug. IST was pointing to Isreal Standard Time. Seems that have been fixed. SimpleDateFomrat is giving the correct date time string now.
	@Test
	public void test7() throws Exception{
		
		DateFormat fmt1 = new SimpleDateFormat("M/d/yy h:mm a Z");
	    Date date = fmt1.parse("6/29/2012 5:15 PM IST");
	    System.out.println(date);

	    DateFormat fmt2 = new SimpleDateFormat("M/d/yy h:mm a");
	    fmt2.setTimeZone(TimeZone.getTimeZone("IST"));
	    System.out.println(fmt2.parse("6/29/2012 5:15 PM"));

	    DateFormat fmt3 = new SimpleDateFormat("M/d/yy h:mm a");
	    fmt3.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
	    System.out.println(fmt3.parse("6/29/2012 5:15 PM"));
	    
	}

}
