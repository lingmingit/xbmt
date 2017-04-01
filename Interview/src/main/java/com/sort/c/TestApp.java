/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.sort.c;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** <p>
 * @author LingMin 
 * @date 2017-2-21<br>
 * @version 1.0<br>
 */
public class TestApp {

	/**             <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		TestApp test = new TestApp();
		
		List<BookBean> list = new ArrayList<BookBean>();
		BookBean book = new BookBean("111" , 1);
		list.add(book);
		book = new BookBean("333" , 3);
		list.add(book);
		book = new BookBean("222" , 2);
		list.add(book);
		System.out.println("list排序前：");
		test.displayList(list);
		
		Collections.sort(list);
		System.out.println("list排序后：");
		test.displayList(list);
		char a = '中';
		
		System.out.println("a="+a);
		
	}

	
	
	private void displayList(List<BookBean> list){
		for(BookBean book : list){
			System.out.println("name="+book.getName());
		}
	}
}
