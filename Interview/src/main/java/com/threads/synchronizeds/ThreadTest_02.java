/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.threads.synchronizeds;

/**
 * 测试  synchronized同步代码块 的 使用 <p>
 * @author LingMin 
 * @date 2016-9-15<br>
 * @version 1.0<br>
 */
public class ThreadTest_02 extends Thread{

	 private String lock ;
	    private String name;
	 
	    public ThreadTest_02(String name,String lock){
	        this.name = name;
	        this.lock = lock;
	    }
	 
	    @Override
	    public void run() {
	        synchronized (lock) {
	            for(int i = 0 ; i < 3 ; i++){
	                System.out.println(name + " run......");
	            }
	        }
	    }
	    /***
	     * 在main方法中我们创建了一个String对象lock，并将这个对象赋予每一个ThreadTest2线程对象的私有变量lock。
	     * 我们知道java中存在一个字符串池，那么这些线程的lock私有变量实际上指向的是堆内存中的同一个区域，
	     * 即存放main函数中的lock变量的区域，所以对象锁是唯一且共享的。线程同步！！
	     * 在这里synchronized锁住的就是lock这个String对象。
	     * <p>
	     * @param args <p>
	     * 
	     * void
	     */
	    public static void main(String[] args) {
	        String lock  = new String("test");
	        for(int i = 0 ; i < 5 ; i++){
	        	//在这里synchronized锁住的就是lock这个String对象。所以 实例化多个线程对象 都能保持 线程同步！！
	            new ThreadTest_02("ThreadTest_" + i , lock).start();
	        }
	    }

}
