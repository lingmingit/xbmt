package com.threads.synchronizeds;


/***
 * 测试同步  synchronized 方法 的 使用<p>
 * @author LingMin 
 * @date 2016-9-15<br>
 * @version 1.0<br>
 */
public class ThreadTest_01  implements Runnable{

	   /***
	    * 线程执行方法
	    */
	    public synchronized void run() {
	        for(int i = 0 ; i < 3 ; i++){
	            System.out.println(Thread.currentThread().getName() + "run......");
	        }
	    }
	    /**
	     * <p>
	     * @param args <p>
	     * void
	     */
	    public static void main(String[] args) {
	    	ThreadTest_01 ttest_01 = new ThreadTest_01();
	        for(int i = 0 ; i < 5 ; i++){
	            //new Thread(new ThreadTest_01(),"Thread_" + i).start();//这个会实例化多个对象
	        	new Thread(ttest_01,"Thread_" + i).start();//这个只有一个对象， 就可以实现同步
	        }
	    }

}
