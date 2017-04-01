package com.bigtfc.raf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/***
 * 3中使用的统计文件的线程类<p>
 * @author LingMin 
 * @date 2016-8-1<br>
 * @version 1.0<br>
 */
public class CalcThread  extends Thread{  

	// Thread NO.  
    private int NO = 0;   
      
    private RandomAccessFile raf;  
      
    private Long start; // access start index;  
      
    private Long end; // access end index;  
      
    private Map<String, Integer> map = new TreeMap<String, Integer>();  
      
    public CalcThread(File file, Long start, Long end, int NO) {  
        try {  
            raf = new RandomAccessFile(file, "rw");  
            this.start = start;  
            this.end = end;  
            this.NO = NO;  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        }  
          
        int letterSize = Constants.words.length;  
        for(int i=0; i<letterSize; i++){  
            map.put(Constants.words[i], 0);  
        }  
    }  
  
    @Override  
    public void run() {  
        System.out.printf("Thread %d is now processing !!\n", NO);  
        try {  
            raf.seek(start);  
            byte[] buff = new byte[1]; // 带着换行符一起统计,每行两个字符  
            while(raf.read(buff) != -1){  
                String letter = new String(buff, "UTF-8").substring(0);  
                if(!"\n".equals(letter)){  
                    Integer cnt = map.get(letter);  
                    map.put(letter, cnt + 1);  
                }  
                start++;  
                if(start.equals(end)){  
                    break;  
                }  
            }    
              
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                raf.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            outputResult();  
        }  
    }  
      
    // 将结果输出到每个线程各自的文件  
    private void outputResult() {  
        Iterator<String> iter = map.keySet().iterator();  
        FileOutputStream outputFileStream = null;  
        try {  
            // 将统计的结果导入文件result-{NO}  
            File outputFile = new File(Constants.OUTPUT_FILE_Catalog+"/result-" + NO);  
            StringBuffer sbf = new StringBuffer();  
            while(iter.hasNext()){  
                String key = iter.next();  
                sbf.append(key + ":" + map.get(key) + "\n");  
            }  
            outputFileStream = new FileOutputStream(outputFile,true);  
            outputFileStream.write(sbf.toString().getBytes("UTF-8"));  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally{  
            if(outputFileStream != null){  
                try {  
                    outputFileStream.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
  
      
    // main 测试  
    public static void main(String[] args) {  
        File file = new File(Constants.OUTPUT_FILE_NAME);  
        CalcThread calcThread = new CalcThread(file, 0L, file.length(), 1);
        calcThread.start();  
    }  
}
