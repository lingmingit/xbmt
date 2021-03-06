/**
 * 
 */
package com.concurrents.locks;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试 ReentrantLockTest的使用 <p>
 * 
 * 
 * @author LingMin 
 * @date 2016-9-21<br>
 * @version 1.0<br>
 */
public class ReentrantLockTest  implements Runnable
{
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;
 
    @Override
    public void run(){
        for (int j = 0; j < 10000000; j++){
            lock.lock();
            try
            {
                i++;
            }finally{
                lock.unlock();
            }
        }
    }
 
    public static void main(String[] args) throws InterruptedException{
    	ReentrantLockTest test = new ReentrantLockTest();
        Thread t1 = new Thread(test);
        Thread t2 = new Thread(test);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
 

}
