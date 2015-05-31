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
		
		String xml = SerializationSvc.getSerializationSvc().toXML(mappings);
		String json = SerializationSvc.getSerializationSvc().toJSON(mappings);

		
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
		
		String xml = SerializationSvc.getSerializationSvc().toXML(wrapper);
		String json = SerializationSvc.getSerializationSvc().toJSON(wrapper);

		
		System.out.println(xml);
		System.out.println(json);
		
	}	
}
