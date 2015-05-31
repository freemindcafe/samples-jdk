package com.freemindcafe.serialization.sample3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import com.freemindcafe.serialization.SerializationSvc;

public class Sample {
	
	@Test
	public void map_to_json(){
		Map<String, String> workerUri = new HashMap<>();
		workerUri.put("hostname", "localhost");
		workerUri.put("port", "9449");
		workerUri.put("mbean", "com.freemindcafe");
		
		String jsonUsingJackson = JSONParserUtil.objectToJson(workerUri, Map.class);
		System.out.println(jsonUsingJackson);
		
		String jsonUsingJettison = SerializationSvc.getSerializationSvc().toJSON(workerUri);
		System.out.println(jsonUsingJettison);		
		
		Map<String, String> deserializedWorkerUri = (Map)JSONParserUtil.jsonToObject(jsonUsingJackson, Map.class);
		Assert.assertEquals(workerUri, deserializedWorkerUri);
	}
	
	@Test
	public void list_to_json(){
		Map<String, String> workerUri1 = new HashMap<>();
		workerUri1.put("hostname", "localhost");
		workerUri1.put("port", "9449");
		workerUri1.put("mbean", "com.freemindcafe");
		
		Map<String, String> workerUri2 = new HashMap<>();
		workerUri2.put("hostname", "localhost");
		workerUri2.put("port", "9449");
		workerUri2.put("mbean", "com.freemindcafe");
		
		List<Map<String,String>> uris = new ArrayList<>();
		uris.add(workerUri1);
		uris.add(workerUri2);
		
		
		String jsonUsingJackson = JSONParserUtil.objectToJson(uris, List.class);
		System.out.println(jsonUsingJackson);
		
		String jsonUsingJettison = SerializationSvc.getSerializationSvc().toJSON(uris);
		System.out.println(jsonUsingJettison);		
		
	}	

}
