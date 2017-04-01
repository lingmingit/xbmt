package com.xbmt.framework.redis.cache;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.xbmt.framework.entity.base.Core;
import com.xbmt.framework.redis.utils.EntityUtils;


/**
 * redis cache方法拦截器<p>
 * @author LingMin 
 * @date 2016-08-25<br>
 * @version 1.0<br>
 */
public class MethodCacheInterceptor implements MethodInterceptor {
	
	//日志打印对应
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private RedisTemplate<Serializable, Object> redisTemplate;
	private Long defaultCacheExpireTime = 1000l; // 缓存默认的过期时间,这里设置了10秒
	
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object value = null;

		String targetName = invocation.getThis().getClass().getName();
		String methodName = invocation.getMethod().getName();

		Object[] arguments = invocation.getArguments();
		String key = getCacheKey(targetName, methodName, arguments);

		try {
			logger.info("key="+key);
			// 判断是否有缓存
			if (exists(key)) {
				logger.info("exists="+key);
				return getCache(key);
			}
			// 写入缓存
			value = invocation.proceed();
			if (value != null) {
				//设置实体中的日志对象 为null,否则 实体对象序列化会出现异常
				EntityUtils.setEntityLog4jToNull(value);
				final String tkey = key;
				final Object tvalue = value;
				new Thread(new Runnable() {
					public void run() {
						setCache(tkey, tvalue, defaultCacheExpireTime);
					}
				}).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (value == null) {
				return invocation.proceed();
			}
		}
		return value;
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
	 * 判断缓存中是否有对应的value
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 读取缓存
	 * 
	 * @param key
	 * @return
	 */
	public Object getCache(final String key) {
		Object result = null;
		ValueOperations<Serializable, Object> operations = redisTemplate
				.opsForValue();
		result = operations.get(key);
		return result;
	}



	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setCache(final String key, Object value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate
					.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void setRedisTemplate(
			RedisTemplate<Serializable, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	/**
	 * @param defaultCacheExpireTime the defaultCacheExpireTime to set
	 */
	public void setDefaultCacheExpireTime(Long defaultCacheExpireTime) {
		this.defaultCacheExpireTime = defaultCacheExpireTime;
	}
	
	
	
}

