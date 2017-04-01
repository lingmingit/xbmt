package com.threads.locks;



/***
 * 共享文件数据类<p>
 * @author LingMin 
 * @date 2017-1-17<br>
 * @version 1.0<br>
 */
public class Data1 {

	private final char[] buffer;
	 
    public Data1(int size) {
        this.buffer = new char[size];
        for (int i = 0; i < size; i++) {
            buffer[i] = '*';
        }
    }
 
    public synchronized String read() {
        StringBuilder result = new StringBuilder();
        for (char c : buffer) {
            result.append(c);
        }
        sleep(100);
        return result.toString();
    }
 
    public synchronized void write(char c) {
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
