/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.framework.web.interceptors;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/** 
 * 
 * 自定义拦截器 用于解决 前端跨域问题<p>
 * [增加response响应头信息，解决跨域请求问题]
 * @author LingMin 
 * @date 2015-5-13<br>
 * @version 1.0<br>
 */
public class AccessKeyInterceptor extends HandlerInterceptorAdapter{

	/** 日志书写对象**/
	protected org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());
	
	private String accessKeyParameterName="accessKey";  
	
    private List<String> defaultAccessAllowedFrom;
	 
    
    /****
     * 重写处理逻辑方法
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {  
        String accessKey=request.getParameter(accessKeyParameterName);  
        String referer = request.getHeader("Referer"); 
        logger.info("referer="+referer);
       // URL u = new URL(referer);  
       // String host = u.getHost().toLowerCase();  
        if(accessKey == null){  
        	logger.error("====================================ILLEGAL ACCESS: ACCESS_KEY_MISSING!=======================");  
        }else{  
        	logger.debug("====================================ACCESS WITH Access KEY:"+accessKey+"====================");  
//	            IAccess access = accessService.getAccess(UserSessionUtils.getUserSession(request), accessKey);  
//	            if(access != null){  
//	                defaultAccessAllowedFrom=access.getAccessAllowedFrom();  
//	            }else{  
//	            	logger.warn("======================================ACCESS KEY:"+accessKey+" DOES NOT EXIST!=================");      
//	            }  
        }  
//	        for(String s : defaultAccessAllowedFrom) {  
//	            if(host.matches(s)){                              
//	                response.setHeader("Access-Control-Allow-Origin", referer);  
//	                break;  
//	            }  
//	        }
        /***
         * Access-Control-Allow-Origin: "*"
			Access-Control-Allow-Methods: "GET"
			Access-Control-Max-Age: "60"
         */
        response.setHeader("Access-Control-Allow-Origin", "*"); 
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");  
        response.setHeader("Access-Control-Allow-Methods", "GET");  
        response.setHeader("Access-Control-Max-Age", "60");  
        response.setHeader("Allow", "GET");  
        return true;  
    }  
	    
	 
	 
	    public List<String> getDefaultAccessAllowedFrom() {  
	        return defaultAccessAllowedFrom;  
	    }  
	  
	    public void setDefaultAccessAllowedFrom(List<String> defaultAccessAllowedFrom) {  
	        this.defaultAccessAllowedFrom = defaultAccessAllowedFrom;  
	    }  
	  
	    public String getAccessKeyParameterName() {  
	        return accessKeyParameterName;  
	    }  
	  
	    public void setAccessKeyParameterName(String accessKeyParameterName) {  
	        this.accessKeyParameterName = accessKeyParameterName;  
	    } 
}
