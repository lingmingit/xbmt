package com.xbmt.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



/****
 * 属性配置文件操作处理<p>
 * @author LingMin 
 * @date 2015-02-10<br>
 * @version 1.0<br>
 */
public class PropertiesUtils {

	/**
	 * 构造函数<p>
	 */
	private PropertiesUtils() {}
	
	
	/****
	 * 获取配置文件中的相关配置信息
	 * @param key 配置文件key信息
	 * @return 返回key对应的value信息
	 */
	public static String getPropertiesKeyValue(String key , String flieName){
		InputStream inputStream = null;
		try {
			inputStream = PropertiesUtils.class.getResourceAsStream(flieName);
			Properties properties = new Properties();
			properties.load(inputStream);
			String value = properties.getProperty(key);
			if(StringUtils.isNotEmpty(value)){
				value = value.trim();
			}
			return value;
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				if(CommonUtils.isNotEmptyObject(inputStream)){
					inputStream.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	/**
	 * 根据文件路径加载资源配置文件<p>
	 * @param path 文件路径<br>
	 * @return Properties文档对象<br>
	 */
	public static java.util.Properties load(String path) {
		java.util.Properties props =null;
		java.io.InputStream input = null;
		if (StringUtils.isNotEmpty(path)) {
			try {
				props = new java.util.Properties();
				input = new java.io.FileInputStream(path);
				props.load(input);
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					if (input != null) {
						input.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return props;
	}
	
	/**
	 * 记载CLASS路径下的配置文件<p>
	 * @param clz 字节码类<br>
	 * @param path 路径<br>
	 * @return 配置文件对象<br>
	 */
	public static java.util.Properties load(String className, String name) {
		java.util.Properties props = null;
		java.io.InputStream input = null;
		try {
			if (StringUtils.isNotEmpty(className) && StringUtils.isNotEmpty(name)) {
				props = new java.util.Properties();
				Class<?> clz = Class.forName(className);
				input = clz.getResourceAsStream("/".concat(clz.getPackage().getName()).replace(".", "/").concat("/").concat(name).concat(".properties"));
				props.load(input);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return props;
	}
	
	/**
	 * 保存Properties对象入指定的文件中<p>
	 * @param props 文档对象<br>
	 * @param path 文档路径<br>
	 * @param comment 注释信息<br>
	 */
	public static void save(java.util.Properties props, String path, String comment) {
	}
	
	/**
	 * 根据关键字获取对应的属性值<p>
	 * @param props 文档对象<br>
	 * @param key 关键字<br>
	 * @return 属性值<br>
	 */
	public static String getAttributeByKey(java.util.Properties props, String key) {
		return CommonUtils.isNotEmptyObject(props) && props.containsKey(key) ? props.getProperty(key) : "";
	}
	
	/**
	 * 移除指定属性及值<p>
	 * @param props 文档对象<br>
	 * @param key 关键字<br>
	 */
	public static void removeAttributeByKey(java.util.Properties props, String key) {
		if (props.containsKey(key)) props.remove(key);
	}
	
	/**
	 * 向指定的Properties文件中增加属性及值<p>
	 * @param props 文档对象<br>
	 * @param key 关键字<br>
	 * @param value 属性值<br>
	 */
	public static void addAttribute(java.util.Properties props, String key, String value) {
		if (CommonUtils.isNotEmptyObject(props) && StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) props.put(key, value);
	}
	
	/**
	 * 根据关键字获取对应的Boolean类型属性值<p>
	 * @param props 文档对象<br>
	 * @param key 关键字<br>
	 * @return Boolean属性值<br>
	 */
	public static boolean getBooleanAttributeByKey(java.util.Properties props, String key) {
		return CommonUtils.getBooleanFromString(PropertiesUtils.getAttributeByKey(props, key));
	}
}
