/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.design.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/** 
 * 测试 cglib 动态代理<p>
 * @author LingMin 
 * @date 2016-12-5<br>
 * @version 1.0<br>
 */
public class CGlibHoseeProxy {

	Object obj;
	 
	/***
	 * <p>
	 * @param target
	 * @return <p>
	 * Object
	 */
    public Object bind(final Object target){
        this.obj = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());//设置父类
        //设置回调机制
        enhancer.setCallback(new MethodInterceptor(){
            @Override
            public Object intercept(Object obj, Method method, Object[] args ,  MethodProxy proxy) throws Throwable{
                System.out.println("I'm proxy!");
                Object res = method.invoke(target, args);
                return res;
            }
        });
        return enhancer.create();
    }
}
