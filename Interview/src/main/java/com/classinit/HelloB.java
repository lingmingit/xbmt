package com.classinit;


/** 
 * 测试类初始化顺序 <p>
 * 类初始化顺序： 静态代码块 -->  代码块  -->  构造方法
 * @author LingMin 
 * @date 2016-10-17<br>
 * @version 1.0<br>
 */
public class HelloB extends HelloA{

	String strB = null ;
	/***
	 * 构造方法 
	 */
	public HelloB(){
		System.out.println("HelloB");
	}
	/***代码块****/
	{
		System.out.println("I'm B Class");
	}
	/***静态代码块****/
	static{
		System.out.println("static B");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new HelloB();
		/***
		 * 1、父类静态块
		 * 2、子类静态块
		 * 3、父类代码块
		 * 4、父类构造方法
		 * 5、子类代码块
		 * 6、子类构造方法
		 * 
		 *  static A
			static B
			I'm A Class
			HelloA
			I'm B Class
			HelloB
		 */
		
	}

}
