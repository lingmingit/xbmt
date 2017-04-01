/**
 * 
 */
package com.test.ehcache;

import java.io.IOException;
import java.io.InputStream;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 
 * 测试集群 缓存配置  ehcache1.xml
 * 测试两个缓存配置的 通讯  机制（相互写入缓存，并进行读取）
 * @author admin
 */
public class TestEhcache1 {

	/** 日志书写对象 **/
	protected org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestEhcache1 test = new TestEhcache1();
		try {
			test.testEhcache();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	public CacheManager getCacheManager(){
		 InputStream is=null;  
	        CacheManager manager=null;  
	        try {  
	            is = TestEhcache1.class.getResourceAsStream("/ehcache1.xml");  
	              manager = CacheManager.create(is);  
	        } catch (CacheException e1) {  
	            try {  
	                if(is!=null){  
	                is.close();  
	                is=null;  
	                }  
	            } catch (IOException e) {  
	                e.printStackTrace();  
	            }  
	            e1.printStackTrace();  
	        } 
	        if(manager == null){
	        	logger.info("CacheManager is null");
	        }
	        return manager;
	}
	
	
	public void testEhcache2() throws InterruptedException{
		 
	     Cache cache = this.getCacheManager().getCache("xbmtCache");    
	     //String key = "0+com.xbmt.sys.entity.Modules";
        int i=0;  
        while (true)    {    
            Thread.sleep(3000);    
            System.out.println("\n");    
            for (Object key : cache.getKeys())    {    
                System.out.println(key + ":" + cache.get(key).getObjectValue());    
            }    
            i++;  
        }    
	}

	
	public void testEhcache() throws InterruptedException{
		 
	             
	       Cache cache = this.getCacheManager().getCache("xbmtCache");    
           Element element = new Element("client1" + System.currentTimeMillis(), "client1");    
           cache.put(element);    
           int i=0;  
           while (true)    {    
               Element element2 = new Element("client-1-"+i,i);   
               cache.put(element2);  
               Thread.sleep(3000);    
               System.out.println("\n");    
               for (Object key : cache.getKeys())    {    
            	   logger.info(key + ":" + cache.get(key).getObjectValue());    
               }    
               i++;  
           }    
	}

}
