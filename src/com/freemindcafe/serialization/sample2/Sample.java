package com.freemindcafe.serialization.sample2;

import java.util.HashMap;
import java.util.Map;

import com.freemindcafe.serialization.SerializationSvc;

public class Sample {
	
	@org.junit.Test
	public void map_without_wrapper() {
		Map<Integer, String> mappings = new HashMap<>();
		mappings.put(1, "one");
		mappings.put(2, "two");
		
		String xml = new SerializationSvc().toXML(mappings);
		String json = new SerializationSvc().toJSON(mappings);

		
		System.out.println(xml);
		System.out.println(json);
		
	}	

	@org.junit.Test
	public void map_within_wrapper() {
		MapWrapper wrapper = new MapWrapper();
		Map<Integer, String> mappings = new HashMap<>();
		mappings.put(1, "one");
		mappings.put(2, "two");
		wrapper.mappings = mappings;
		
		String xml = new SerializationSvc().toXML(wrapper);
		String json = new SerializationSvc().toJSON(wrapper);

		
		System.out.println(xml);
		System.out.println(json);
		
	}	
}
