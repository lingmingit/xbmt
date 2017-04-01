package com.bigtfc.raf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

/***
 * 随机生成 大文本文件类
 * 数据生成代码，可以跑一段时间然后看输出的文件<p>
 * @author LingMin 
 * @date 2016-8-1<br>
 * @version 1.0<br>
 */
public class DataProductor {

	/**             <p>
	 * @param args <p>
	 * void
	 */
	 public static void main(String[] args) {  
	        // 随机生成数据  
	        new DataProductor().produceData();  
	    }  
	  
	    private FileOutputStream outputFileStream = null;  
	    private static final File outputFile = new File(Constants.OUTPUT_FILE_NAME);  
	  
	    public void produceData(){  
	        DataProductor productor = new DataProductor();  
	        PrepareOutput prepareOutput = productor.new PrepareOutput();  
	        Thread thread = new Thread(prepareOutput);  
	        thread.start();  
	    }  
	  
	  
	    /** 
	     * 子线程在文件中插入英文字符 
	     */  
	    private class PrepareOutput implements Runnable{  
	        String output = ""  ;  
	  
	        private String genWord(){  
	            int index = new Random().nextInt(Constants.words.length);  
	            return Constants.words[index];  
	        }  
	  
	        public void run(){  
	            while(true){  
	                for(int i=0; i<10000;i++){  
	                    output += genWord() + "\n";  
	                }  
	                try{  
	                    byte[] outputBytes = output.getBytes("UTF-8");  
	                    //append text at the end, NO covering the previous file  
	                    outputFileStream = new FileOutputStream(outputFile,true);  
	                    outputFileStream.write(outputBytes);  
	                } catch (FileNotFoundException e) {  
	                    e.printStackTrace();  
	                } catch (UnsupportedEncodingException e) {  
	                    e.printStackTrace();  
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }finally{  
	                    //close file stream  
	                    try {  
	                        outputFileStream.close();  
	                    } catch (IOException e) {  
	                        e.printStackTrace();  
	                    }  
	                }  
	            }  
	        }  
	    }  

}
