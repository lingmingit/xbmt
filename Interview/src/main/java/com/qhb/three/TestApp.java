package com.qhb.three;

/**
 * 我用java写的抢红包用的红包类，支持多线程 
 http://blog.csdn.net/wj310298/article/details/44979007
 * @author admin
 *
 */
public class TestApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    final CashGift[] cgList = new CashGift[10000];  
	    for (int i = 0; i < cgList.length; i++) {  
	        cgList[i] = new CashGift(100, 10000);  
	    }     
	    Runnable qianghongbao = new Runnable() {  
	      
	        @Override  
	        public void run() {  
	            int i = 0;  
	            while (i < cgList.length) {  
	                try {  
	                    float temp = cgList[i].get();
	                    System.out.println("temp="+temp);
	                } catch(CashGift.OverException e) {  
	                    i++;  
	                }  
	            }  
	        }  
	    };  
	    System.out.println("Starting...");  
	    int number = 100;  
	    final Thread[] people = new Thread[number];  
	    long start = System.nanoTime();  
	    for (int i = 0; i < people.length; i++) {  
	        people[i] = new Thread(qianghongbao);  
	        people[i].start();  
	    }  
	    for (int i = 0; i < people.length; i++) {  
	        try {
				people[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	    }  
	    System.out.println(System.nanoTime() - start);  
	    int i;  
	  /*  for (i = 0; i < cgList.length; i++) {  
	        if (!cgList[i].isEmpty()) {  
	            System.out.println("Failed!");  
	            break;  
	        }  
	    }  
	    if (cgList.length == i)  
	        System.out.println("Successed!");  */
	}

}
