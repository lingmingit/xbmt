package com.qhb.four;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


/** 
 * Java模拟抢红包应用
 * http://www.lai18.com/content/8811447.html
 * <p>
 * @author LingMin 
 * @date 2016-10-10<br>
 * @version 1.0<br>
 */
public class QHBTest4 {

	 public static ConcurrentLinkedQueue<SpringGift> queue;
	 public static SpringGift currGift;
	 public static AtomicInteger count = new AtomicInteger();
	 
	
	 static class myThread implements Runnable{
		  public void run(){
			  handleEvent();
		  }
	 }
	 /***
	  * 
	  * @param args
	  * @throws Exception
	  */
	public static void main(String[] args) throws Exception {
		queue = new ConcurrentLinkedQueue<SpringGift>();
		for (int i = 0; i < 10; i++) {
			SpringGift gift = new SpringGift();
			gift.setRole("role" + i);
			gift.setGift(new AtomicInteger(50));
			queue.add(gift);
		}
		System.out.println("queue.size="+queue.size());
		myThread mythread = new myThread();
		for (int i = 0; i < 10; i++) {
			new Thread(mythread).start();
		}
		Thread.sleep(2000);
		System.err.println("总共收到" + count.get());
	}
	
	
	/*
	 * 第一种方法 使用 synchronized 同步关键字处理
	 private static SpringGift getGift() {
		// 防止多条线程同时弹出队首
		synchronized (queue) {// 若没有加锁，打印的count总数不对！！！！
			//if (currGift == null || currGift.getRemainCount() <= 0) {
				currGift = queue.poll();//自动弹出队首元素，必须做好同步机制
			//}
			if(currGift != null){
				System.out.println("obj="+currGift.toString());
			}
		}
		return currGift;
	}*/
	
	 public static ReentrantLock lock = new ReentrantLock();
	/*
	 * 第二种方法 使用 Lock 同步关键字处理*/
	 private static SpringGift getGift() {
		// 防止多条线程同时弹出队首
		// 若没有加锁，打印的count总数不对！！！！
			lock.lock();
            try{
            	currGift = queue.poll();//自动弹出队首元素，必须做好同步机制
            }finally{
                lock.unlock();
            }
			if(currGift != null){
				System.out.println("obj="+currGift.toString());
			}
		return currGift;
	}

	
	public static void handleEvent() {
		try {
			SpringGift obj = getGift();
			//System.out.println("obj="+obj.toString());
			if (obj == null || obj.getRemainCount() <= 0) {
				System.err.println("没有了");
				return;
			}
			if (obj != null && obj.getGift().getAndDecrement() > 0) {//递减方法
				System.err.println("抢到一个红包");
				count.getAndIncrement();
			}
			Thread.sleep(500);// 模拟处理其他操作
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
