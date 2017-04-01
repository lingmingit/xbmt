package com.base;

/***
 * 测试 String equals()  == <p>
 * @author LingMin 
 * @date 2016-8-16<br>
 * @version 1.0<br>
 */
public class StringEqualsTest {

	/** 
	 * <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		String s = "hello";
		String t = new String("hello");
		char[] c = {'h' , 'e' , 'l' , 'l' , 'o'};
		
		System.out.println(s.equals(t));//true
		System.out.println(t.equals(c));//false，此处c为一个object对象，而不会自动转换为String，所以为false
		System.out.println(s == t);// false
		System.out.println(t.equals(new String("hello")));// true ，这个是值比较，所以是true
		
		String str1 = "hello";
		String str2 = "he"+new String("llo");
		System.out.println(str1 == str2);//  false
		
		
		 String s1 = "Programming";
	        String s2 = new String("Programming");
	        String s3 = "Program" + "ming";
	        System.out.println(s1 == s2);
	        System.out.println(s1 == s3);
	        System.out.println(s1 == s1.intern());
	}

}
