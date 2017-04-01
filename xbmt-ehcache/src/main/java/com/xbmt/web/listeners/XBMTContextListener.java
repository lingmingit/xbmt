package com.xbmt.web.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.xbmt.common.PropertiesUtils;
import com.xbmt.web.constants.XBMTConstant;


/***
 * 服务器上下文 监听器 <p>
 * @author LingMin 
 * @date 2015年11月19日<br>
 * @version 1.0<br>
 */
public class XBMTContextListener implements ServletContextListener{

	
	/** 日志书写对象**/
	protected org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());
	
	
	/****
	 * 服务器上下文
	 */
	private ServletContext context = null;   
	/****
	 * 服务器上下初始化方法
	 */
	public void contextInitialized(ServletContextEvent sce) {
		this.context = sce.getServletContext(); 
		String uri = PropertiesUtils.getPropertiesKeyValue(XBMTConstant.XBMTAdminRequestURIKeyName, XBMTConstant.XBMTPropertiesFileName);
		logger.info("uri="+uri);
		context.setAttribute(XBMTConstant.XBMTAdminRequestURIName, uri);
	}

	/***
	 * 服务器上下文 销毁方法
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
