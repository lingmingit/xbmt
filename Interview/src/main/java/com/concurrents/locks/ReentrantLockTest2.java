/**
 * 
 */
package com.concurrents.locks;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试 ReentrantLockTest的使用 <p>
 * 
 * 普通的lock.lock()是不能响应中断的，lock.lockInterruptibly()能够响应中断。
     我们模拟出一个死锁现场，然后用中断来处理死锁
 * @author LingMin 
 * @date 2016-9-21<br>
 * @version 1.0<br>
 */
public class ReentrantLockTest2 implements Runnable{

	 public static ReentrantLock lock1 = new ReentrantLock();
	 public static ReentrantLock lock2 = new ReentrantLock();
	 int lock;
	 
     public ReentrantLockTest2(int lock){
        this.lock = lock;
     }
	 
	    @Override
	    public void run(){
	        try{
	            if (lock == 1){
	                lock1.lockInterruptibly();
	            	//lock1.lock();
	                try{
	                    Thread.sleep(500);
	                }
	                catch (Exception e){
	                }
	                lock2.lockInterruptibly();
	               // lock2.lock();
	            }
	            else{
	                lock2.lockInterruptibly();
	            	// lock2.lock();
	                try{
	                    Thread.sleep(500);
	                } catch (Exception e){
	                }
	                lock1.lockInterruptibly();
	               // lock1.lock();
	            }
	        }catch (Exception e){
	        }
	        finally{
	            if (lock1.isHeldByCurrentThread()){
	                lock1.unlock();
	            }
	            if (lock2.isHeldByCurrentThread()){
	                lock2.unlock();
	            }
	            System.out.println(Thread.currentThread().getId() + ":线程退出");
	        }
	    }
	 
	    public static void main(String[] args) throws InterruptedException{
	    	ReentrantLockTest2 t1 = new ReentrantLockTest2(1);
	    	ReentrantLockTest2 t2 = new ReentrantLockTest2(2);
	        Thread thread1 = new Thread(t1);
	        Thread thread2 = new Thread(t2);
	        thread1.start();
	        thread2.start();
	        Thread.sleep(1000);
	        DeadlockChecker.check();
	    }
	 
	    static class DeadlockChecker{
	        private final static ThreadMXBean mbean = ManagementFactory.getThreadMXBean();
	        final static Runnable deadlockChecker = new Runnable(){
	            @Override
	            public void run(){
	                while (true){
	                    long[] deadlockedThreadIds = mbean.findDeadlockedThreads();
	                    if (deadlockedThreadIds != null){
	                        ThreadInfo[] threadInfos = mbean.getThreadInfo(deadlockedThreadIds);
	                        for (Thread t : Thread.getAllStackTraces().keySet()){
	                            for (int i = 0; i < threadInfos.length; i++){
	                                if(t.getId() == threadInfos[i].getThreadId()){
	                                    t.interrupt();
	                                }
	                            }
	                        }
	                    }
	                    try{
	                        Thread.sleep(5000);
	                    }catch (Exception e){
	                    }
	                }
	 
	            }
	        };
	 
	        public static void check()
	        {
	            Thread t = new Thread(deadlockChecker);
	            t.setDaemon(true);
	            t.start();
	        }
	    }
}
