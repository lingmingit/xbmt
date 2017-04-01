package com.xbmt.framework.redis.cache;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import com.xbmt.framework.redis.utils.EntityUtils;
import com.xbmt.service.impl.JedisTemplate;


/**
 * redis cache 集群 处理类<p>
 * 使用另一种方式set 缓存 测试通过
 * 如：set 缓存 redisTemplate.expire(key, expireTime, TimeUnit.SECONDS); 
 *  序列化 org.springframework.data.redis.serializer.JdkSerializationRedisSerializer
 * @author LingMin 
 * @date 2016-08-25<br>
 * @version 1.0<br>
 */
public class RedisCache3 implements Cache { 
	//日志打印对应
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
    //
    private JedisTemplate jedisTemplate;  
    private String name;  
  
    int liveTime = 86400;  
    /**
	 * @return the jedisTemplate
	 */
	public JedisTemplate getJedisTemplate() {
		return jedisTemplate;
	}

	/**
	 * @param jedisTemplate the jedisTemplate to set
	 */
	public void setJedisTemplate(JedisTemplate jedisTemplate) {
		this.jedisTemplate = jedisTemplate;
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
        return this.jedisTemplate;  
    }  
  
    
    /***
     * 根据key 获取缓存
     */
    @Override  
    public ValueWrapper get(Object key) {  
    	
        String keyf = (String) key;  
        Object object  = this.getCache(keyf);
		return (object != null ? new SimpleValueWrapper(object) : null);  
    }  
  
    /***
     * 设置缓存
     */
    public void put(Object key, Object value) {  
    	 //设置实体中的日志对象 为null,否则 实体对象序列化会出现异常
		EntityUtils.setEntityLog4jToNull(value);
        this.jedisTemplate.setObject((String)key, value, liveTime);
    }  
  
  
   /***
    * 删除缓存 某个key  
    */
    public void evict(Object key) {  
    	jedisTemplate.del(key);
    }  
  
    /***
     * 清空所有缓存
     */
    public void clear() {
    	logger.info("clear......");
    	jedisTemplate.clear();
    }

	@Override
	public <T> T get(Object arg0, Class<T> arg1) {
		return null;
	}

	@Override
	public ValueWrapper putIfAbsent(Object arg0, Object arg1) {
		return null;
	}  

	
	/**
	 * 创建缓存key
	 *
	 * @param targetName
	 * @param methodName
	 * @param arguments
	 */
	private String getCacheKey(String targetName, String methodName,
			Object[] arguments) {
		StringBuffer sbu = new StringBuffer();
		sbu.append(targetName).append("_").append(methodName);
		if ((arguments != null) && (arguments.length != 0)) {
			for (int i = 0; i < arguments.length; i++) {
				sbu.append("_").append(arguments[i]);
			}
		}
		return sbu.toString();
	}


	

	/**
	 * 读取缓存
	 * 
	 * @param key
	 * @return
	 */
	public Object getCache(final String key) {
		return jedisTemplate.get(key);
	}

}
