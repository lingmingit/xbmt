package com.bigtfc.raf;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;

/***
 * 测试读取大文件<p>
 * @author LingMin 
 * @date 2016-8-2<br>
 * @version 1.0<br>
 */
public class TestApp {
	
	  // 随机生成的数据文件  
    public  String OUTPUT_FILE_NAME = Constants.OUTPUT_FILE_Catalog+"/output.txt";  
	/***
	 * <p>
	 *  <p>
	 * void
	 */
	public  void testRead(){
		//BufferedInputStream a;
		//Files.readLines(new File(OUTPUT_FILE_NAME), Charsets.UTF_8);
		try {
			InputStream input = new FileInputStream(new File(OUTPUT_FILE_NAME));
			 byte[] buff = new byte[1024]; 
			 while(input.read(buff) != -1){
				 String temp = new String(buff);
				 System.out.println(temp);
			 }
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
	}

	/**             <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		TestApp testApp = new TestApp();
		testApp.testRead();
	}

}
