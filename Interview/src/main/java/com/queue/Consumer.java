package com.queue;




/***
 * 消息队列消费者<p>
 * @author LingMin 
 * @date 2016-6-28<br>
 * @version 1.0<br>
 */
public class Consumer extends Thread{

	 private ShareData sd;  
	    public Consumer(ShareData sd){  
	        this.sd = sd;  
	    }  
	    @Override 
	    public void run() {  
	        for(int i = 0; i < 20; i++){  
	            sd.getArray();  
	            try {  
	                Thread.sleep((int)(Math.random()*200));  
	            } catch (InterruptedException e) {  
	                e.printStackTrace();  
	            }  
	        }  
	    }  
}
