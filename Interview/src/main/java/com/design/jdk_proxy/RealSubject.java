/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.design.jdk_proxy;

/**
 * 定义实现类  <p>
 * @author LingMin 
 * @date 2016-8-10<br>
 * @version 1.0<br>
 */
public class RealSubject implements Subject {

	/* (non-Javadoc)
	 * @see com.proxy.Subject#rent()
	 */
	@Override
	public void rent() {
		System.out.println("I want to rent my house");
	}

	/* (non-Javadoc)
	 * @see com.proxy.Subject#hello(java.lang.String)
	 */
	@Override
	public void hello(String str) {
		System.out.println("hello: " + str);
	}

}
