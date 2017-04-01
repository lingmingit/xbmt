/**
 * 
 */
package com.concurrents.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 用AtomicStampedReference解决ABA问题 <p>
 * http://blog.hesey.net/2011/09/resolve-aba-by-atomicstampedreference.html
 * 
 * 以上就是由于ABA问题带来的隐患，各种乐观锁的实现中通常都会用版本戳version来对记录或对象标记，
 * 避免并发操作带来的问题，在Java中，AtomicStampedReference<E>也实现了这个作用，
 * 它通过包装[E,Integer]的元组来对对象标记版本戳stamp，从而避免ABA问题，
 * 例如下面的代码分别用AtomicInteger和AtomicStampedReference来对初始值为100的原子整型变量进行更新，
 * AtomicInteger会成功执行CAS操作，而加上版本戳的AtomicStampedReference对于ABA问题会执行CAS失败：
 * 
 * @author LingMin 
 * @date 2016-9-21<br>
 * @version 1.0<br>
 */
public class ABATest {

	//默认 CAS 原子变量操作，就存在ABA问题
	private static AtomicInteger atomicInt = new AtomicInteger(100);
	//初始化变量值 并 初始化版本号，
    private static AtomicStampedReference atomicStampedRef = new AtomicStampedReference(100, 0);

    /***
     * 
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
    	
    		/***
    		 * 线程1
    		 */
            Thread intT1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                            atomicInt.compareAndSet(100, 101);
                            atomicInt.compareAndSet(101, 100);
                    }
            });
            /**
             * 线程2
             */
            Thread intT2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                            try {
                                    TimeUnit.SECONDS.sleep(1);
                            } catch (InterruptedException e) {
                            }
                            boolean c3 = atomicInt.compareAndSet(100, 101);
                            System.out.println(c3); // true
                    }
            });

            intT1.start();
            intT2.start();
            intT1.join();
            intT2.join();

            Thread refT1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                    	 int stamp = atomicStampedRef.getStamp();
                    	 System.out.println("refT1-1.stamp="+stamp);
                            try {
                                    TimeUnit.SECONDS.sleep(1);
                            } catch (InterruptedException e) {
                            }
                            atomicStampedRef.compareAndSet(100, 101, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
                            System.out.println("refT1-2.stamp="+atomicStampedRef.getStamp());
                            atomicStampedRef.compareAndSet(101, 100, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
                            System.out.println("refT1-3.stamp="+atomicStampedRef.getStamp());
                    }
            });

            Thread refT2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                            int stamp = atomicStampedRef.getStamp();
                            System.out.println("refT2-1.stamp="+stamp);
                            try {
                                    TimeUnit.SECONDS.sleep(2);
                            } catch (InterruptedException e) {
                            }
                            boolean c3 = atomicStampedRef.compareAndSet(100, 101, stamp, stamp + 1);
                            System.out.println(c3); // false
                            System.out.println("refT2-2.stamp="+atomicStampedRef.getStamp());
                    }
            });

            refT1.start();
            refT2.start();
    }

}
