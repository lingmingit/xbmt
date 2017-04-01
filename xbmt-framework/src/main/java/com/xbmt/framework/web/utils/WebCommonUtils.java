/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.framework.web.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;

import com.xbmt.common.CommonUtils;
import com.xbmt.common.StringUtils;
import com.xbmt.framework.entity.sys.SysUser;

/** 
 * web 处理 帮助类<p>
 * @author LingMin 
 * @date 2015-9-2<br>
 * @version 1.0<br>
 */
public class WebCommonUtils {

	/***
	 * 获取当前登录用户id<p>
	 * @return <p>
	 * String
	 */
	public static String getCurrentUserId(){
		if(CommonUtils.isNotEmptyObject(SecurityContextHolder.getContext().getAuthentication())){
			Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(object instanceof org.springframework.security.core.userdetails.User){
				org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)object;
				return user.getUsername();
			}else if(object instanceof String){
				if("anonymousUser".equals((String)object)){
					return null;
				}
				return object.toString();
			}else{
				return object.toString();
			}
		}
		return null;
	}
	
	
	/**
	 * 获取当前登录用户实体对象<p>
	 * @return SysUser<p>
	 * 
	 */
	public static SysUser getCurrentSysUser(){
		String userId = WebCommonUtils.getCurrentUserId();
		if(StringUtils.isNotEmpty(userId)){
			SysUser user = new SysUser();
			user.setId(userId);
			return user;
		}
		return null;
	}
	
	
	/***
	 * 提示错误消息到前台
	 * @param message 错误消息
	 * @param request http请求对象
	 * @param response http响应对象
	 */ 
	public static void gotoMsgPage(String message , HttpServletRequest request, HttpServletResponse response){
		try {
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 将错误信息 通过ajax 返回到前台
	 * @param message 错误消息
	 * @param request http请求对象
	 * @param response http响应对象
	 */ 
	public static void ajaxWriterMsgPage(String message , HttpServletRequest request, HttpServletResponse response){
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/plain");
			response.getWriter().write(message);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
