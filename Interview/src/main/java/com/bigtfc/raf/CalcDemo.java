package com.bigtfc.raf;

import java.io.File;

/***
 * 分成多个子线程统计每个英文字符出现的次数<p>
 * 1、根据file的大小，将文件分为8份
 * 2、开启8个线程进行分别统计
 * 3、
 * 
 * @author LingMin 
 * @date 2016-8-1<br>
 * @version 1.0<br>
 */
public class CalcDemo {

	// the number of calc threads number  统计线程数量
    public static final int CALC_THREADS_NUM = 100; //8  4 
    // the src file   
    private static final File file = new File(Constants.OUTPUT_FILE_NAME);  
    // total length  文件总长度
    private static final Long totalBytes = file.length();  
    // bytes per thread  将文件平均分配为8分份
    private static final Long bytesPerThread = totalBytes / CALC_THREADS_NUM;  
    // bytes left  文件除去8份后，剩余的部分长度
    private static final Long bytesLeft = totalBytes % bytesPerThread;  
      
    private static void initInfo(){  
        System.out.printf("file size: %d bytes\n", totalBytes);  
        System.out.printf("per thread: %d bytes\n", bytesPerThread);  
        System.out.printf("bytes left: %d bytes\n", bytesLeft);  
    }  
      
    public static void doCalc() {  
        initInfo();  
        // calc thread start  将文件平均分配为8份，同时启动 8个线程 进行统计
        for(int threadId=0; threadId<CALC_THREADS_NUM; threadId++){  
            Long start = threadId * bytesPerThread;  
            Long end = start + bytesPerThread;  
            new CalcThread(file, start, end, threadId).start();  
        }  
          
        // for the bytes left  统计  剩余部分的长度
        CalcThread calcThread = new CalcThread(file  
                , bytesPerThread*CALC_THREADS_NUM, totalBytes, CALC_THREADS_NUM);  
        calcThread.start();  
          
    }  

}
