package com.concurrents.queue.waitnotify;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



/***
 * 基于wait和notify的实现  生产者-消费者并发模型
 * @author LingMin 
 * @date 2016-11-16<br>
 * @version 1.0<br>
 */
public class Test06 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		   List<Task> buffer = new ArrayList<>(Constants.MAX_BUFFER_SIZE);
	        ExecutorService es = Executors.newFixedThreadPool(Constants.NUM_OF_CONSUMER + Constants.NUM_OF_PRODUCER);
	        
	        //2个生产者  线程：2、1、
	        for(int i = 1; i <= Constants.NUM_OF_PRODUCER; ++i) {
	            es.execute(new Producer(buffer));
	        }
	        //3个消费者 线程：3、4、5
	        for(int i = 1; i <= Constants.NUM_OF_CONSUMER; ++i) {
	            es.execute(new Consumer(buffer));
	        }
	 }

}


/**
 * 公共常量
 * @author 骆昊
 *
 */
class Constants {
    public static final int MAX_BUFFER_SIZE = 10;
    public static final int NUM_OF_PRODUCER = 2;
    public static final int NUM_OF_CONSUMER = 3;
}
 
/**
 * 工作任务
 * @author 骆昊
 *
 */
class Task {
    private String id;  // 任务的编号
 
    public Task() {
        id = UUID.randomUUID().toString();
    }
 
    @Override
    public String toString() {
        return "Task[" + id + "]";
    }
}
 
/**
 * 消费者
 * @author 骆昊
 *
 */
class Consumer implements Runnable {
    private List<Task> buffer;
 
    public Consumer(List<Task> buffer) {
        this.buffer = buffer;
    }
 
    @Override
    public void run() {
        while(true) {
            synchronized(buffer) {
                while(buffer.isEmpty()) {
                    try {
                        buffer.wait();
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Task task = buffer.remove(0);
                buffer.notifyAll();
                System.out.println("Consumer[" + Thread.currentThread().getName() + "] got " + task);
            }
        }
    }
}
 
/**
 * 生产者
 * @author 骆昊
 *
 */
class Producer implements Runnable {
    private List<Task> buffer;
 
    public Producer(List<Task> buffer) {
        this.buffer = buffer;
    }
 
    @Override
    public void run() {
        while(true) {
            synchronized (buffer) {
                while(buffer.size() >= Constants.MAX_BUFFER_SIZE) {
                    try {
                        buffer.wait();
                    } catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Task task = new Task();
                buffer.add(task);
                buffer.notifyAll();
                System.out.println("Producer[" + Thread.currentThread().getName() + "] put " + task);
            }
        }
    }
 
}
