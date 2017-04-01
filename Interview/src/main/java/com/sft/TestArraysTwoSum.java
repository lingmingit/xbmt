/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.sft;

/** 
 * 
 * 
         《面试题精选》10.给一个有序数组和一个数s，求数组中两数和等于s的组合 
   http://www.cnblogs.com/huanglei/p/3677694.html
 * 此时我们就会想到两个元素同时移动，于是很容易就会想到两个元素向中间靠拢的方法，如果两个数的和小了，则左边元素右移，大了则右边元素左移。
 * 总结：此题的妙点在于两边同时开始遍历。
 * <p>
 * @author LingMin 
 * @date 2016-9-7<br>
 * @version 1.0<br>
 */
public class TestArraysTwoSum {

	/**
	 * <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String args[]){
		//有序数组
		int[] array = {0,1,2,3,4,5,6,7,8,9,10,11,12 ,13,14,15} ;
		//组合 表达式 值
		int s = 15 ;
		//
		fun(array , s) ;
	}
	
	/***
	 * 查找 组合算法函数<p>
	 * @param array
	 * @param s <p>
	 * void
	 */
	public static void fun(int[] array , int s){
		int n = array.length ;//数组长度
		int i = 0 ;//该索引 从 左边开始
		int j = n - 1 ;//该索引 从右边开始
		while(i < j){//当 i = j后 表明遍历完成，结束循环
			if((array[i]+array[j]) < s){//如果两个数和小了，左边索引增加
				i++;
			}else if((array[i]+array[j]) > s){//如果两个数和大了，右边索引减少
				j--;
			}else{
				System.out.println(array[i]+"+" + array[j]+"="+s) ;
				//i=j ; //跳出while循环
				i++;
			}
		}
	}

}
