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
 * 测试父类隐藏 定义逻辑<p>
 * 隐藏是针对父类中的成员变量和静态方法而言
 * @author LingMin 
 * @date 2017-2-22<br>
 * @version 1.0<br>
 */
public class Child extends Parent {

	//对象属性
	protected String str= "Child_str";
	
	//静态属性
	public static String SSTR = "Child_static_str";

	/**
	 * 静态方法<p>
	 *  <p>
	 * void
	 */
	public static void test(){
		System.out.println("Child_test()");
	}
	
	/**
	 * 普通方法<p>
	 *  <p>
	 * void
	 */
	public void test2(){
		System.out.println("Child_test2()");
		System.out.println("this.str="+this.str);
		System.out.println("super.str="+super.str);
	}
	
	/**
	 * 子类普通方法<p>
	 *  <p>
	 * void
	 */
	public void test3(){
		System.out.println("Child_test3()");
	}
	/***
	 * 隐藏是针对父类中的成员变量和静态方法而言<p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		Parent parent = new Parent();
		System.out.println("父类对象");
		System.out.println("parent.str="+parent.str);
		System.out.println("parent.SSTR="+parent.SSTR);
		parent.test();
		parent.test2();
		Child child = new Child();
		System.out.println("子类对象");
		System.out.println("child.str="+child.str);
		System.out.println("child.SSTR="+child.SSTR);
		child.test();
		child.test2();
		
		Parent parent2 = child;
		System.out.println("父类引用子类对象，parent2="+parent2);
		System.out.println("parent2.str="+parent2.str);//打印父类属性
		System.out.println("parent2.SSTR="+parent2.SSTR);//打印父类静态属性
		parent2.test();//调用父类静态方法
		parent2.test2();//调用子类方法
		
		
		//Child child2 = (Child)parent;//运行异常  java.lang.ClassCastException: com.inherit.hide.Parent cannot be cast to com.inherit.hide.Child
		//child2.test3();
	}
}
