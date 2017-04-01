package com.xbmt.common.web;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.xbmt.common.CommonUtils;


/***
 * spring 上下文容器初始化辅助类（非controller之外的地方）<p>
 * @author LingMin 
 * @date 2015-8-5<br>
 * @version 1.0<br>
 */
public class SpringContextUtil implements org.springframework.context.ApplicationContextAware, org.springframework.web.context.ServletContextAware{
	
	/** 日志书写对象**/
	protected static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SpringContextUtil.class);
	/***当前实例对象****/
	private static SpringContextUtil springContextUtil = null;
	/** ServletContext对象 **/
	protected static javax.servlet.ServletContext servletContext;
	/** ApplicationContext对象 **/
	protected static org.springframework.context.ApplicationContext applicationContext;
	/**用户service名称定义***/
	private static String userServerName;
	/***
	 * 私有购置方法
	 */
	private SpringContextUtil(){
	}
	
	/***
	 * 通过静态方法获取当前实例对象<p>
	 * @return <p>
	 * SpringContextUtil
	 */
	public static SpringContextUtil getInstance(){
		if(CommonUtils.isEmptyObject(springContextUtil)){
			springContextUtil = new SpringContextUtil();
		}
		return springContextUtil;
	}
	
	
	/**
	 * 设置WEB层的上下文环境<p>
	 */
	public void setServletContext(javax.servlet.ServletContext servletContext) {
		logger.info("servletContext="+servletContext);
		SpringContextUtil.servletContext = servletContext;
	}
	
	
	/***
	 * 初始化  Spring 应用上下文方法
	 */
	public  void setApplicationContext(ApplicationContext arg0) throws BeansException {
		logger.info("applicationContext="+applicationContext);
		SpringContextUtil.applicationContext = arg0;
	}

	/***
	 * 获取Spring实例bean 对象<p>
	 * @param name beanid名称
	 * @return Object<p>
	 */
	public Object getBean(String name){
	   return applicationContext.getBean(name);
	}
	/***
	 * 获取Spring实例bean 对象<p>
	 * @param classType class类型
	 * @return Object<p>
	 */
	public Object getBean(Class classType){
	   return applicationContext.getBean(classType);
	}
	
	/***
	 * 获取用户service  对象<p>
	 * @param 用户service beanid名称
	 * @return Object<p>
	 */
	public Object getUserServiceBean(){
		//这种方法 也是可以的
//		WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();  
//		logger.info("wac="+wac+" \t ="+wac.getBean(userServerName));
		//logger.info("userServerName="+userServerName);
	   return SpringContextUtil.applicationContext.getBean(userServerName);
	}
	
	/**
	 * 获取ServletContext对象<p>
	 * @return  servletContext  ServletContext对象<br>
	 */
	public javax.servlet.ServletContext getServletContext() {
		return servletContext;
	}

	/**
	 * 获取ApplicationContext对象<p>
	 * @return  applicationContext  ApplicationContext对象<br>
	 */
	public org.springframework.context.ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 设置用户service名称定义<p>
	 * @param  userServerName  用户service名称定义<br>
	 */
	public void setUserServerName(String userServerName) {
		this.userServerName = userServerName;
	}
	
	
	
	
}
