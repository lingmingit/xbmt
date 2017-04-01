package com.sft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;




/** 
 * 查找某个字符串数组中，最大和的子串
 * 1、首先按负数进行分割数组，循环遍历+ 数组copy 完成分割
 * 2、再统计分割数组累加总和，同时将分割的数组进行元素字符串拼接
 * 3、再依次比较各子数组累加总和，保留最大的总和 和 子字符串
 * 4、遍历完成后，即获取最大总和 和子字符串
 * <p>
 * @author LingMin 
 * @date 2016-9-9<br>
 * @version 1.0<br>
 */
public class TestLookupMaxChildStr {

	
	//
	public int[] numArray = {1 , 22, 12, -23, -43 , 43 ,23 ,-111, 89 ,12 , -341};
	
	
	/***
	 * 按负数进行分串处理
	 * @param numArray
	 * @return
	 */
	public String getMaxNumStr(int[] numArray){
		//List list = new ArrayList();
		Map<Integer , String> map = new HashMap<Integer , String>();
		int count = 1;
		int sum = 0;
		int beginIndex = 0 ;
		int maxSum = 0;
		for(int i = 0 ; i < numArray.length ; i++){
			sum += numArray[i];
			if(numArray[i] < 0 || i == numArray.length -1){
				if(beginIndex != 0){
					beginIndex++;
				}
				String temp = this.getNumArrayToString(numArray , beginIndex , count);
				map.put(sum , temp);
				if(sum > maxSum){
					maxSum = sum;
				}
				sum = 0;
				count = 0;
				beginIndex = i;
			}
			count++;
		}
		java.util.Iterator<Integer> iterator = map.keySet().iterator(); 
		while(iterator.hasNext()){
			Integer num = iterator.next();
			String temp = map.get(num);
			System.out.println("num="+num+" \t temp="+temp);
		}
		String maxStr =  null;
		if(maxSum > 0){
			maxStr = map.get(maxSum);
			System.out.println("maxSum="+maxSum+" \t maxStr="+maxStr);
		}
		return maxStr;
	}
	
	
	private String getNumArrayToString( int[] numArray , int beginIndex , int count){
		int[]newNumArray = new int[count];
		//参数说明：原数组、copy起始索引、新数组、新数组起始索引、copy元素个数
		//public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
		System.arraycopy(numArray, beginIndex , newNumArray , 0 ,count);
		StringBuilder tempStr = new StringBuilder();
		for(int num : newNumArray){
			tempStr.append(num).append(",");
		}
		return tempStr.toString();
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestLookupMaxChildStr test = new TestLookupMaxChildStr();
		//String temp = test.getNumArrayToString(test.numArray, 2, 3);
		//System.out.println("temp="+temp);
		test.getMaxNumStr(test.numArray);
		
	}

}
