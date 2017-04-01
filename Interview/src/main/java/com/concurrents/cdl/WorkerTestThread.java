package com.concurrents.cdl;

import java.util.concurrent.CountDownLatch;


/***
 * CountDownLatch是一种简单的同步模式，它让一个线程可以等待一个或多个线程完成它们的工作从而避免对临界资源并发访问所引发的各种问题。
 * 下面借用别人的一段代码（我对它做了一些重构）来演示CountDownLatch是如何工作的。<p>
 * @author LingMin 
 * @date 2016-11-16<br>
 * @version 1.0<br>
 */
public class WorkerTestThread implements Runnable {
    private Worker worker;
    private CountDownLatch cdLatch;
 
    public WorkerTestThread(Worker worker, CountDownLatch cdLatch) {
        this.worker = worker;
        this.cdLatch = cdLatch;
    }
 
    @Override
    public void run() {
        worker.doWork();        // 让工人开始工作
        cdLatch.countDown();    // 工作完成后倒计时次数减1
    }

}
