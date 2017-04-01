/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.base;

import java.util.HashSet;
import java.util.Set;

/** 
 * 测试 Set 对应 hashCode() equals() 方法<p>
 * Set 是不能添加重复的元素，主要跟 hashCode() 、  equals() 有关系
 * 任意注释掉 其中一个，都是不重复的元素
 * @author LingMin 
 * @date 2016-8-16<br>
 * @version 1.0<br>
 */
public class SetTest {
	
	
	/***
	 * 重写hashCode 方法
	 */
	public int hashCode() {
		return 1;
	}
	
	/***
	 * 重写equals 方法
	 */
	public boolean equals(Object obj) {
		return true;
	}

	/**             <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		Set set = new HashSet();
		set.add(new SetTest());
		set.add(new SetTest());
		set.add(new SetTest());
		System.out.println(set.size());
	}

}
