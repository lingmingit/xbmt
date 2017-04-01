/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.threads.tst;

/** 
 * 测试 线程 中的变量共享问题[编译器优化导致 变量可见性问题]<p>
 * 
 * my.oschina.net/hosee/blog/599884  高并发Java（3）：Java内存模型和线程安全
 * 
 * 代码很简单，v线程一直不断的在while循环中i++，直到主线程调用stop方法，改变了v线程中的stop变量的值使循环停止。
	看似简单的代码运行时就会出现问题。这个程序在 client 模式下是能停止线程做自增操作的，但是在 server 模式先将是无限循环。（server模式下JVM优化更多）
	64位的系统上面大多都是server模式，在server模式下运行：
	finish main
	true
	只会打印出这两句话，而不会打印出finish loop。可是能够发现stop的值已经是true了。

	这是JVM优化后的结果。如何避免呢？和指令重排一样，用volatile关键字。
 * @author LingMin 
 * @date 2016-9-17<br>
 * @version 1.0<br>
 */
public class VisibilityTest extends Thread{

	  private volatile boolean stop;
	  
	    public void run() {
	        int i = 0;
	        while(!stop) {
	            i++;
	        }
	        System.out.println("finish loop,i=" + i);
	    }
	 
	    public void stopIt() {
	        stop = true;
	    }
	 
	    public boolean getStop(){
	        return stop;
	    }
	    public static void main(String[] args) throws Exception {
	        VisibilityTest v = new VisibilityTest();
	        v.start();
	 
	        Thread.sleep(1000);
	        v.stopIt();
	        Thread.sleep(2000);
	        System.out.println("finish main");
	        System.out.println(v.getStop());
	    }

}
