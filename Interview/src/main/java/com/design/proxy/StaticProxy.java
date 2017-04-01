package com.design.proxy;


/***
 * 设计模式：代理模式测试
 * 静态代理
 * @author LingMin 
 * @date 2016-7-19<br>
 * @version 1.0<br>
 */
public class StaticProxy implements HelloWorld {

	 private HelloWorld helloWorld;
	 
	    public StaticProxy(HelloWorld helloWorld){
	        this.helloWorld = helloWorld;
	    }
	 
	    
	    public void print(){
	        System.out.println("Before Hello World!");
	        helloWorld.print();
	        System.out.println("After Hello World!");
	    }
}
