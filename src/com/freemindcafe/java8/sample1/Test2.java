package com.freemindcafe.java8.sample1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

import org.junit.Test;

public class Test2 {
	
	@Test
	public void test1() {
		Runnable r1 = new Runnable() {
			
			@Override
			public void run() {
				System.out.println("r1");			
			}
		};
		
		r1.run();
		
		Runnable r2 = () -> System.out.println("r2");
		r2.run();
		
		//Runnable r3 = () -> System.out::println;
	}
	
	
	@Test
	public void test2(){
		List<String> threeHighCaloricDishNames = Dish.menu.stream().filter(d -> d.getCalories() > 300).map(d -> d.getName()).map(d -> d.toUpperCase()).limit(3).collect(toList());
		System.out.println(threeHighCaloricDishNames);
		
		List<String> vegDishNames = Dish.menu.stream().filter(d -> d.isVegetarian()).map(d -> d.getName()).collect(toList());
		System.out.println(vegDishNames);
		
		List<String> skipFirstTwoHighCaloricDishNames = Dish.menu.stream().filter(d -> d.getCalories() > 300).map(d -> d.getName()).map(d -> d.toUpperCase()).skip(2).collect(toList());
		System.out.println(skipFirstTwoHighCaloricDishNames);
		
		List<String> words = Arrays.asList("Java8", "Lambdas", "In", "Action");
		List<Integer> worldLengths = words.stream().map(s -> s.length()).collect(toList());
		System.out.println(worldLengths);
		
		List<Integer> dishNameLengths = Dish.menu.stream().map(d -> d.getName()).map(s -> s.length()).collect(toList());
		System.out.println(dishNameLengths);
		
//		List<String> distinctWords = words.stream().map(s -> s.split("")).distinct().collect(toList());
//		List<String[]> distinctWords = words.stream().map(s -> s.split("")).distinct().collect(toList());
//		System.out.println(distinctWords);
		
//		List<Stream<String>> distinctWords = words.stream().map(s->s.split("")).map(s->Arrays.stream(s)).distinct().collect(toList());
//		System.out.println(distinctWords);
		
		List<String> distinctWords = words.stream().map(s->s.split("")).flatMap(s->Arrays.stream(s)).map(s->s.toLowerCase()).distinct().collect(toList());
		System.out.println(distinctWords);
	}
	
	@Test
	public void temp(){
		Arrays.stream("nikhil".split("")).forEach(s -> System.out.println(s));
	}
	

	
	

}


