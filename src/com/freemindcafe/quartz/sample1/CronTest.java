package com.freemindcafe.quartz.sample1;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.quartz.CronExpression;


public class CronTest {

	@Test
	public void cronTest(){
		CronExpression cron1=null;
		CronExpression cron2 = null;
		CronExpression cron3 = null;
		try {
			cron1 = new CronExpression("0 0 11-23,0-1 * * ?");
			cron2=new CronExpression("* * 11-23,0-1 ? * MON-FRI"); 
			cron3=new CronExpression("* * 11-23,0-1 ? * SAT"); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal=Calendar.getInstance();
		cal.set(Calendar.YEAR, 2015);
		cal.set(Calendar.MONTH, 3);
		cal.set(Calendar.DATE, 30);
		cal.set(Calendar.HOUR, 13);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date date = cal.getTime();
		System.out.println(date);
		

		System.out.println("---------------------------");
		Assert.assertTrue(cron1.isSatisfiedBy(date));
		System.out.println(cron1.isSatisfiedBy(date));
		System.out.println("Invalid"+cron1.getNextInvalidTimeAfter(date));
		System.out.println("Valid"+cron1.getNextValidTimeAfter(date));
		
		System.out.println("---------------------------");

		Assert.assertTrue(cron2.isSatisfiedBy(date));
		System.out.println(cron2.isSatisfiedBy(date));
		System.out.println("Invalid"+cron2.getNextInvalidTimeAfter(date));
		System.out.println("Valid"+cron2.getNextValidTimeAfter(date));
		System.out.println("---------------------------");

		System.out.println("---------------------------");

		Assert.assertFalse(cron3.isSatisfiedBy(date));
		System.out.println(cron3.isSatisfiedBy(date));
		System.out.println("Invalid"+cron3.getNextInvalidTimeAfter(date));
		System.out.println("Valid"+cron3.getNextValidTimeAfter(date));
		
	}
}
