package com.bitys;

public class TestApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/***Java的位运算符中有一个叫异或的运算符，用符号(^)表示，其运算规则是：
		 * 两个操作数的位中，相同则结果为0，不同则结果为1。下面看一个例子：
		运行结果是：i ^ j = 13.

		分析上面程序，i=15转成二进制是1111，
					  j=2转成二进制是0010，
		
		***/
		int i = 15, j = 2;
		System.out.println("i ^ j = " + (i ^ j));
		System.out.println("temp");
		
		/***不增加新的变量来交换a b两个变量的值**********/
		//，这种方式有一个弊端：有可能a＋b的值会超出int型的范围。
		int a = 1 , b = 2;
//		a = a + b; //此时a包含a 和 b
//		b = a - b; 
//		a = a - b;
		
		//第一步之后，原来a占用了多少位依旧是多少位，绝对不会发生数据的溢出。
		a = a ^ b; 
		b = a ^ b;  //实际上是(a^b)^b 也就是a异或了b两次，等号右边是a的值
		a = a ^ b;  //此时b里面已经是“果汁”，实际上是(a^b)^a，也就是b异或了a两次，是b
		
		System.out.println("a="+a + " \t b="+b);
		//1 一个数异或同一个数两次还是原数；
		int c = 2;
		int temp = c ^ 5 ^ 5;
		System.out.println(temp);
		
	}

}
