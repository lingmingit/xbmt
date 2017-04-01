/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.design.jdk_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/** 
 * 动态调用类<p>
 * 
 * 
 * 
 * http://rejoy.iteye.com/blog/1627405  JDK动态代理实现原理
 * http://blog.csdn.net/mhmyqn/article/details/48474815  细说JDK动态代理的实现原理 
 * 
 * @author LingMin 
 * @date 2016-8-10<br>
 * @version 1.0<br>
 */
public class Client {

	
	/***
	 * <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args){
        //    我们要代理的真实对象
        Subject realSubject = new RealSubject();
        //    我们要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法的
        InvocationHandler handler = new DynamicProxy(realSubject);
        /*
         * 通过Proxy的newProxyInstance方法来创建我们的代理对象，我们来看看其三个参数
         * 第一个参数 handler.getClass().getClassLoader() ，我们这里使用handler这个类的ClassLoader对象来加载我们的代理对象
         * 第二个参数realSubject.getClass().getInterfaces()，我们这里为代理对象提供的接口是真实对象所实行的接口，表示我要代理的是该真实对象，这样我就能调用这组接口中的方法了
         * 第三个参数handler， 我们这里将这个代理对象关联到了上方的 InvocationHandler 这个对象上
         */
        Subject subject = (Subject)Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject
                .getClass().getInterfaces(), handler);
 
        System.out.println(subject.getClass().getName());
        subject.rent();
        subject.hello("world");
        
        testGenerateProxyClass();
    }

	 /** 
     * 把代理类的字节码写到硬盘上 
     * @param path 保存路径 
     */  
	public static void testGenerateProxyClass() {  
        ProxyGeneratorUtils.writeProxyClassToHardDisk("F:/$Proxy11.class");  
    }  
}
