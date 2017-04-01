package com.xbmt.admin.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.octo.captcha.service.image.ImageCaptchaService;
import com.xbmt.common.StringUtils;
import com.xbmt.framework.web.controller.base.CoreController;

/***
 * 后台管理登录controller定义<p>
 * @author LingMin 
 * @date 2015年7月24日<br>
 * @version 1.0<br>
 */
@Controller
public class AdminLoginController extends CoreController {

	
	
	/** 图片验证码生成器 **/
	@Autowired
	private ImageCaptchaService captchaLogicBean;
	
	
	/*****
	 * 指定首页跳转地址<p>
	 * @return String<p>
	 */
	protected  String getIndexURL(){
		return "";
	}

	
	/*****
	 * 跳转后台管理登录方法<p>
	 * @return <p>
	 * String
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String gotoLogin() {
		return "login";
	}

	
	
	
	/*****
	 * 跳转后台管理登录方法<p>
	 * @return <p>
	 * String
	 */
	@RequestMapping(value = "/login2", method = RequestMethod.GET)
	public String gotoLogin2(HttpServletRequest request) {
		String error = request.getParameter("loginError");
		request.setAttribute("loginError", error);
		logger.info("error="+error);
		return "login2";
	}
	
	
	
	/****
	 * 验证后台管理登录 操作验证码 <p>
	 * @return String<p>
	 */
	@RequestMapping(value = "/validateCode/{authCode}", method = RequestMethod.POST)
	public String validateCode(@PathVariable String authCode , HttpServletRequest request , HttpServletResponse response) {
		try {
			StringBuffer resultMsg = new StringBuffer();
			resultMsg.append("{");
			if(StringUtils.isNotEmpty(authCode)){
				if (captchaLogicBean.validateResponseForID(request.getSession(false).getId(), authCode)) {
					resultMsg.append("flag:true,msg:\"验证码正确!!\"");
				}else{
					resultMsg.append("flag:false,msg:\"验证码错误!!\"");
				}
			}else{
				resultMsg.append("flag:false,msg:\"请求参数异常!!\"");
			}
			resultMsg.append("}");
			logger.info("resultMsg="+resultMsg);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/plain");
			response.getWriter().write(resultMsg.toString());
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
