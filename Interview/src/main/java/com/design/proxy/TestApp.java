package com.design.proxy;


/***
 * 设计模式：代理模式测试
 * 
 * @author LingMin 
 * @date 2016-7-19<br>
 * @version 1.0<br>
 */
public class TestApp {
	/****
	 * 虽然我们自己写了Proxy，但是JDK绝对不会用这种方式实现，原因无他，就是太慢。
	 * 看到三个版本的代码，运行时间都在300ms以上，效率如此低的实现，如何能给开发者使用？
	 * 我拿JDK提供的Proxy和InvocationHandler自己写了一个简单的动态代理，耗时基本只在5ms左右。
	 * 所以，文章的内容仅供学习、研究，知识点很多，如果能把这篇文章里面的东西都弄懂，
	 * 对于个人水平、对于Java很多知识点的理解，绝对是一个非常大的提高。
	 * 
	 */

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//静态代理
		/*HelloWorld hw = new HelloWorldImpl();
		StaticProxy sp = new StaticProxy(hw);
		sp.print();*/
		
		//版本1：为一个静态代理动态生成一个代理类      动态代理,接口 和 执行代理处理逻辑是写死的 
		/*try {
			long start = System.currentTimeMillis();
		    HelloWorld helloWorld = (HelloWorld)ProxyVersion_0.newProxyInstance();
		    System.out.println("动态生成代理耗时：" + (System.currentTimeMillis() - start) + "ms");
		    helloWorld.print();
		    System.out.println();        
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		//版本二：为指定接口生成代理类               动态代理,接口是活，但是 执行代理处理逻辑是写死的
		/*try {
			long start = System.currentTimeMillis();
		    HelloWorld helloWorld = (HelloWorld)ProxyVersion_1.newProxyInstance(HelloWorld.class);
		    System.out.println("动态生成代理耗时：" + (System.currentTimeMillis() - start) + "ms");
		    helloWorld.print();
		    System.out.println();        
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		//版本三：让代理内容可复用              动态代理,接口是活，但是 执行代理处理逻辑也是活的
		try {
			 long start = System.currentTimeMillis();
			 	//被代理类接口对象
			    HelloWorld helloWorldImpl = new HelloWorldImpl();
			    //被代理类 处理逻辑 接口对象
			    MyInvocationHandler ih = new HelloInvocationHandler(helloWorldImpl);
			    //获取代理类接口对象
			    HelloWorld helloWorld = (HelloWorld)ProxyVersion_2.newProxyInstance(HelloWorld.class, ih);
			    System.out.println("动态生成代理耗时：" + (System.currentTimeMillis() - start) + "ms");
			    helloWorld.print();
			    System.out.println(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
