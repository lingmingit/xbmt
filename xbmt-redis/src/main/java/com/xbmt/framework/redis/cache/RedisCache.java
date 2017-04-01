package com.xbmt.framework.redis.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import com.xbmt.framework.redis.utils.EntityUtils;



/**
 * redis cache 处理类<p>
 *    如：set 缓存 匿名类 Callback redisTemplate.execute(new RedisCallback<Object>()
 *    自定义实现序列化  toByteArray |  toObject
 * @author LingMin 
 * @date 2016-08-25<br>
 * @version 1.0<br>
 */
public class RedisCache implements Cache {  
	
	//日志打印对应
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
  
    private RedisTemplate<String, Object> redisTemplate;  
    private String name;  
  
    public RedisTemplate<String, Object> getRedisTemplate() {  
        return redisTemplate;  
    }  
  
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {  
        this.redisTemplate = redisTemplate;  
    }  
  
    public void setName(String name) {  
        this.name = name;  
    }  
  
    @Override  
    public String getName() {  
        return this.name;  
    }  
  
    @Override  
    public Object getNativeCache() {  
        return this.redisTemplate;  
    }  
  
    @Override  
    public ValueWrapper get(Object key) {  
    	
        final String keyf = (String) key;  
        Object object = null;  
        object = redisTemplate.execute(new RedisCallback<Object>() {  
            public Object doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
  
                byte[] key = keyf.getBytes();  
                byte[] value = connection.get(key);  
                if (value == null) {  
                    return null;  
                }  
                return toObject(value);  
  
            }  
        });  
        return (object != null ? new SimpleValueWrapper(object) : null);  
    }  
  
    @Override  
    public void put(Object key, Object value) {  
    	 //设置实体中的日志对象 为null,否则 实体对象序列化会出现异常
		EntityUtils.setEntityLog4jToNull(value);
        final String keyf = (String) key;  
        final Object valuef = value;  
        final long liveTime = 86400;  
     
        redisTemplate.execute(new RedisCallback<Long>() {  
            public Long doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                byte[] keyb = keyf.getBytes();  
                byte[] valueb = toByteArray(valuef);  
                connection.set(keyb, valueb);  
                if (liveTime > 0) {  
                    connection.expire(keyb, liveTime);  
                }  
                return 1L;  
            }  
        });  
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
  
    @Override  
    public void evict(Object key) {  
        final String keyf = (String) key;  
        redisTemplate.execute(new RedisCallback<Long>() {  
            public Long doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                return connection.del(keyf.getBytes());  
            }  
        });  
    }  
  
    @Override  
    public void clear() {  
        redisTemplate.execute(new RedisCallback<String>() {  
            public String doInRedis(RedisConnection connection)  
                    throws DataAccessException {  
                connection.flushDb();  
                return "ok";  
            }  
        });  
    }

	@Override
	public <T> T get(Object arg0, Class<T> arg1) {
		return null;
	}

	@Override
	public ValueWrapper putIfAbsent(Object arg0, Object arg1) {
		return null;
	}  

}
