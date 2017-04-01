package com.queue;


/***
 * 息队列共享数据<p>
 * @author LingMin 
 * @date 2016-6-28<br>
 * @version 1.0<br>
 */
public class ShareData {
	private static int shareArray[] = new int[10];  
    private int count;  
    private int in;  
    private int out;  
       
    ShareData(){  
        this.count = 0;  
        this.in = 0;  
        this.out = 0;  
    }  
       
    public synchronized void setArray(int product){  
        try{  
            while(count >= shareArray.length){  
                System.out.println("array full.");  
                this.wait();  
            }  
            this.notify();  
        }catch (Exception e) {  
            e.printStackTrace();  
        }  
        shareArray[in] = product;  
        count++;  
        System.out.println("produce: " + product);  
        in = (in + 1) % shareArray.length;  
    }  
       
    public synchronized int getArray(){  
        try{  
            while(count <= 0){  
                System.out.println("array empty.");  
                this.wait();  
            }  
            this.notify();  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
        int consume = shareArray[out];  
        count--;  
        System.out.println("consume: " + consume);  
        out = (out + 1) % shareArray.length;  
        notify();  
        return consume;  
    }  
}
