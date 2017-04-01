package com.xbmt.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.ShardedJedis;

import com.xbmt.framework.redis.utils.EntityUtils;
import com.xbmt.service.RedisDataSource;



/**
 * redis 集群操作JedisTemplate 实现类
 * @author LingMin 
 * @date 2016-08-25<br>
 * @version 1.0<br>
 */
//@Service("jedisTemplate")
public class JedisTemplate {
 
	private  final Logger log = LoggerFactory.getLogger(JedisTemplate.class);

    @Autowired
    private RedisDataSource redisDataSource;
    

    public void disconnect() {
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        shardedJedis.disconnect();
    }

    /**
     * 设置单个值
     * 
     * @param key
     * @param value
     * @return
     */
    public String set(String key, String value , int expireTime) {
        String result = null;

        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
            result = shardedJedis.set(key, value);
            if(expireTime > 0){
            	shardedJedis.expire(key, expireTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }
    
    
    /**
     * 设置Object对象
     * 
     * @param key
     * @param value
     * @return
     */
    public String setObject(String key, Object value , int expireTime) {
        String result = null;

        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }
        boolean broken = false;
        try {
        	byte[] keyByte = key.getBytes();
            result = shardedJedis.set(keyByte, this.toByteArray(value));
            if(expireTime > 0){
            	shardedJedis.expire(keyByte, expireTime);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }
    
    /***
     * 判断某个key是否存在
     * @param key
     * @return
     */
    public boolean exists(String key){
	   ShardedJedis shardedJedis = redisDataSource.getRedisClient();
       if (shardedJedis == null) {
           return false;
       }
       try {
           return shardedJedis.exists(key);
       } catch (Exception e) {
           log.error(e.getMessage(), e);
       } finally {
           redisDataSource.returnResource(shardedJedis);
       }
       return false;
    }

    /**
     * 获取单个值
     * 
     * @param key
     * @return
     */
    public String get(String key) {
        String result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            result = shardedJedis.get(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }
    /**
     * 获取Object对象
     * 
     * @param key
     * @return
     */
    public Object getObject(String key) {
        Object result = null;
        ShardedJedis shardedJedis = redisDataSource.getRedisClient();
        if (shardedJedis == null) {
            return result;
        }

        boolean broken = false;
        try {
            byte[] objectByte = shardedJedis.get(key.getBytes());
            result = this.toObject(objectByte);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            broken = true;
        } finally {
            redisDataSource.returnResource(shardedJedis, broken);
        }
        return result;
    }
    
    /***
     * 清空所有缓存
     */
    public boolean clear(){
         return false;
    }
    
    
    
    /***
     * 删除缓存 某个key  
     */
    public boolean del(Object key){
    	  ShardedJedis shardedJedis = redisDataSource.getRedisClient();
          if (shardedJedis == null) {
              return false;
          }
          try {
        	   byte[] keyBtye = null;
        	   if(key instanceof String){
        		   keyBtye = ((String) key).getBytes();
        	   }else{
        		   keyBtye = this.toByteArray(key);
        	   }
               shardedJedis.del(keyBtye);
               return true;
          } catch (Exception e) {
              log.error(e.getMessage(), e);
          } finally {
              redisDataSource.returnResource(shardedJedis);
          }
          return false;
    }
    
    
    
    
    /** 
     * 描述 : <Object转byte[]>. <br> 
     * <p> 
     * <使用方法说明> 
     * </p> 
     *  
     * @param obj 
     * @return 
     */  
    private byte[] toByteArray(Object obj) {  
        byte[] bytes = null;  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        try {  
            ObjectOutputStream oos = new ObjectOutputStream(bos);  
            oos.writeObject(obj);  
            oos.flush();  
            bytes = bos.toByteArray();  
            oos.close();  
            bos.close();  
        } catch (IOException ex) {  
            ex.printStackTrace();  
        }  
        return bytes;  
    }  
  
    /** 
     * 描述 : <byte[]转Object>. <br> 
     * <p> 
     * <使用方法说明> 
     * </p> 
     *  
     * @param bytes 
     * @return 
     */  
    private Object toObject(byte[] bytes) {  
        Object obj = null;  
        try {  
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);  
            ObjectInputStream ois = new ObjectInputStream(bis);  
            obj = ois.readObject();  
            ois.close();  
            bis.close();  
        } catch (IOException ex) {  
            ex.printStackTrace();  
        } catch (ClassNotFoundException ex) {  
            ex.printStackTrace();  
        }  
        return obj;  
    }  
}
