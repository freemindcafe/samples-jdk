package com.freemindcafe.java8.sample1;

import java.util.Optional;

import org.junit.Test;

public class Test1{
	
	@Test(expected=NullPointerException.class)
	public void should_throw_null_pointer_exception(){
		System.out.println(new Person().car.insurance.name);
	}
	
	@Test
	public void should_not_throw_null_pointer_exception(){
		//System.out.println(new Person().optionalCar.map((c) -> c.optionalInsurance).map((i) -> i.);
	}
	
}

class Person {
	Car car;
	Optional<Car> optionalCar;
}

class Car{
	Insurance insurance;
	Optional<Insurance> optionalInsurance;
}

class Insurance{
	String name;
}
