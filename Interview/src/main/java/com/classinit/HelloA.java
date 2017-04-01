package com.classinit;


/** 
 * 测试类初始化顺序 <p>
 * @author LingMin 
 * @date 2016-10-17<br>
 * @version 1.0<br>
 */
public class HelloA {

	String strA = null;
	/***
	 * 构造方法 
	 */
	public HelloA(){
		System.out.println("HelloA");
	}
	/***代码块****/
	{
		System.out.println("I'm A Class");
	}
	/***静态代码块****/
	static{
		System.out.println("static A");
	}
}
