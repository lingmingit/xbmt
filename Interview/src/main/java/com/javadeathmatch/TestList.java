/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.javadeathmatch;

import java.util.ArrayList;
import java.util.List;

/** 
 * 4、利用两个花括号进行初始化<p>
 * 利用两个花括号进行初始化是有另一套初始化过程的。这里，我们用了一个匿名类来初始化一个List，当要打印NAMES时，实际上打印出来的是null，这是因为初始化程序尚未完成，此时的list是空的。
 * 效率比较低
 * @author LingMin 
 * @date 2017-3-16<br>
 * @version 1.0<br>
 */
public class TestList {

	private static final List<String> names = new ArrayList<String>(){{
		add("aaa");
		add("bbb");
		System.out.println(names);
	}};
	
	private  List<String> tl = new ArrayList<>(); 
	
	/**             <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestList test = new TestList();
	}

}
