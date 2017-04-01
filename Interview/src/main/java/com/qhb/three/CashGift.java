package com.qhb.three;

import java.util.concurrent.atomic.AtomicInteger;


/** 
我用java写的抢红包用的红包类，支持多线程 
 http://blog.csdn.net/wj310298/article/details/44979007
 * <p>
 * @author LingMin 
 * @date 2016-10-10<br>
 * @version 1.0<br>
 */
public class CashGift {

	
	 public static class OverException extends Exception {  
         
	    }  
	    private final int totalNumber;  
	    private final float totalMoney;  
	    private final AtomicInteger index = new AtomicInteger();  
	    long p1, p2, p3, p4, p5, p6, p7;  
	    private volatile int realIndex;  
	    private volatile float remainMoney;  
	    public CashGift(int totalNumber, float totalMoney) {  
	        this.totalNumber = totalNumber;  
	        this.totalMoney = totalMoney;  
	        realIndex = 1;  
	        remainMoney = totalMoney;  
	    }  
	    protected float rand(float x) {  
	        return (float)(Math.random() * x);  
	    }  
	    protected float getMoney(int i) {  
	        if (i < totalNumber) {  
	            float j = totalNumber + 1 - i;  
	            return rand(2 / j) * remainMoney * j / 2;  
	        } else {  
	            return remainMoney;  
	        }  
	    }  
	      
	    public float get() throws OverException {  
	        int ticket = index.incrementAndGet();  
	        if (ticket > totalNumber) {  
	            throw new OverException();  
	        } else {  
	            for (;;) {  
	                if (ticket == realIndex) {  
	                    float money = getMoney(realIndex);  
	                    remainMoney -= money;  
	                    realIndex++;  
	                    return money;  
	                }  
	            }  
	        }  
	    }  
	
}
