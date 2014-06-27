package com.freemindcafe.oops.innerclass.mypackage;

/*
 * 
 */
public class B {
	
	public static void main(String[] args) {
		//A can be accessed
		A a = new A();
		
		//B can not be accessed
		//A.B b = new A.B();
		
		//C can be accessed
		//Since it is not static, it can only be accessed within A's closure
		A.C c = new A().new C();
		
		//D can be accessed
		A.D d = new A.D();
		
	}

}
