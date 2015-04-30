package com.freemindcafe.apache.velocity.sample1;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Assert;

public class Test {
	
	@org.junit.Test
	public void test1() {
		VelocityEngine velocityEngineTest=new VelocityEngine();
		Map<String, String> valTypes = new HashMap<String, String>();
		valTypes.put("hasNewData", "Y");
		valTypes.put("hasMissingData", "N");
		//valTypes.put("customValType", "Y");	
		List<String> expressions = Arrays.asList("$hasNewData == 'Y' || $hasMissingData == 'Y'", "true", "false", "$hasNewData == 'Y' && $hasMissingData == 'Y'");
		List<String> expectedResults = Arrays.asList("true", "true" , "false", "false");
		List<String> actualResults = new ArrayList<String>();
		
		String templateExpression = "#if(%replace_this%)true #else false #end";
		expressions.forEach((expression)->{
			VelocityContext context = new VelocityContext();
			valTypes.forEach((valType,valid) -> {context.put(valType, valid);});
			StringWriter writer = new StringWriter();
			try {
				velocityEngineTest.evaluate(context, writer, "", templateExpression.replace("%replace_this%", expression));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			actualResults.add(writer.toString().trim());
		});
		Assert.assertTrue(actualResults.equals(expectedResults));
		
	}


}
