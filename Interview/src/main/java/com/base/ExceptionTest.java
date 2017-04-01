/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/** 
 * catch捕获多异常测试 <p>
 * 1、子类异常先捕获，必须放catch前面，否则编译不通过
 * 2、catch块只捕获一次，并且是从子类先匹配，匹配后即不执行后续catch块
 * @author LingMin 
 * @date 2016-9-7<br>
 * @version 1.0<br>
 */
public class ExceptionTest {

	/**             <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try{
			File file = new File("D:/西部红果煤炭交易中心(盘江)/盘云快销项目/盘云快销说明.txt");
			InputStream input = new FileInputStream(file);
			input.close();
			input.read();
		}catch(FileNotFoundException e){
			//e.printStackTrace();
			System.out.print("FileNotFoundException!");
		}catch(IOException e){
			e.printStackTrace();
			System.out.print("IOException!");
		}catch(Exception e){
			e.printStackTrace();
			System.out.print("Exception!");
		}

	}

}
