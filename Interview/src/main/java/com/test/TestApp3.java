package com.test;

import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

public class TestApp3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		AtomicInteger num = new AtomicInteger(50);
//		for(int i = 0  ; i < 50; i++){
//			System.out.println(num.getAndDecrement());
//		}
//		
		char s = '1';
		int a = Integer.parseInt(s+"");
		System.out.println("a="+a);
		System.out.println(String.valueOf(s));
		
		
		byte ac = 1;
		long b = ac;
		System.out.println("b="+b);
		
		
		test();

	}

	
	public static void test(){
		byte a = 127;
		byte b = 127;
		//b = a + b; // error : cannot convert from int to byte
		b += a; // ok
		System.out.println("a="+a+" \t b="+b);
		long l = a;
	}
}
