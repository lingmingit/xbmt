/**
 * 
 */
package com.base;

/** 
 * 测试 Switch 的用法<p>
 * String(JDK1.7之后支持)、int、、Integer、char 和 byte支持，long、float、double不支持
 * @author LingMin 
 * @date 2016-8-16<br>
 * @version 1.0<br>
 */
public class SwitchTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int a = 0;
		switch(a){
		 case 1:
			 System.out.println("1");
			 break;
		 case 2:
			 System.out.println("2");
			 break;
		  default:
			  System.out.println("default");
		}
		Integer inte = 10;
		switch(inte){
		 case 10:
			 System.out.println("10");
			 break;
		 case 20:
			 System.out.println("20");
			 break;
		  default:
			  System.out.println("default");
		}
		
		/*long b = 1;
		switch(b){
		 case 1:
			 System.out.println("1");
			 break;
		 case 2:
			 System.out.println("2");
			 break;
		  default:
			  System.out.println("default");
		}*/
		String str = "1";
		switch(str){
		 case "1":
			 System.out.println("1");
			 break;
		 case "2":
			 System.out.println("2");
			 break;
		  default:
			  System.out.println("default");
		}
		
		byte by = 3;
		switch(by){
		 case 0:
			 System.out.println("0");
			 break;
		 case 3:
			 System.out.println("3");
			 break;
		  default:
			  System.out.println("default");
		
		}
		char ch = 'a';
		double dob = 1.12;
		switch(ch){
		 case 'a':
			 System.out.println("a");
			 break;
		 case 'b':
			 System.out.println("b");
			 break;
		  default:
			  System.out.println("default");
		}
		char a1 = '你';
		byte b1 = 127;
		System.out.println(a1);
		System.out.println(b1);
		char i = 'a';
		char ch1 = 0;
	}

}
