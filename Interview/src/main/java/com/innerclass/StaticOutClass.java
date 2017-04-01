package com.innerclass;


/** 
 * 
 * 测试静态内部类<p>
 * @author LingMin 
 * @date 2016-10-31<br>
 * @version 1.0<br>
 */
public class StaticOutClass {
	/**
	 * 静态内部类
	 */
	static class StaticInnerClass{
		public void test(){
			System.out.println("test11");
		}
	}
	public static void main(String[] args) {
		StaticInnerClass staticInner = new StaticInnerClass();
		staticInner.test();
	}
	
}
