package com.threads.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/***
 * 共享文件数据类<p>
 * 读写锁  
 * @author LingMin 
 * @date 2017-1-17<br>
 * @version 1.0<br>
 */
public class Data {

	 private final  java.util.concurrent.locks.ReadWriteLock lock = new ReentrantReadWriteLock(); // 创建读写锁
	    private final Lock readLock = lock.readLock();    // 获取读锁
	    private final Lock writeLock = lock.writeLock();  // 获取写锁
	    
	    private final char[] buffer;
		
		
		 public Data(int size) {
		        this.buffer = new char[size];
		        for (int i = 0; i < size; i++) {
		            buffer[i] = '*';
		        }
		    }
		 
		 
		 public String read() throws InterruptedException {
		        readLock.lock(); // 读取上锁
		        try {
		            return doRead(); // 执行读取操作
		        } finally {
		            readLock.unlock(); // 读取解锁
		        }
		    }
		 
		    public void write(char c) throws InterruptedException {
		        writeLock.lock(); // 写入上锁
		        try {
		            doWrite(c); // 执行写入操作
		        } finally {
		            writeLock.unlock(); // 写入解锁
		        }
		    }
		    
		    
		    private String doRead() {
		        StringBuilder result = new StringBuilder();
		        for (char c : buffer) {
		            result.append(c);
		        }
		        sleep(100);
		        return result.toString();
		    }
		 
		    private void doWrite(char c) {
		        for (int i = 0; i < buffer.length; i++) {
		            buffer[i] = c;
		            sleep(100);
		        }
		    }
		    
		    
		    private void sleep(long ms) {
		        try {
		            Thread.sleep(ms);
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		    }
	
}
