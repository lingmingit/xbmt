package com.base;


/***
 * 测试线程  直接调用run() 方法<p>
 * 测试结果：可以正常执行
 * pong
 * ping
 * @author LingMin 
 * @date 2016-8-16<br>
 * @version 1.0<br>
 */
public class ThreadTest {

	/**             <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		
		Thread thead = new Thread(){
			public void run(){
				pong();
			}
		};
		thead.run();
		System.out.println("ping");
	}

	
	static void pong(){
		System.out.println("pong");
	}
}
