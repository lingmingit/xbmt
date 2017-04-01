/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.test;

/** 
 * 1. System.exit(0)会跳过finally块的执行
 * 2. String str = “Hello”;其中str是一个字符串对象 跟C++不同的是，Java里的变量要么是基础类型，要么是引用。变量不可能是对象。
 * <p>
 * @author LingMin 
 * @date 2016-9-15<br>
 * @version 1.0<br>
 */
public class TestApp2 {

	/**             <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * 增加如下代码  finally 就可以打印出来，否则不能打印
		 System.setSecurityManager(new SecurityManager() {
	        @Override
	        public void checkExit(int status) {
	            throw new ThreadDeath();
	        }
	    });
	    try {
	        System.exit(0);
	    } finally {
	        System.out.println("In the finally block");
	    }*/
	    TestApp2 test = new TestApp2();
	   
	    
	    final StringBuilder sb = new StringBuilder();
	    sb.append("Hello"); // 这个引用是final类型的，而不是这个实例。
	    
	    //sb = new StringBuilder();
	    test.testMethod(sb);
	    System.out.println("sb="+sb.toString());
	    
	    String str;
	    //System.out.println("str="+str);
	    System.out.println("5"+2);
	    System.out.println(Integer.MAX_VALUE + 1 < 1);
	    //Double.NaN
	    System.out.println("Double.NaN="+Double.NaN);//打印结果 NaN
	    double i = Float.NaN , j = 1 ;//Double.NaN;
	    System.out.println(i > j || i <= j);//false
	}
	
	
	
	public void testMethod(StringBuilder sb){
		sb = new StringBuilder();
		sb.append("aaa");
		System.out.println("sb="+sb.toString());
		
	}

}
