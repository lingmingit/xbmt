package com.base;


/***
 * 测试 null
 * ((NULL)null).printNULL(); //居然可以正常调用
 * ((NULL)null).printnull();  //居然可以正常调用
 *   原因是两个方法为静态方法，如果不是静态方法就会出现异常
 *   具体原因还不知道？
 * <p>
 * @author LingMin 
 * @date 2016-7-21<br>
 * @version 1.0<br>
 */
public class NULL {
	
	
	public static void printNULL(){
		System.out.println("NULL");
	}
	
	public static void printnull(){
		System.out.println("null");
	}

	/**             <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		((NULL)null).printNULL();
		((NULL)null).printnull();
		
		System.out.println((NULL)null);
	}

}
