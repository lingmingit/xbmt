/**
 * 
 */
package com.design.proxy;

import java.lang.reflect.Method;

/***
 * 设计模式：代理模式测试
 * 
 * @author LingMin 
 * @date 2016-7-19<br>
 * @version 1.0<br>
 */
public interface MyInvocationHandler {

	void invoke(Object proxy, Method method) throws Exception;
}
