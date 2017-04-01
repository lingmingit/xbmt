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
 * 什么是字符串常量池？ http://www.importnew.com/10756.html
 * 这些字符串的创建方式之间有什么区别呢？使用new运算符、使用字符串常量或者常量表达式
 *  使用相同的字符序列而不是使用new关键字创建的两个字符串会创建指向Java字符串常量池中的同一个字符串的指针。字符串常量池是Java节约资源的一种方式。
 *  <p>
 *  同一个包下同一个类中的字符串常量的引用指向同一个字符串对象；
同一个包下不同的类中的字符串常量的引用指向同一个字符串对象；
不同的包下不同的类中的字符串常量的引用仍然指向同一个字符串对象；
由常量表达式计算出的字符串在编译时进行计算,然后被当作常量；
在运行时通过连接计算出的字符串是新创建的，因此是不同的；
通过计算生成的字符串显示调用intern方法后产生的结果与原来存在的同样内容的字符串常量是一样的。
 * @author LingMin 
 * @date 2016-9-10<br>
 * @version 1.0<br>
 */
public class DemoStringCreation {
	
	class Other {  String hello = "Hello"; }

	/**             <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		
		DemoStringCreation test = new DemoStringCreation();
		
		String str1 = "Hello";
        String str2 = "Hello";
        System.out.println("str1 and str2 are created by using string literal.");
        System.out.println("    str1 == str2 is " + (str1 == str2));
        System.out.println("    str1.equals(str2) is " + str1.equals(str2));  
        String str3 = new String("Hello");
        String str4 = new String("Hello");
        System.out.println("str3 and str4 are created by using new operator.");
        System.out.println("    str3 == str4 is " + (str3 == str4));
        System.out.println("    str3.equals(str4) is " + str3.equals(str4));  
        String str5 = "Hel" + "lo";
        String str6 = "He" + "llo";
        System.out.println("str5 and str6 are created by using string constant expression.");
        System.out.println("    str5 == str6 is " + (str5 == str6));
        System.out.println("    str5.equals(str6) is " + str5.equals(str6));  
        String s = "lo";
        String str7 = "Hel" + s;
        String str8 = "He" + "llo";
        System.out.println("str7 is computed at runtime.");
        System.out.println("str8 is created by using string constant expression.");
        System.out.println("    str7 == str8 is " + (str7 == str8));
        System.out.println("    str7.equals(str8) is " + str7.equals(str8));  
        
        
     // Create three strings in three different ways.
        String s1 = "Hello";
        String s2 = new StringBuffer("He").append("llo").toString();
        //java.lang.String.intern()返回一个保留池字符串，就是一个在全局字符串池中有了一个入口。如果以前没有在全局字符串池中，那么它就会被添加到里面。
        String s3 = s2.intern();
 
        // Determine which strings are equivalent using the ==
        // operator
        System.out.println("s1 == s2? " + (s1 == s2));
        System.out.println("s1 == s3? " + (s1 == s3));
        
        Other other = test.new Other();
        String hello = "Hello", lo = "lo";
        System.out.print((hello == "Hello") + " ");
        System.out.print((other.hello == hello) + " ");//其它类对象的 字符串常量
        //System.out.print((other.Other.hello == hello) + " ");// 其它类静态的字符串常量
        System.out.print((hello == ("Hel"+"lo")) + " ");
        System.out.print((hello == ("Hel"+lo)) + " ");
        System.out.println(hello == ("Hel"+lo).intern());

	}

}
