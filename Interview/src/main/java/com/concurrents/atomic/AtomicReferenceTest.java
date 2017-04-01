/**
 * 
 */
package com.concurrents.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 测试AtomicReference 它可以用来封装任意类型的数据 <p>
 * http://blog.hesey.net/2011/09/resolve-aba-by-atomicstampedreference.html
 * 
 * 
 * @author LingMin 
 * @date 2016-9-21<br>
 * @version 1.0<br>
 */
public class AtomicReferenceTest {
	//测试AtomicReference 它可以用来封装 String 类型
	public final static AtomicReference<String> atomicString = new AtomicReference<String>("hosee");
	/***
	 * 
	 * @param args
	 */
    public static void main(String[] args){
    	
        for (int i = 0; i < 10; i++){
            final int num = i;
            new Thread() {
            	
                public void run() {
                    try{
                        Thread.sleep(Math.abs((int)Math.random()*100));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    if (atomicString.compareAndSet("hosee", "ztk")){
                        System.out.println(Thread.currentThread().getId() + "Change value="+atomicString.get());
                    }else {
                        System.out.println(Thread.currentThread().getId() + "Failed");
                    }
                };
            }.start();
        }
    }

}
