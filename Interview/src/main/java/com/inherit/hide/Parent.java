/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.inherit.hide;

/** 
 * 
 * 测试父类隐藏 定义逻辑<p>
 * 隐藏是针对父类中的成员变量和静态方法而言
 * @author LingMin 
 * @date 2017-2-22<br>
 * @version 1.0<br>
 */
public class Parent {

	//对象属性
	protected String str= "parent_str";
	
	//静态属性
	public static String SSTR = "parent_static_str";

	/**
	 * 静态方法<p>
	 *  <p>
	 * void
	 */
	public static void test(){
		System.out.println("Parent_test()");
	}
	
	/**
	 * 普通方法<p>
	 *  <p>
	 * void
	 */
	public void test2(){
		System.out.println("Parent_test2()");
	}
}
