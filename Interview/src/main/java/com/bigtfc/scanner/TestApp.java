package com.bigtfc.scanner;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import com.bigtfc.raf.Constants;


/***
 * <p>
 * @author LingMin 
 * @date 2016-8-2<br>
 * @version 1.0<br>
 */
public class TestApp {
	
	  // 随机生成的数据文件  
    public static String OUTPUT_FILE_NAME = Constants.OUTPUT_FILE_Catalog+"/output.txt";  
	/***
	 * 测试读取文件<p>
	 *  <p>
	 * void
	 * @throws IOException 
	 */
	public static void testRead() throws IOException{
		FileInputStream inputStream = null;
		Scanner sc = null;
		try {
		    inputStream = new FileInputStream(OUTPUT_FILE_NAME);
		    sc = new Scanner(inputStream, "UTF-8");
		    while (sc.hasNextLine()) {
		        String line = sc.nextLine();
		         System.out.println(line);
		    }
		    // note that Scanner suppresses exceptions
		    if (sc.ioException() != null) {
		        throw sc.ioException();
		    }
		} finally {
		    if (inputStream != null) {
		        inputStream.close();
		    }
		    if (sc != null) {
		        sc.close();
		    }
		}
	}

	/**             <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {

//		try {
//			testRead();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		Map<String, Integer> map1 = new HashMap<String, Integer>();
		map1.put("11", 11);
		Map<String, Integer> map2 = new HashMap<String, Integer>();
		map2.put("11", 22);
		
		 TreeMap<String, Integer> tMap = new TreeMap<String, Integer>();
		 tMap.putAll(map1);
		 tMap.putAll(map2);
		 java.util.Iterator<String> iterator = tMap.keySet().iterator();
		 while(iterator.hasNext()){
			 String key = iterator.next(); 
			 Integer value = tMap.get(key);
			 System.out.println("key="+key +" \t value="+value);
		 }
	}
	
	
	

}
