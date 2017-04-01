package com.threads.locks;


/***
 * 测试客户端类<p>
 * @author LingMin 
 * @date 2017-1-17<br>
 * @version 1.0<br>
 */
public class Client {
	
	/***
	 * <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
        Data data = new Data(10);
		
 
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
        new ReaderThread(data).start();
 
        new WriterThread(data, "ABCDEFGHI").start();
        new WriterThread(data, "012345789").start();
    }

}
