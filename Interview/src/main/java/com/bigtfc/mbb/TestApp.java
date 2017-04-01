package com.bigtfc.mbb;

import java.io.File;

import com.bigtfc.raf.Constants;

/***
 * 用java多线程统计超大数据文件(1TB以上)中出现次数最多的人名 <p>
 * @author LingMin 
 * @date 2016-8-2<br>
 * @version 1.0<br>
 */
public class TestApp {

	  // 随机生成的数据文件  
    public static String OUTPUT_FILE_NAME = Constants.OUTPUT_FILE_Catalog+"/output.txt";  
	/**             <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		
		//第一种方法 默认分为两部分 和两个线程
//		DealFileText dft = new DealFileText(new File(OUTPUT_FILE_NAME));
//		dft.dealFile();
//		dft.dealFile1();
		//第二种方法 自定义 分割数量
		DealFileText dft = new DealFileText(new File(OUTPUT_FILE_NAME) , 200);
		dft.createThreads();
		try {
			Thread.sleep(10*1000*60);//*60
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dft.doResult();
		
	}

}
