package com.concurrents.cdl;


/***
 * CountDownLatch是一种简单的同步模式，它让一个线程可以等待一个或多个线程完成它们的工作从而避免对临界资源并发访问所引发的各种问题。
 * 下面借用别人的一段代码（我对它做了一些重构）来演示CountDownLatch是如何工作的。<p>
 * @author LingMin 
 * @date 2016-11-16<br>
 * @version 1.0<br>
 */
public class Worker {

	 private String name;        // 名字
	    private long workDuration;  // 工作持续时间
	 
	    /**
	     * 构造器
	     */
	    public Worker(String name, long workDuration) {
	        this.name = name;
	        this.workDuration = workDuration;
	    }
	 
	    /**
	     * 完成工作
	     */
	    public void doWork() {
	        System.out.println(name + " begins to work...");
	        try {
	            Thread.sleep(workDuration); // 用休眠模拟工作执行的时间
	        } catch(InterruptedException ex) {
	            ex.printStackTrace();
	        }
	        System.out.println(name + " has finished the job...");
	    }

}
