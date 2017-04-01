/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.sft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/** 
 * 
  （JAVA）已知一个数组中的数有序排列,编程实现插入一个数后,依然有序.
      第一种,就是常规的想法,:建个数组,然后逐个比较,这应该也是出题人的考察点,但是这种题不适合在java理出,一般C语言愿意有这样的问题.
      	建个新数组，然后逐个比较，
      第二种就是利用java api里的方法,实现简单,但是效率不够,而且也违背了出题人的意愿,可做了解
      	将新元素添加到List中，再进行sort排序即可
   <p>
 * @author LingMin 
 * @date 2016-9-7<br>
 * @version 1.0<br>
 */
public class TestArrayInsert {
	
	
	 Integer[] arr = { 11, 22, 33, 44, 55, 66, 77, 88, 99 };
	 int number = 60;

	/**            
	 *  <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		TestArrayInsert test = new TestArrayInsert();
		System.out.println("插入前:" + Arrays.deepToString(test.arr));
        Integer[] brr = insert2(test.arr, 60);
        System.out.println("插入前:" + Arrays.deepToString(brr));
	}
	
	/***
	 * 第一种,就是常规的想法,:建个数组,然后逐个比较,这应该也是出题人的考察点,但是这种题不适合在java理出,一般C语言愿意有这样的问题.<p>
	 * @param arr
	 * @param num
	 * @return <p>
	 * Integer[]
	 */
	private static Integer[] insert(Integer[] arr, int num) {
        Integer[] brr = new Integer[arr.length + 1];
        int idx = 0;
        boolean hasInsert = false;
        for (int i = 0; i < arr.length; i++) {
			if (hasInsert) {
	            brr[idx++] = arr[i];
	            continue;
	        }
			
			if (arr[i] > num) {
	              brr[idx++] = num;
	              hasInsert = true;
	         }
	         brr[idx++] = arr[i];
        }
        return brr;
	}

	/***
	 * 第二种就是利用java api里的方法,实现简单,但是效率不够,而且也违背了出题人的意愿,可做了解<p>
	 * @param arr
	 * @param num
	 * @return <p>
	 * Integer[]
	 */
	 private static Integer[] insert2(Integer[] arr, int num) {
	        List<Integer> data = new ArrayList<Integer>();
	        for (Integer a : arr) {
	            data.add(a);
	        }
	        data.add(num);
	        Collections.sort(data);
	        return data.toArray(new Integer[0]);
	 }
}
