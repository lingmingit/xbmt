package com.design.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/***
 * 测试 单例模式<p>
 * @author LingMin 
 * @date 2016-12-7<br>
 * @version 1.0<br>
 */
public class SingleTestApp {

	/**             <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		SingleTestApp testApp = new SingleTestApp();
		//testApp.testInnerClass();//测试 内部类 实现的单例模式
		//testApp.testSingleClassReflectAttack();//通过JAVA的反射机制来“攻击”单例模式
		//testApp.testEnumSingletonClass();//通过枚举产生 单例模式
		//testApp.testEnumSingletonClassReflectAttack();//通过JAVA的反射机制来“攻击”单例模式
		//testApp.testSingleton();//单例模式：懒汉式，线程不安全
		//testApp.testSingleton1();//懒汉式，线程安全
		
		testApp.testSingleton2();//双重检验锁
	}
	
	
	
	/**
	 * 单例模式：双重检验锁<p>
	 * 
	 *  <p>
	 * void
	 */
	public void testSingleton2(){
//		Singleton2 ingleclass = Singleton2.getInstance();
//		Singleton2 ingleclass2 = Singleton2.getInstance();
//		System.out.println(ingleclass == ingleclass2);
		//创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(20);  
		  for (int i = 0; i < 10; i++) {  
		   final int index = i;  
		   fixedThreadPool.execute(new Runnable() {  
		    public void run() {  
		     try {  
		    	 Singleton2 ingleclass = Singleton2.getInstance();
		    	 System.out.println(Thread.currentThread().getName()+"="+ingleclass);
		         Thread.sleep(100);  
		     } catch (InterruptedException e) {  
		         e.printStackTrace();  
		     }  
		    }  
		   });  
		}
	  fixedThreadPool.shutdown();
	}
	
	/**
	 * 单例模式：懒汉式，线程安全<p>
	 * 虽然做到了线程安全，并且解决了多实例的问题，但是它并不高效。因为在任何时候只能有一个线程调用 getInstance() 方法。
	 * 但是同步操作只需要在第一次调用时才被需要，即第一次创建单例实例对象时。这就引出了双重检验锁。
	 *  <p>
	 * void
	 */
	public void testSingleton1(){
//		Singleton1 ingleclass = Singleton1.getInstance();
//		Singleton1 ingleclass2 = Singleton1.getInstance();
//		System.out.println(ingleclass == ingleclass2);
		//创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(20);  
		  for (int i = 0; i < 10; i++) {  
		   final int index = i;  
		   fixedThreadPool.execute(new Runnable() {  
		    public void run() {  
		     try {  
		    	 Singleton1 ingleclass = Singleton1.getInstance();
		    	 System.out.println(Thread.currentThread().getName()+"="+ingleclass);
		         Thread.sleep(100);  
		     } catch (InterruptedException e) {  
		         e.printStackTrace();  
		     }  
		    }  
		   });  
		}
	  fixedThreadPool.shutdown();
	}
	
	/**
	 * 单例模式：懒汉式，线程不安全<p>
	 * 这段代码简单明了，而且使用了懒加载模式，但是却存在致命的问题。
	 * 当有多个线程并行调用 getInstance() 的时候，就会创建多个实例。
	 * 也就是说在多线程下不能正常工作。
	 *  <p>
	 * void
	 */
	public void testSingleton(){
//		Singleton ingleclass = Singleton.getInstance();
//		Singleton ingleclass2 = Singleton.getInstance();
//		System.out.println(ingleclass == ingleclass2);
		//创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);  
		  for (int i = 0; i < 10; i++) {  
		   final int index = i;  
		   fixedThreadPool.execute(new Runnable() {  
		    public void run() {  
		     try {  
		    	 Singleton ingleclass = Singleton.getInstance();
		    	 System.out.println(Thread.currentThread().getName()+"="+ingleclass);
		         Thread.sleep(100);  
		     } catch (InterruptedException e) {  
		         e.printStackTrace();  
		     }  
		    }  
		   });  
		}
	}
	
	
	
	/***
	 * 通过枚举产生 单例模式<p>
	 * 单元素的枚举类型已经成为实现Singleton模式的最佳方法。
	 *  <p>
	 * void
	 */
	public void testEnumSingletonClass(){
		SingletonEnumClass s1 = SingletonEnumClass.INSTANCE;
		SingletonEnumClass s2 = SingletonEnumClass.INSTANCE;
		s1.test();
		System.out.println(s1 == s2);
	}
	
	/**
	 * 但这都是基于一个条件：确保不会通过反射机制调用私有的构造器。
	      这里举个例子，通过JAVA的反射机制来“攻击”单例模式：<p>
	 *  <p>
	 * void
	 */
	public void testEnumSingletonClassReflectAttack(){
		try {
			Class<?> classType = SingletonEnumClass.class;  
	        Constructor<?> c = classType.getDeclaredConstructor(null);
			c.setAccessible(true);  
			SingletonEnumClass e1 = (SingletonEnumClass)c.newInstance();  
			SingletonEnumClass e2 = SingletonEnumClass.INSTANCE;  
	        System.out.println(e1==e2);  //false
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}  
	}
	
	/**
	 * 但这都是基于一个条件：确保不会通过反射机制调用私有的构造器。
	      这里举个例子，通过JAVA的反射机制来“攻击”单例模式：<p>
	 *  <p>
	 * void
	 */
	public void testSingleClassReflectAttack(){
		try {
			Class<?> classType = SingleClass.class;  
	        Constructor<?> c = classType.getDeclaredConstructor(null);
			c.setAccessible(true);  
	        SingleClass e1 = (SingleClass)c.newInstance();  
	        SingleClass e2 = SingleClass.getInstance();  
	        System.out.println(e1==e2);  //false
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}  
	}
	
	
	
	
	/***
	 * 测试 内部类 实现的单例模式<p>
	 *  <p>
	 * void
	 */
	public void testInnerClass(){
		// 
		//SingleClass ingleclass = SingleClass.getInstance();
		//SingleClass ingleclass2 = SingleClass.getInstance();
		//System.out.println(ingleclass == ingleclass2);
		//测试多线程加载
		Runnable runnable = new Runnable() {
			public void run() {
				SingleClass ingleclass = SingleClass.getInstance();
				System.out.println(Thread.currentThread().getName()+"="+ingleclass);
			}
		};
		
		/***
		 * 
		 */
		for(int i = 0 ; i < 10 ; i++){
			Thread thread = new Thread(runnable);
			thread.start();
		}
	}

}
