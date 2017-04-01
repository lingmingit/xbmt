package com.design.proxy;

import java.lang.reflect.Method;


/**
 * 设计模式：代理模式测试<p>
 * @author LingMin 
 * @date 2016-12-5<br>
 * @version 1.0<br>
 */
public class HelloInvocationHandler implements MyInvocationHandler {

	//
	private Object obj;
	 
    public HelloInvocationHandler(Object obj){
        this.obj = obj;
    }
 
	@Override
	public void invoke(Object proxy, Method method) throws Exception {
		 System.out.println("Before Hello World!");
	        try{
	            method.invoke(obj, new Object[]{});
	        } catch (Exception e){
	            e.printStackTrace();
	        }
	        System.out.println("After Hello World!");

	}

}
