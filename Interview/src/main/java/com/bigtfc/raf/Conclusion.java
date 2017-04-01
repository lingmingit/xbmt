package com.bigtfc.raf;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/***
 * 对分割统计所得的小文件，最后进行统计<p>
 * @author LingMin 
 * @date 2016-8-1<br>
 * @version 1.0<br>
 */
public class Conclusion {

	static Map<String, Integer> resultMap = new TreeMap<String, Integer>();  
    static File file = new File(Constants.OUTPUT_FILE_Catalog+"/results.txt");  
      
    /** 
     * 分别统计每个子结果文件 
     */  
    @SuppressWarnings("resource")  
    public static void sumUp() {  
        // include the thread to deal with byte-left  
        for(int i=0; i<CalcDemo.CALC_THREADS_NUM+1; i++){  
            File file = new File(Constants.OUTPUT_FILE_Catalog+"/result-" + i);  
            FileInputStream fio = null;  
            try {  
                fio = new FileInputStream(file);  
                InputStreamReader inputStreamReader = new InputStreamReader(fio);    
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);   
                String line;  
                while((line = bufferedReader.readLine()) != null){  
                    String letter = line.substring(0,1);  
                    String cntStr = line.substring(2);  
                    if(resultMap.get(letter) == null){  
                        resultMap.put(letter, 0);  
                    }  
                    resultMap.put(letter, resultMap.get(letter) + Integer.parseInt(cntStr));  
                }  
            } catch (FileNotFoundException e) {  
                e.printStackTrace();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            showResults();  
        }  
    }  
      
      
    /** 
     * 输出所有结果 
     */  
    private static void showResults(){  
        Iterator<String> iter = resultMap.keySet().iterator();  
        StringBuffer sbf = new StringBuffer();  
        while(iter.hasNext()){  
            String letter = iter.next();  
            Integer cnt = resultMap.get(letter);  
            String line = letter + ": " + cnt + "\n";  
            sbf.append(line);  
        }  
          
        FileOutputStream fos;  
        try {  
            fos = new FileOutputStream(file);  
            fos.write(sbf.toString().getBytes("UTF-8"));  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
    
    /***
     * 查找最多的字符串<p>
     * @param chineseCounts
     * @return <p>
     * Map<String,Integer>
     */
    public static Map<String, Integer> maxCountOfCharacters(Map<String, Integer> chineseCounts) {
        if (chineseCounts == null) {
            return null;
        }
        Set<String> keys = chineseCounts.keySet();
        Iterator<String> iterator = keys.iterator();
        int max = 0;
        Map<String, Integer> result = new HashMap<String, Integer>();
        while (iterator.hasNext()) {
            String currChar = iterator.next();
            int currCount = chineseCounts.get(currChar);
            if (currCount > max) {
                max = currCount;
                result.clear();
                result.put(currChar, max);
            } else if (currCount == max) {
                result.put(currChar, max);
            }
        }
        return result;
    }
    /***
     * 打印显示最多的 key 和数量<p>
     * @param map <p>
     * void
     */
    public static void showMessage(Map<String, Integer> map){
        if(null != map && map.size() > 0){
            Set<String> keys = map.keySet();
            Iterator<String> iterator = keys.iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                System.out.println("出现次数最多的人是【 " + key + "】" + "  次数为：【" + map.get(key) + "】");
            }
        }       
    }
    
    //获取 最大的map 信息
    public static void getMaxMap(){
    	 //查找最大的 key
        Map<String, Integer> maxMap = maxCountOfCharacters(resultMap);
        //打印 最大 的key 信息
        showMessage(maxMap);
    }

     
    public static void main(String[] args) {  
        sumUp();  //分别进行统计
        getMaxMap();//查找最大的 map 并打印
    }  

}
