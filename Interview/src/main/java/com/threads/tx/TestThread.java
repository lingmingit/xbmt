/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.threads.tx;

/** 
 * 测试 多线程之间的 通讯<p>
 * @author LingMin 
 * @date 2016-9-8<br>
 * @version 1.0<br>
 */
public class TestThread {

	/**    
	 *          <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		TestThread test = new TestThread();
		//test.testWait();
		
		
		final TestThread test1 = test;
		
		Thread thead = new Thread(){
			public void run() {
				test1.testWait();
			}
		};
		thead.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("执行完成");
//		try {
//			thead.wait(1000);
//			System.out.println("等待完成");
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	
	
	/**
	 * 测试等待<p>
	 * synchronized
	 *  <p>
	 * void
	 */
	public void testWait(){
		try {
			Thread.sleep(5000);
			System.out.println("发送邮件");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
