package com.design.single;

/***
 * 单例模式：双重检验锁  <p>
 * @author LingMin 
 * @date 2016-12-7<br>
 * @version 1.0<br>
 */
public class Singleton2 {

	 private volatile static Singleton2 instance; //声明成 volatile
	    private Singleton2 (){}

	    /***
	     * 返回单例 实例对象<p>
	     * @return <p>
	     * Singleton
	     */
	    public synchronized  static Singleton2 getInstance() {
	     if (instance == null) {    			       //Single Checked
	    	 System.out.println("new0");
	         synchronized (Singleton2.class) {
	        	 System.out.println("new1");
	             if (instance == null) {                 //Double Checked
	            	 System.out.println("new2");
	                 instance = new Singleton2();
	             }
	         }
	     }
	     return instance;
	    }
}
