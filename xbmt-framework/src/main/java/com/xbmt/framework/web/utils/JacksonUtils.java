/**
 * &lt;p&gt;
 * copyright &amp;copy;  2014, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.framework.web.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


/**
 * Jackson 框架 json格式转换辅助类<p>
 * @author LingMin 
 * @date 2015-5-12<br>
 * @version 1.0<br>
 */
public class JacksonUtils {

	
	
	
	public static List getJsonToList(String jsonStr){
		try {
			return JacksonUtils.getObjectMapper().readValue(jsonStr, List.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/***
	 * 将 对象(实体对象、Map、List等)转换json字符串<p>
	 * 使用 JsonGenerator对象
	 * @param value 实体对象、Map、List
	 * @return String 返回json字符串<p>
	 */
	public static String getJsonGeneratorJsonStr(Object value){
		return JacksonUtils.getJsonGeneratorJsonStr(new StringWriter(), value);
	}
	
	/***
	 * 将 对象(实体对象、Map、List等)转换json字符串<p>
	 * 使用 JsonGenerator对象
	 * @param value 实体对象、Map、List
	 * @param writer输出流对象
	 * @return String 返回json字符串<p>
	 */
	public static String getJsonGeneratorJsonStr(Writer writer ,Object value){
		ObjectMapper objectMapper = new  ObjectMapper();
		//StringWriter writer = new StringWriter();
		try {
			JsonGenerator jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(writer);//, JsonEncoding.UTF8
			jsonGenerator.writeObject(value);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return writer.toString();
	}
	
	/***
	 * 将 对象(实体对象、Map、List等)转换json字符串<p>
	 * 使用 ObjectMapper对象
	 * @param value 实体对象、Map、List
	 * @return String 返回json字符串<p>
	 */
	public static String getObjectMapperJsonStr(Object value){
		return JacksonUtils.getObjectMapperJsonStr(new StringWriter(), value);
	}
	/***
	 * 将 对象(实体对象、Map、List等)转换json字符串<p>
	 * 使用 ObjectMapper对象
	 * @param writer输出流对象
	 * @param value 实体对象、Map、List
	 * @return String 返回json字符串<p>
	 */
	public static String getObjectMapperJsonStr(Writer writer , Object value){
		//StringWriter writer = new StringWriter();
		try {
			JacksonUtils.getObjectMapper().writeValue(writer, value);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return writer.toString();
	}
	
	/***
	 * 获取转换器对象【ObjectMapper】<p>
	 * @return ObjectMapper<p>
	 */
	public static ObjectMapper getObjectMapper(){
		return new ObjectMapper();
	}
	
	/***
	 * 获取转换器对象【JsonGenerator】<p>
	 * @param writer输出流对象
	 * @return ObjectMapper<p>
	 * @throws IOException 
	 */
	public static JsonGenerator getJsonGenerator(Writer writer) throws IOException{
		return JacksonUtils.getObjectMapper().getJsonFactory().createJsonGenerator(writer);
	}
}
