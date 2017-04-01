package com.lb.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.lb.IpMap;


/***
 * 
 * 几种简单的负载均衡算法及其 Java 代码实现
 * 源地址哈希（Hash）法
 * <p>
 * @author LingMin 
 * @date 2017-1-6<br>
 * @version 1.0<br>
 */
public class HashLB {

	
	/***
	 * <p>
	 * @return <p>
	 * String
	 */
	 public static String getServer()
	    {
	        // 重建一个Map，避免服务器的上下线导致的并发问题
	        Map<String, Integer> serverMap = 
	                new HashMap<String, Integer>();
	        serverMap.putAll(IpMap.serverWeightMap);
	 
	        // 取得Ip地址List
	        Set<String> keySet = serverMap.keySet();
	        ArrayList<String> keyList = new ArrayList<String>();
	        keyList.addAll(keySet);
	 
	        // 在Web应用中可通过HttpServlet的getRemoteIp方法获取
	        String remoteIp = "127.0.0.1";
	        int hashCode = remoteIp.hashCode();
	        int serverListSize = keyList.size();
	        int serverPos = hashCode % serverListSize;
	 
	        return keyList.get(serverPos);
	    }
	 
	 
	 /**
	     * <p>
	     * @param args <p>
	     * void
	     */
	    public static void main(String[] args) {
	    	for(int i = 0 ; i < 10 ; i++){
	    		String temp = HashLB.getServer();
	        	System.out.println("temp="+temp);
	    	}
	    	
		}
}
