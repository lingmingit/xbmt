package com.xbmt.framework.redis.utils;

import java.util.List;

import com.xbmt.framework.entity.base.Core;


/**
 * 
 * 实体操作辅助类
 * @author LingMin 
 * @date 2016-08-25<br>
 * @version 1.0<br>
 */
public class EntityUtils {

	
	/***
	 * 设置实体中的日志对象 为null,否则 实体对象序列化会出现异常
	 * @param value
	 */
	public static void setEntityLog4jToNull(Object value){
		if(value instanceof Core){
			com.xbmt.framework.entity.base.Core core = (com.xbmt.framework.entity.base.Core)value;
			core.setLogger(null);
		}
		
		if(value instanceof List){
			for(Object temp : (List)value){
				if(temp instanceof Core){
					com.xbmt.framework.entity.base.Core core = (com.xbmt.framework.entity.base.Core)temp;
					core.setLogger(null);
				}
			}
		}
	}
}
