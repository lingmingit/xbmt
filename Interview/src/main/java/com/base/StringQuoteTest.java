/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.base;

/** 
 * String 、char[]数组 方法内改变是否影响全局变量
 * 1、String类型方法内修改不影响全局变量（String 不可变）
 * 2、String[] 、char[]数组是引用类型，方法内修改会影响全局变量
 * <p>
 * @author LingMin 
 * @date 2016-9-7<br>
 * @version 1.0<br>
 */
public class StringQuoteTest {
	
	String str = new String("good");
	String[] strs = {"111" , "222"};
	char[] ch = {'a' , 'b'  , 'c'};

	/**             <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		StringQuoteTest test = new StringQuoteTest();
		test.change(test.str , test.strs , test.ch);
		System.out.print(test.str+ " and ");// 打印结果 good String类型不可变 
		System.out.print(test.strs[0]);//打印结果 update 数组为引用类型，被修改
		System.out.print(test.ch);//打印结果 gbc 数组为引用类型，被修改
		//good and update gbc
	}
	
	public void change(String str ,String[] strs , char ch[]){
		str = "test ok";
		strs[0] = "update ";
		ch[0] = 'g';
	}

}
