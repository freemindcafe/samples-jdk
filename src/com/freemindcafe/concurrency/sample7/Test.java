package com.freemindcafe.concurrency.sample7;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Test {
	
	@org.junit.Test
	public void calling_conditional_replace_will_work_only_if_the_state_has_not_been_changed_1() throws Exception{
		Map<Integer, List<Integer>> cache = new ConcurrentHashMap<Integer, List<Integer>>();
		List<Integer> l = new ArrayList<>();
		l.add(1);
		cache.put(1, l);
		
		Runnable r1 = () -> { 
			List<Integer> list  = cache.get(1);
			list.add(2);
			System.out.println(cache.replace(1, list, list));
		};
		
		Runnable r2 = () -> { 
			while(true){
				List<Integer> list  = cache.get(1);
				list.add(3);
				if(!cache.replace(1, list, list)){
					continue;
				}
				break;
			}
		};
		
		new Thread(r1).start();
		new Thread(r2).start();
		
		synchronized (r2) {
			r2.wait();
		}
		
	}
	
	@org.junit.Test
	public void calling_conditional_replace_will_work_only_if_the_state_has_not_been_changed_2() throws Exception{
		Map<Integer, List<Integer>> cache = new ConcurrentHashMap<Integer, List<Integer>>();
		List<Integer> l = new CopyOnWriteArrayList<Integer>();
		l.add(1);
		cache.put(1, l);
		
		Runnable r1 = () -> { 
			List<Integer> list  = cache.get(1);
			list.add(2);
			System.out.println(cache.replace(1, list, list));
		};
		
		Runnable r2 = () -> { 
			while(true){
				List<Integer> list  = cache.get(1);
				list.add(3);
				if(!cache.replace(1, list, list)){
					continue;
				}
				break;
			}
		};
		
		new Thread(r1).start();
		new Thread(r2).start();
		
		synchronized (r2) {
			r2.wait();
		}
		
	}	
	
	@org.junit.Test
	public void calling_conditional_replace_will_work_only_if_the_state_has_not_been_changed_3() throws Exception{
		Map<Integer, Object> cache = new ConcurrentHashMap<Integer, Object>();
		cache.put(1, new Object());
		
		Runnable r1 = () -> { 
			Object obj  = cache.get(1);
			System.out.println(cache.replace(1, obj, new Object()));
		};
		
		Runnable r2 = () -> { 
			while(true){
				Object obj  = cache.get(1);
				if(!cache.replace(1, obj, new Object())){
					continue;
				}
				break;
			}
		};
		
		new Thread(r1).start();
		new Thread(r2).start();
		
		synchronized (r2) {
			r2.wait();
		}
		
	}

}
