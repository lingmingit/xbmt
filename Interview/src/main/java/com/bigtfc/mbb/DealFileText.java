package com.bigtfc.mbb;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/***
 * 用java多线程统计超大数据文件(1TB以上)中出现次数最多的人名<p>
 * @author LingMin 
 * @date 2016-8-2<br>
 * @version 1.0<br>
 */
public class DealFileText {

	 // 要处理的文件
    private File file = null;
     
    // 线程数数组
    private CountWordsThread[] threads = null;
     
    private List<CountWordsThread> listThread = null;
     
    public DealFileText(File file){
        this.file = file;
    }
     
    public DealFileText(File file , int threadNum){
        this.file = file;
        this.threads = new CountWordsThread[threadNum];
    }
    
    /***
     * 该方法 是将文件默认 分为两部分 进行统计<p>
     *  <p>
     * void
     */
    public void dealFile1(){
        final CountWordsThread thread1 = new CountWordsThread(file, 0, file.length());
        final Thread t1 = new Thread(thread1);
        // 开启两个线程分别处理文件的不同片段
        t1.start();
        Thread mainThread = new Thread() {
            public void run() {
                while(true) {
                    // 两个线程均运行结束
                    if(Thread.State.TERMINATED == t1.getState()) {
                        // 获取各自处理的结果
                        Map<String, Integer> hMap1 = thread1.getResultMap();
                         
                        // 使用TreeMap保证结果有序
                        TreeMap<String, Integer> tMap = new TreeMap<String, Integer>();
                        // 对不同线程处理的结果进行整合    2016-08-02此处应该是存在问题的，因为将两个结果map 都putAll到TreeMap中，相同的key将被覆盖
                        tMap.putAll(hMap1);
                         
                        Map<String, Integer> map = maxCountOfCharacters(tMap);
                        showMessage(map);       
                         
                        t1.interrupt();
                        return;
                    }
                }
            }
        };
        mainThread.start();
    }
 
    /***
     * 该方法 是将文件默认 分为两部分 进行统计<p>
     *  <p>
     * void
     */
    public void dealFile(){
        final CountWordsThread thread1 = new CountWordsThread(file, 0, file.length()/2);
        final CountWordsThread thread2 = new CountWordsThread(file, file.length()/2, file.length());
        final Thread t1 = new Thread(thread1);
        final Thread t2 = new Thread(thread2);
        // 开启两个线程分别处理文件的不同片段
        t1.start();
        t2.start();
        Thread mainThread = new Thread() {
            public void run() {
                while(true) {
                    // 两个线程均运行结束
                    if(Thread.State.TERMINATED == t1.getState() && Thread.State.TERMINATED == t2.getState()) {
                        // 获取各自处理的结果
                        Map<String, Integer> hMap1 = thread1.getResultMap();
                        Map<String, Integer> hMap2 = thread2.getResultMap();
                         
                        // 使用TreeMap保证结果有序
                        TreeMap<String, Integer> tMap = new TreeMap<String, Integer>();
                        // 对不同线程处理的结果进行整合    2016-08-02此处应该是存在问题的，因为将两个结果map 都putAll到TreeMap中，相同的key将被覆盖
                        tMap.putAll(hMap1);
                        tMap.putAll(hMap2);
                         
                        Map<String, Integer> map = maxCountOfCharacters(tMap);
                        showMessage(map);       
                         
                        t1.interrupt();
                        t2.interrupt();
                        return;
                    }
                }
            }
        };
        mainThread.start();
    }
     
    /***
     * 自定义 文件分割数【线程数量】  进行统计 <p>
     *  <p>
     * void
     */
    public void createThreads(){
        listThread = new ArrayList<CountWordsThread>();
        // 每线程应该读取的字节数
        long numPerThred = this.file.length() / this.threads.length;
        // 整个文件整除后剩下的余数
        long left = this.file.length() % this.threads.length;
        System.out.println("this.file.length(bytes)="+this.file.length()+" \t numPerThred="+numPerThred+" \t left="+left);
        Thread  tempThread = null;
        for (int i = 0; i < this.threads.length; i++) {
            // 让每个线程分别负责读取文件的不同部分。
            try {
            	long start = i * numPerThred;
            	long end = (i + 1) * numPerThred;
            	long size = numPerThred;
            	System.out.println("start="+start+" \t end="+end+" \t size="+size);
                // 最后一个线程读取指定numPerThred+left个字节
                if (i == this.threads.length - 1) {
                    this.threads[i] = new CountWordsThread(file, start , size + left);
                } else {// 每个线程负责读取一定的numPerThred个字节                   
                    this.threads[i] = new CountWordsThread(file, start , size );
                }
                tempThread = new Thread(threads[i]);
                tempThread.start();
                listThread.add((CountWordsThread)threads[i]);
            } catch (Exception e) {
                System.out.println("系统出现严重错误！系统将终止运行！");
                e.printStackTrace();
                System.exit(-1);// 系统退出
            }
        }       
    }
     
     
    public void doResult(){
    	//1、先将 所有线程 map合并，  同的key value 相加
    	Map<String, Integer> allMap = new HashMap<String , Integer>();
        if(null != listThread && listThread.size() > 0){
            for(int i=0;i<listThread.size(); i++){
            	CountWordsThread temp = listThread.get(i);
            	this.putAllMap(allMap, temp.getResultMap());
            }
        }
        //2、查找key 最多值
        Map<String, Integer> maxMap = this.maxCountOfCharacters(allMap);
        //3、打印
        this.showMessage(maxMap);
    }
    
    /****
     * 将 单个map对象 添加 所有all map 中 相同的key value 相加<p>
     * @param allMap
     * @param tempMap <p>
     * void
     */
    public void putAllMap(Map<String, Integer> allMap , Map<String, Integer> tempMap){
    	if(tempMap == null || allMap == null){
    		return;
    	}
    	 Iterator<String> iterator = tempMap.keySet().iterator();
    	while(iterator.hasNext()){
    		String key = iterator.next();
    		Integer value = tempMap.get(key);
    		//判断 all map 中是否存在 key，如果存在key  就增加value值
    		if(allMap.containsKey(key)){
    			Integer allValue = allMap.get(key);
    			value += allValue;
    		}
    		allMap.put(key, value);
    	}
    }
    
    
     
    public List<CountWordsThread> doThread(CountWordsThread thread){
        List<CountWordsThread> list = new ArrayList<CountWordsThread>();
        list.add(thread);
        return list;
    } 
     
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

}
