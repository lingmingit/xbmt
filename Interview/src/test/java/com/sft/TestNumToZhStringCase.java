package com.sft;

import org.junit.Test;


/** 
  测试用例：将数字转换为中文可读数字<p>
* @author LingMin 
* @date 2016-10-19<br>
* @version 1.0<br>
*/
public class TestNumToZhStringCase {

	
	
//	@Test
	public void testGetZhNumString(){
		NumToZhString test = new NumToZhString();
//		String temp = test.getZhNumString("1203");
//		System.out.println(temp);
//		//import static org.junit.Assert.assertTrue;
//		org.junit.Assert.assertEquals(temp, "一千二百零三");
		
//		String temp = test.getZhNumString("1003");
//		System.out.println(temp);
//		org.junit.Assert.assertEquals(temp, "一千零三");
		
		
//		String temp = test.getZhNumString("100003");
//		System.out.println(temp);
//		//import static org.junit.Assert.assertTrue;
//		org.junit.Assert.assertEquals(temp, "一十万零三");
		
//		String temp = test.getZhNumString("20303");
//		System.out.println(temp);
//		//import static org.junit.Assert.assertTrue;
//		org.junit.Assert.assertEquals(temp, "二万零三百零三");
		
		
		String numStr = "120303";
		String temp = test.getZhNumString(numStr);
		System.out.println(numStr+"=\t"+temp);
		//import static org.junit.Assert.assertTrue;
		org.junit.Assert.assertEquals(temp, "二万零三百零三");
		
		//有问题
//		String temp = test.getZhNumString("1234567890");
//		System.out.println(temp);
//		//import static org.junit.Assert.assertTrue;
//		org.junit.Assert.assertEquals(temp, "一十二亿三千四百五十六万七千八百九十");
	
		
	}
	
	
	@Test
	public void testGetZhNumString2(){
		NumToZhString test = new NumToZhString();
		
//		String temp = test.getZhNumString2("120303");
//		System.out.println("temp="+temp);
		//org.junit.Assert.assertEquals(temp, "一十二万零三百零三万");
		
		
		
//		String numStr = "123456789";
//		String temp = test.getZhNumString2(numStr);
//		System.out.println(numStr+"="+temp);
//		//import static org.junit.Assert.assertTrue;
//		//org.junit.Assert.assertEquals(temp, "一十二万零三百零三万");
		
		//
//		String numStr = "100056789012";
//		String temp = test.getZhNumString2(numStr);
//		System.out.println(numStr+"="+temp);
//		org.junit.Assert.assertEquals(temp, "一千亿五千六百七十八万九千零一十二");
		
		String numStr = "1234567890123456";
		String temp = test.getZhNumString2(numStr);
		System.out.println(test.getNum4SplitString(numStr)+"=\t"+temp);
		//import static org.junit.Assert.assertTrue;
		org.junit.Assert.assertEquals(temp, "一千二百三十四万亿五千六百七十八亿九千零一十二万三千四百五十六");
		
//		String numStr = "123456789012345";
//		String temp = test.getZhNumString2(numStr);
//		System.out.println(test.getNum4SplitString(numStr)+"="+temp);
//		org.junit.Assert.assertEquals(temp, "一百二十三万亿四千五百六十七亿八千九百零一万二千三百四十五");
		
	}
}
