package com.lb.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lb.IpMap;


/***
 * 
 * 几种简单的负载均衡算法及其 Java 代码实现
 * 加权轮询（Weight Round Robin）法
 * <p>
 * @author LingMin 
 * @date 2017-1-6<br>
 * @version 1.0<br>
 */
public class WeightRoundRobin {

	 private static Integer pos = 0;
	 
	    public static String getServer()
	    {
	        // 重建一个Map，避免服务器的上下线导致的并发问题
	        Map<String, Integer> serverMap =  new HashMap<String, Integer>();
	        serverMap.putAll(IpMap.serverWeightMap);
	 
	        // 取得Ip地址List
	        Set<String> keySet = serverMap.keySet();
	        Iterator<String> iterator = keySet.iterator();
	 
	        List<String> serverList = new ArrayList<String>();
	        while (iterator.hasNext()){
	            String server = iterator.next();
	            int weight = serverMap.get(server);
	            for (int i = 0; i < weight; i++)
	                serverList.add(server);
	        }
	 
	        String server = null;
	        synchronized (pos){
	            if (pos > keySet.size())
	                pos = 0;
	            server = serverList.get(pos);
	            pos ++;
	        }
	 
	        return server;
	    }
	    
	    
	    /**
	     * <p>
	     * @param args <p>
	     * void
	     */
	    public static void main(String[] args) {
	    	for(int i = 0 ; i < 10 ; i++){
	    		String temp = WeightRoundRobin.getServer();
	        	System.out.println("temp="+temp);
	    	}
	    	
		}
}
