package com.classinit;


/** 
 * 测试类初始化顺序 <p>
 * Java对象初始化顺序  链接：http://www.importnew.com/21832.html
 * @author LingMin 
 * @date 2016-10-17<br>
 * @version 1.0<br>
 */
public class Upper {
	String upperString;
	/***
	 * 构造方法
	 */
	public Upper() {
	  Initializer.initialize(this);
	}
}
