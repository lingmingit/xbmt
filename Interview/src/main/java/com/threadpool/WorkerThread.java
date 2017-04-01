/**
 * 
 */
package com.threadpool;

/** 
 * 线程池实例：使用Executors和ThreadPoolExecutor
 * 测试
 * <p>
 * @author LingMin 
 * @date 2016-9-15<br>
 * @version 1.0<br>
 */
public class WorkerThread implements Runnable {
 
    private String command;
 
    public WorkerThread(String s){
        this.command=s;
    }
 
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" Start. Command = "+command);
        processCommand();
        System.out.println(Thread.currentThread().getName()+" End.");
    }
 
    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
 
    @Override
    public String toString(){
        return this.command;
    }

}
