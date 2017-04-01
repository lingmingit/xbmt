package com.queue;


/***
 * 消息队列  测试 类<p>
 * @author LingMin 
 * @date 2016-6-28<br>
 * @version 1.0<br>
 */
public class ProducerConsumer {

	public static void main(String[] args) {  
        ShareData sd = new ShareData();  
        new Producer(sd).start();  
        new Consumer(sd).start();  
    }  
}
