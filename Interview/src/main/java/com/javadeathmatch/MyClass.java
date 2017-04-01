/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.javadeathmatch;

/** 
 * 
 * 问题的关键在于，是否使用了toString()<p>
 * @author LingMin 
 * @date 2017-3-16<br>
 * @version 1.0<br>
 */
public class MyClass {
	
	private String name;

	/**             <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		MyClass m0 = new MyClass();
		MyClass m1 = new MyClass();
		m0.name = m1.name = "m0";
//		callMe(m0 , m1);
		callMe2(m0 , m1);//两张方法传递参数 调用等同于
		
		System.out.println(m0+" & "+m1);
		System.out.println(m0.name+" & "+m1.name);
	}
	
	
	private static void callMe(MyClass... m){
		m[0] = m[1];
		m[1].name = "new name";
		System.out.println("callMe="+m[0]+" & "+m[1]);
		System.out.println("name="+m[0].name+" & "+m[1].name);
	}
	
	private static void callMe2(MyClass m0 , MyClass m1){
		m0 = m1;
		m1.name = "new name";
		System.out.println("callMe2="+m0+" & "+m1);
		System.out.println("name="+m0.name+" & "+m1.name);
	}

}
