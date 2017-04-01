package com.threads.locks;

/***
 * 写数据线程<p>
 * @author LingMin 
 * @date 2017-1-17<br>
 * @version 1.0<br>
 */
public class WriterThread extends Thread {
 
    private final Data data;
    private final String str;
    private int index = 0;
 
    public WriterThread(Data data, String str) {
        this.data = data;
        this.str = str;
    }
 
    @Override
    public void run() {
        while (true) {
            char c = next();
            try {
				data.write(c);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
 
    private char next() {
        char c = str.charAt(index);
        index++;
        if (index >= str.length()) {
            index = 0;
        }
        return c;
    }
}