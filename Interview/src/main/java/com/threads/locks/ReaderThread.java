package com.threads.locks;


/***
 * 读取文件数据线程<p>
 * @author LingMin 
 * @date 2017-1-17<br>
 * @version 1.0<br>
 */
public class ReaderThread extends Thread {
 
    private final Data data;
 
    public ReaderThread(Data data) {
        this.data = data;
    }
 
    @Override
    public void run() {
        while (true) {
        	long begin = System.currentTimeMillis();
                String result = null;
				try {
					result = data.read();
					 System.out.println(Thread.currentThread().getName() + " => " + result);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
            long time = System.currentTimeMillis() - begin;
            System.out.println(Thread.currentThread().getName() + " -- " + time + "ms");
        }
    }
}