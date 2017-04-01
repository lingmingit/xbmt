/**
 * 
 */
package com.xbmt.front;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/***
 * 前台首页controller定义<p>
 * @author LingMin 
 * @date 2015年11月17日<br>
 * @version 1.0<br>
 */
@Controller
@RequestMapping(value="/front")
public class IndexController  {

	/*****
	 * 跳转后台管理登录方法<p>
	 * @return <p>
	 * String
	 */
	@RequestMapping(value = "/index.jspx", method = RequestMethod.GET)
	public String index() {
		
		return "index";
	}

	/*****
	 * 跳转首页<p>
	 * @return <p>
	 * String
	 */
	@RequestMapping(value = "/indexA.jspx", method = RequestMethod.GET)
	public String indexA(HttpServletRequest request , HttpServletResponse response,  ModelMap model) {
		
		return "indexA";
	}
	
	/*****
	 * 跳转首页<p>
	 * @return <p>
	 * String
	 */
	@RequestMapping(value = "/indexB.jspx", method = RequestMethod.GET)
	public String indexB(HttpServletRequest request , HttpServletResponse response,  ModelMap model) {
		
		return "indexB";
	}
	
	
	/*****
	 * 测试 Session 复制问题<p>
	 * @return <p>
	 * String
	 */
	@RequestMapping(value = "/session.jspx", method = {RequestMethod.GET ,  RequestMethod.POST})
	public String session(HttpServletRequest request , HttpServletResponse response,  ModelMap model) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			String dataName = request.getParameter("dataName");
			String dataValue = request.getParameter("dataValue");
			System.out.println("dataName="+dataName+" \t dataValue="+dataValue);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "session";
	}
}
