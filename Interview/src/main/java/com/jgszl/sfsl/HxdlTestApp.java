/**
 * &lt;p&gt;
 * copyright &amp;copy;  2017, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.jgszl.sfsl;

import java.util.Random;

/** 
 * 测试环形队列的使用<p>
 * @author LingMin 
 * @date 2017年3月16日<br>
 * @version 1.0<br>
 */
public class HxdlTestApp {

	/**             <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		
		//testHxdlTest();//测试1
		testHxdlTest2();//测试2
	}
	
	/****
	 * 测试 变量使用  AtomicInteger <p>
	 *  <p>
	 * void
	 */
	public static void testHxdlTest2(){
		//启动环形队列 时钟
		HxdlTest2 hxdlTest = new HxdlTest2();
		hxdlTest.startTimer();//启动环形队列 滚动线程
		
		String[] uidArray = {"aaa" , "bbb" , "ccc" ,"ddd"};// , "aaa" , "ccc"
		String[] uidArray2 = {"aaa", "bbb" , "ccc" , "ddd" , "eee" , "ddd"};
		//第二种 写法
		Thread thread1 = new Thread(() -> {
			System.out.println("putUid start1");
			for(int i = 0 ; i < uidArray.length ; i++){
				hxdlTest.putUid(uidArray[i]);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("putUid end1");
			//打印map
			hxdlTest.displayMap(hxdlTest.getMaps());
		});
		thread1.setName("thread1");
		thread1.start();
		//暂停20秒钟 
		try {
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Thread thread2 =new Thread(() -> {
			System.out.println("putUid start2");
			for(int i = 0 ; i < uidArray2.length ; i++){
				hxdlTest.putUid(uidArray2[i]);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("putUid end2");
			//打印map
			hxdlTest.displayMap(hxdlTest.getMaps());
		});
		thread2.setName("thread2");
		thread2.start();
	}
	
	/***
	 * 测试  变量 使用 原始类型，目前存在 多线程并发问题<p>
	 *  <p>
	 * void
	 */
	public static void testHxdlTest(){
		//启动环形队列 时钟
		HxdlTest.getInstance().startTimer();
		String[] uidArray = {"aaa" , "bbb" , "ccc" ,"ddd"};// , "aaa" , "ccc"
		Random random = new Random();
		//第一种写法 线程
		/*Runnable run = () -> {
			for(int i = 0 ; i < 100 ; i++){
				int num = random.nextInt(10);
				String uid = "uid-"+String.valueOf(num);
				HxdlTest.getInstance().putUid(uid);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Thread thread = new Thread(run);
		thread.start();*/
		
		
		//第二种 写法
		Thread thread1 = new Thread(() -> {
			System.out.println("putUid start1");
			for(int i = 0 ; i < uidArray.length ; i++){
				//int num = random.nextInt(10);
				//String uid = "uid-"+String.valueOf(num);
				HxdlTest.getInstance().putUid(uidArray[i]);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("putUid end1");
			//打印map
			HxdlTest.getInstance().displayMap(HxdlTest.getInstance().getMaps());
		});
		thread1.setName("thread1");
		thread1.start();
		//暂停20秒钟 
		try {
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String[] uidArray2 = {"aaa", "bbb" , "ccc" , "ddd" , "eee" , "ddd"};
		//String[] uidArray2 = {"eee", "fff" , "ggg"};
		
		Thread thread2 =new Thread(() -> {
			System.out.println("putUid start2");
			for(int i = 0 ; i < uidArray2.length ; i++){
				//int num = random.nextInt(10);
				//String uid = "uid-"+String.valueOf(num);
				HxdlTest.getInstance().putUid(uidArray2[i]);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("putUid end2");
			//打印map
			HxdlTest.getInstance().displayMap(HxdlTest.getInstance().getMaps());
		});
		thread2.setName("thread2");
		thread2.start();
	}
	

}
