package com.classinit;


/** 
 * 测试类初始化顺序 <p>
 * Java对象初始化顺序  链接：http://www.importnew.com/21832.html
 * 
 * 如果有一个明确地赋值 null 的操作，执行流程会略有不同：当父类构造器完成后，在其余的构造器运行前，任何变量初始化都会执行（参见java语言规范12.5节）。
 * 在这种情况下，之前赋值给 lowerString 的字符串引用会再一次被赋予 null 。然后继续执行其余的函数构造，现在打印 lowerString 的值为: null 。
 * @author LingMin 
 * @date 2016-10-17<br>
 * @version 1.0<br>
 */
public class Lower extends Upper{
	String lowerString = null;
	 public Lower() {
		 super();
		 System.out.println("Upper:  " + upperString);
		 System.out.println("Lower:  " + lowerString);
	 }
	 public static void main(final String[] args) {
		 new Lower();
	 }
}
