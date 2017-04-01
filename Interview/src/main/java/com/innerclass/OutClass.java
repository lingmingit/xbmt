package com.innerclass;


/** 
 * 测试内部类
 * <p>
 * @author LingMin 
 * @date 2016-10-31<br>
 * @version 1.0<br>
 */
public class OutClass {
	
	/***
	 * 内部类
	 */
	 public class InnerClass {
		 public void test(){
			System.out.println("test"); 
		 }
	 }
	 /**
	  * main方法
	  */
	 public static void main(String[] args) {
		 OutClass out = new OutClass();
		 OutClass.InnerClass inner = out.new InnerClass();
		 inner.test();
	}
}
