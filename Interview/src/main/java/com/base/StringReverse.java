package com.base;



/** 
 * 
 * 有一道很常见的面试题是用递归实现字符串反转，代码如下所示：<p>
 * 字符串反转这个递归算法空间复杂度都平方级了，递归还要浪费栈空间。我觉得比较好的方法是直接新建一个字符数组，从后往前读原字符串。
 * @author LingMin 
 * @date 2016-10-31<br>
 * @version 1.0<br>
 */
public class StringReverse {

	
	/***
	 * 有一道很常见的面试题是用递归实现字符串反转
	 * @param originStr
	 * @return
	 */
	public static String reverse(String originStr) {
	      if(originStr == null || originStr.length() <= 1) 
	          return originStr;
	      return reverse(originStr.substring(1)) + originStr.charAt(0);
	  }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String temp = "abcdefg";
		String temp1 = reverse(temp);

		System.out.println("temp1="+temp1);
	}

}
