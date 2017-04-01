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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/** 
 * （算法）从10000个数中找出最大的10个
 * http://www.cnblogs.com/ceachy/archive/2013/03/20/Algorithm_Max_m_from_N.html
 * 有没有更好一点的算法呢？当然。维持一个长度为10的降序数组，每一个从数组拿到的数字都与这个降序数组的最小值比较。
 * 如果小于最小值，就舍弃；如果大于最小值，就把它插入到降序数组中的合适位置，舍弃原来的最小值。这样，遍历一遍就可以找到最大的10个数。
 * 因为需要在降序数组中插入一个数，对于遍历的每个数可能都需要这样，所以其复杂为5N。
 * <p>
 * @author LingMin 
 * @date 2016-9-9<br>
 * @version 1.0<br>
 */
public class TestLookupMax10Num {

	/**             <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		TestLookupMax10Num test = new TestLookupMax10Num();
		test.testLookupMax10Num();
	}

	/**
	 * 查找最大数<p>
	 *  <p>
	 * void
	 */
	public void testLookupMax10Num(){
		int[] radomArray = this.getRandomIntArray(20 , 200);
		//int[] radomArray = {69,17,79,59,47,167,78,69,136,173,32,86,91,67,22,20,177,49,120,102};
		System.out.println("产生的随机数组：");
		this.displayIntArray(radomArray);
		//
		int maxNum = 10;
		//将随机数组中的前 10个数组，设置到新数组中，并降序排列
		Integer[] tempArray = this.get10Integer(radomArray , maxNum);
		System.out.println();
		System.out.println("10个数字的数组：");
		this.displayIntegerArray(tempArray);
		/**维持一个长度为10的降序数组，每一个从数组拿到的数字都与这个降序数组的最小值比较。
		  如果小于最小值，就舍弃；如果大于最小值，就把它插入到降序数组中的合适位置，舍弃原来的最小值。
		  这样，遍历一遍就可以找到最大的10个数。因为需要在降序数组中插入一个数，对于遍历的每个数可能都需要这样，所以其复杂为5N。
		  **/
		for(int i = maxNum ; i < radomArray.length ; i++){
			if(radomArray[i] > tempArray[maxNum - 1]){
				tempArray = this.insert2(tempArray, radomArray[i]);
			}
		}
		System.out.println();
		System.out.println("最大的10个数字的数组：");
		this.displayIntegerArray(tempArray);
		
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
	        //降序排列
	        Collections.sort(data , new Comparator(){
				public int compare(Object o1, Object o2) {
					Integer num1 = (Integer)o1;
					Integer num2 = (Integer)o2;
					//System.out.println("num1="+num1+" \t num2="+num2);
					if(num1 > num2){
						return -1;
					}else{
						return 1;
					}
				}
	        });
	        data.remove(data.size() - 1);//删除最小的一个元素
	        return data.toArray(new Integer[0]);
	 }
	
	
	
	/***
	 * 将随机数组中的前 10个数组，设置到新数组中，并降序排列<p>
	 * @param arr
	 * @param num
	 * @return <p>
	 * Integer[]
	 */
	private static Integer[] get10Integer(int[] arr, int num) {
        List<Integer> data = new ArrayList<Integer>();
        int index = 0;
        for (int i = 0 ; i < num ; i++) {
            data.add(arr[i]);
        }
        //降序排列
        Collections.sort(data , new Comparator(){
			public int compare(Object o1, Object o2) {
				Integer num1 = (Integer)o1;
				Integer num2 = (Integer)o2;
				//System.out.println("num1="+num1+" \t num2="+num2);
				if(num1 > num2){
					return -1;
				}else{
					return 1;
				}
			}
        });
        return data.toArray(new Integer[0]);
 }
	
	/***
	 * 获取随机int 数组<p>
	 * @param len
	 * @return <p>
	 * int[]
	 */
	public int[] getRandomIntArray(int len , int maxNum){
		int[] tempArray = new int[len];
		Random random = new Random();
		for(int i = 0 ; i < len ; i++){
			tempArray[i] = random.nextInt(maxNum);
		}
		return tempArray;
	}
	
	/***
	 * 打印数组<p>
	 * @param array <p>
	 * void
	 */
	public void displayIntArray(int[] array){
		for(int temp : array){
			System.out.print(temp);
			System.out.print(",");
		}
	}
	
	/***
	 * 打印数组<p>
	 * @param array <p>
	 * void
	 */
	public void displayIntegerArray(Integer[] array){
		for(int temp : array){
			System.out.print(temp);
			System.out.print(",");
		}
	}
}
