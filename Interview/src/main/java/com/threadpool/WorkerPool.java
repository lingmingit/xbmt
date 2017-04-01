/**
 * 
 */
package com.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/** 
 * 线程池实例：使用Executors和ThreadPoolExecutor
 *   还可以创建自己的 RejectedExecutionHandler 实现来处理不适合放在工作队列里的任务。
 * <p>
 * @author LingMin 
 * @date 2016-9-15<br>
 * @version 1.0<br>
 */
public class WorkerPool {

	/**
	 * @param args
	 */
	public static void main(String args[]) throws InterruptedException{
        //RejectedExecutionHandler implementation
        RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();
        //Get the ThreadFactory implementation to use
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        //creating the ThreadPoolExecutor
        //请注意：在初始化 ThreadPoolExecutor 时，初始线程池大小设为2、最大值设为4、工作队列大小设为2。
        //所以，如果当前有4个任务正在运行而此时又有新任务提交，工作队列将只存储2个任务和其他任务将交由RejectedExecutionHandlerImpl 处理。
        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2), threadFactory, rejectionHandler);
        //start the monitoring thread
        MyMonitorThread monitor = new MyMonitorThread(executorPool, 3);
        Thread monitorThread = new Thread(monitor);
        monitorThread.start();
        //submit work to the thread pool
        for(int i=0; i<10; i++){
            executorPool.execute(new WorkerThread("cmd"+i));
        }
 
        Thread.sleep(30000);
        //shut down the pool
        executorPool.shutdown();
        //shut down the monitor thread
        Thread.sleep(5000);
        monitor.shutdown();
 
    }

}
