package com.xbmt.framework.web.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xbmt.common.CommonUtils;
import com.xbmt.common.ReflectionUtils;
import com.xbmt.common.StringUtils;
import com.xbmt.framework.entity.base.Core;



/** 
 * 分录后台处理 帮助类<p>
 * @author LingMin 
 * @date 2015-12-04<br>
 * @version 1.0<br>
 */
public class EntryDataUtils {

	/***空的json 字符串***/
	public static String EmptyJSONStr = "{}";
	/***空的json 数组字符串 ***/
	public static String EmptyJSONArrayStr = "[{}]";
	

	/***
	 * 将分录json 字符串对象转换为 实体list对象
	 * @param entryClass 分录实体class对象
	 * @param jsonStr json字符串
	 * @param refSuperClass 分类对象属性基类
	 * @param parentObject父实体对象
	 * @param parentFieldName 分录关联父实体属性名称
	 * @return 返回转换后台list对象
	 */
	public static List getJSONStrToEntityList(Class<?> entryClass ,String jsonStr , String[] refFieldNameArray , Object parentObject , String parentFieldName){
		if(StringUtils.isEmpty(jsonStr)){
			return null;
		}
		List list = new ArrayList();
		JSONArray jsonArray = JSON.parseArray(jsonStr); 
		if(CommonUtils.isEmptyObject(jsonArray) || jsonArray.isEmpty()){
			return null;
		}
		for(int i = 0 ; i < jsonArray.size() ; i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			if(jsonObject.isEmpty()){
				continue;
			}
			Object entityObj = EntryDataUtils.getJSONObjectToEntity(entryClass, jsonObject, refFieldNameArray);
			if(CommonUtils.isNotEmptyObject(entityObj)){
				list.add(entityObj);
			}
			if(StringUtils.isNotEmpty(parentFieldName)){
				//根据 属性名称 设置 set方法名称
				String methodName = "set".concat(StringUtils.firstCharToUpperCase(parentFieldName));
				if(CommonUtils.isNotEmptyObject(parentObject)){
					//设置引用属性 实体字段
					ReflectionUtils.invokeMethod(entityObj, methodName, new Object[]{parentObject}, new Class[]{parentObject.getClass()});
				}else{
					//设置引用属性 实体字段
					//ReflectionUtils.invokeMethod(entityObj, methodName, new Object[]{parentObject}, new Class[]{});
				}
			}
		}
		return list;
	}
	
	
	/***
	 * 将分录json 字符串对象转换为 实体list对象
	 * @param entryClass 分录实体class对象
	 * @param jsonStr json字符串
	 * @param refSuperClass 分类对象属性基类
	 * @return 返回转换后台list对象
	 */
	public static List getJSONStrToEntityList(Class<?> entryClass ,String jsonStr , String[] refFieldNameArray){
		return EntryDataUtils.getJSONStrToEntityList(entryClass, jsonStr, refFieldNameArray, null, null);
	}
	
	
	/***
	 * 将单个json对象 转换为 实体对象
	 * @param entryClass 分录实体class类型
	 * @param jsonObject 单个json 对象 
	 * @param refFieldNameArray 指定那些属性为引用对象
	 * @return 返回分录实体对象
	 */
	public static Object getJSONObjectToEntity(Class entryClass , JSONObject jsonObject , String[] refFieldNameArray){
		if(CommonUtils.isEmptyObject(jsonObject)){
			return null;
		}
		//第1步：首先将jsonObject中引用对象字段的id值取出存放到map，并同时将其删除
		Map<String , String> map = new HashMap<String, String>();
		if(CommonUtils.isNotEmptyObjectArray(refFieldNameArray)){
			for(String refFileName : refFieldNameArray){
				map.put(refFileName, jsonObject.getString(refFileName));
				jsonObject.remove(refFileName);
			}
		}
		//第2步：将其它属性通过 fastjson-1.2.7 转换为实体对象
		Object entityObj = JSON.parseObject(jsonObject.toJSONString(), entryClass);
		//第3步：再将对象引用属性设置
		if(CommonUtils.isNotEmptyObjectArray(refFieldNameArray)){
			for(String refFileName : refFieldNameArray){
				if(ReflectionUtils.isExistFieldForDeep(entryClass, refFileName)){
					EntryDataUtils.setReferenceFieldValue(entityObj , refFileName, map.get(refFileName));
				}
			}
		}
		return entityObj;
	}
	
	
	
	/***
	 * 设置关联引用属性实体 id属性值
	 * @param 分录主实体entityObj对象
	 * @param refFileName 属性名称
	 * @param fieldValue 属性值
	 */
	public static void setReferenceFieldValue(Object entityObj , String refFileName , String fieldValue ){
		try {
			java.lang.reflect.Field field = entityObj.getClass().getDeclaredField(refFileName);
			String refType = field.getType().getName();
			Object object = ReflectionUtils.newInstance(refType);
			if(object instanceof Core){
				//设置 关联引用实体的id 值
				ReflectionUtils.invokeMethod(object, "setId", new Object[]{fieldValue}, new Class[]{String.class});
				//根据 属性名称 设置 set方法名称
				String methodName = "set".concat(StringUtils.firstCharToUpperCase(refFileName));
				//设置引用属性 实体字段
				ReflectionUtils.invokeMethod(entityObj, methodName, new Object[]{object}, new Class[]{object.getClass()});
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

}
