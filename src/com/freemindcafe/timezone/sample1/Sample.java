package com.freemindcafe.timezone.sample1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.Test;

import junit.framework.Assert;

public class Sample {
	
	@Test
	public void normal_day(){
		
		TimeZone timeZone = TimeZone.getTimeZone("America/Los_Angeles");
		System.out.println(timeZone.useDaylightTime());
		System.out.println(timeZone.getDSTSavings());
		//timeZone.inDaylightTime(date)
		
		Calendar startTime = Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles"));
		startTime.set(2015, Calendar.JANUARY, 1, 1, 0, 0);
		
		Calendar endTime = Calendar.getInstance(TimeZone.getTimeZone("America/Los_Angeles"));
		endTime.set(2015, Calendar.JANUARY, 2, 0, 0, 0);		
		
		int count = 0;
		
		while(endTime.after(startTime) || endTime.equals(startTime)){
			System.out.format("%d-%02d-%02dT%02d-%02d-%02d\n",
					startTime.get(Calendar.YEAR), startTime.get(Calendar.MONTH), startTime.get(Calendar.DAY_OF_MONTH), 
					startTime.get(Calendar.HOUR_OF_DAY), startTime.get(Calendar.MINUTE), startTime.get(Calendar.SECOND));
			startTime.add(Calendar.HOUR_OF_DAY, 1);
			count++;
		}
		
		Assert.assertTrue(count == 24);
		
	}
	
	public static void main(String[] args) {
		

	}

}
