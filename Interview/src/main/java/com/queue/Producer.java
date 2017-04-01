package com.queue;


/***
 * 消息生产者<p>
 * @author LingMin 
 * @date 2016-6-28<br>
 * @version 1.0<br>
 */
public class Producer extends Thread{  
    
 private ShareData sd;  
 public Producer(ShareData sd){  
     this.sd = sd;  
 }  
    
 @Override 
 public void run() {  
     for(int i = 0; i < 20; i++){  
         int product = (int)(Math.random()*1000);  
         sd.setArray(product);  
         try {  
             Thread.sleep((int)(Math.random()*200));  
         } catch (InterruptedException e) {  
             e.printStackTrace();  
         }  
     }  
 }  
}  
