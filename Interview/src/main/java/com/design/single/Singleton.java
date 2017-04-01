/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.design.single;

/** 
 * 单例模式：懒汉式，线程不安全<p>
 * @author LingMin 
 * @date 2016-12-7<br>
 * @version 1.0<br>
 */
public class Singleton {

    private static Singleton instance;
    private Singleton (){}

    /***
     * 返回单例 实例对象<p>
     * @return <p>
     * Singleton
     */
    public static Singleton getInstance() {
     if (instance == null) {
    	 System.out.println("new");
         instance = new Singleton();
     }
     return instance;
    }
}
