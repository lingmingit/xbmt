/**
 * 
 */
package com.xbmt.admin.utils;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import com.xbmt.framework.web.utils.EntryDataUtils;
import com.xbmt.framework.web.utils.JacksonUtils;
import com.xbmt.sys.entity.MenuFunAction;

/** 
 * 分录后台处理 帮助类<p>
 * @author LingMin 
 * @date 2015-12-04<br>
 * @version 1.0<br>
 */
public class TestEntryDataUtils {

	
	
	
	
	
	public static void testJackson(){
		//String jsonStr = "[{\"name\":\"新增\",\"action\":\"402896e451479dff015147b8bc4b0000\",\"buttonIdName\":\"11\",\"actionName\":\"11\"},{\"name\":\"编辑\",\"action.id\":\"402896e451479dff015147bb13b30001\",\"buttonIdName\":\"22\",\"actionName\":\"22\"}]";
				String jsonStr = "[{\"name\":\"新增\",\"action\":{\"id\":\"402896e451479dff015147b8bc4b0000\"},\"buttonIdName\":\"11\",\"actionName\":\"11\"},{\"name\":\"编辑\",\"action\":{\"id\":\"402896e451479dff015147bb13b30001\"},\"buttonIdName\":\"22\",\"actionName\":\"22\"}]";
				try {
					
					ObjectMapper objectMapper = JacksonUtils.getObjectMapper();
					 // 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性  
			        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);  
			        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false); 
					MenuFunAction[] array = objectMapper.readValue(jsonStr, MenuFunAction[].class);
					
					System.out.println("array="+array);
				} catch (JsonParseException e) {
					e.printStackTrace();
				} catch (JsonMappingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	}
	
	/***
	 * 测试方法
	 * @param args
	 */
	public static void main(String[] args) {
		//String jsonStr = "[{\"name\":\"新增\",\"action.id\":\"402896e451479dff015147b8bc4b0000\",\"buttonIdName\":\"11\",\"actionName\":\"11\"},{\"name\":\"编辑\",\"action.id\":\"402896e451479dff015147bb13b30001\",\"buttonIdName\":\"22\",\"actionName\":\"22\"}]";
		//String jsonStr = "[{\"name\":\"新增\",\"action\":{\"id\":\"402896e451479dff015147b8bc4b0000\"},\"buttonIdName\":\"11\",\"actionName\":\"11\"},{\"name\":\"编辑\",\"action\":{\"id\":\"402896e451479dff015147bb13b30001\"},\"buttonIdName\":\"22\",\"actionName\":\"22\"}]";
		String jsonStr = "[{\"name\":\"新增\",\"action\":\"402896e451479dff015147b8bc4b0000\",\"buttonIdName\":\"11\",\"actionName\":\"11\"},{\"name\":\"编辑\",\"action\":\"402896e451479dff015147bb13b30001\",\"buttonIdName\":\"22\",\"actionName\":\"22\"}]";
		//List list = JSON.parseArray(jsonStr, MenuFunAction.class);
		//System.out.println("list="+list);		
		
		//JSONArray jsonArray=JSON.parseArray(jsonStr); 
		//System.out.println("jsonArray="+jsonArray.size());	
		
		
		List list = EntryDataUtils.getJSONStrToEntityList( MenuFunAction.class, jsonStr, new String[]{"action"});
		System.out.println("list="+list);
	}
}
