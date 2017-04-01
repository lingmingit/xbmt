package com.sft;


/** 
 * 古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，假如兔子都不死，问每个月的兔子总数为多少？ 
 * http://blog.csdn.net/duxinfeng2010/article/details/7770354
   <p>
 * @author LingMin 
 * @date 2016-9-7<br>
 * @version 1.0<br>
 */
public class RabbitPX2 {

	/**
	 * http://blog.csdn.net/duxinfeng2010/article/details/7770354
	 * @param args
	 */
	public static void main(String[] args) {
		int[] m = new int[24];  
	    int i;  
	    m[0]=m[1]=1;  //初始化  第1、2个月 都为1
	    for (i=0; i<24; i++) {  
	        if (i==0 || i==1) {  
	            System.out.println("第"+(i+1)+"个月月兔子数量为:1");  
	        } else {  
	            m[i]=m[i-1]+m[i-2];  
	            System.out.println("第"+(i+1)+"个月月兔子数量为:="+m[i]);  
	        }  
	    }  
	}

}

