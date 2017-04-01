package com.bigtfc.raf;

import java.io.File;
import java.util.Map;

/***
 * 主测试文件（20s sleep如果output.txt数据文件更大则需要更长时间）<p>
 * @author LingMin 
 * @date 2016-8-1<br>
 * @version 1.0<br>
 */
public class MainTest {

	public static void main(String[] args) {  
		
		/**第一步：对已有的拆分文件进行删除， 根据文件大小进行拆分，开启多个线程对拆分文件进行统计（一个拆分文件 对应一个线程）***********
        // clear data   清空拆分 统计文件
        File file = null;  
        for(int i=0; i<=CalcDemo.CALC_THREADS_NUM; i++){  
            // delete old data file  
            file = new File(Constants.OUTPUT_FILE_Catalog+"/result-" + i);  
            if(file.isFile() && file.exists())  
                file.delete();  
        }  
  
        file = new File(Constants.RESULTS_FILE);  
        if(file.isFile() && file.exists())  
            file.delete();  
  
        // 开始进行 拆分统计 CALC_THREADS_NUM+1（for the bytes-left） Threads to analyze data  
        CalcDemo.doCalc(); */
  
        /* 20 seconds time waiting for all threads' process   
        try {  
            Thread.sleep(20000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        } */ 
		/**第二步：对拆分统计结果进行汇总，查找最大的key  并打印********************************/
        // 将拆分统计结果 进行汇总  main thread to sum up data  
        Conclusion.sumUp();  
        //查找最大的 key
        Conclusion.getMaxMap();//查找最大的 map 并打印
    }  

}
