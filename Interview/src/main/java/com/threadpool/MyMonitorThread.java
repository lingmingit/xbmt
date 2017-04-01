/**
 * 
 */
package com.threadpool;

import java.util.concurrent.ThreadPoolExecutor;

/** 
 * 线程池实例：使用Executors和ThreadPoolExecutor
 *   还可以创建自己的 RejectedExecutionHandler 实现来处理不适合放在工作队列里的任务。
 * <p>
 * @author LingMin 
 * @date 2016-9-15<br>
 * @version 1.0<br>
 */
public class MyMonitorThread implements Runnable
{
    private ThreadPoolExecutor executor;
 
    private int seconds;
 
    private boolean run=true;
 
    public MyMonitorThread(ThreadPoolExecutor executor, int delay)
    {
        this.executor = executor;
        this.seconds=delay;
    }
 
    public void shutdown(){
        this.run=false;
    }
 
    @Override
    public void run()
    {
        while(run){
                System.out.println(
                    String.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
                        this.executor.getPoolSize(),
                        this.executor.getCorePoolSize(),
                        this.executor.getActiveCount(),
                        this.executor.getCompletedTaskCount(),
                        this.executor.getTaskCount(),
                        this.executor.isShutdown(),
                        this.executor.isTerminated()));
                try {
                    Thread.sleep(seconds*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
 
    }

}
