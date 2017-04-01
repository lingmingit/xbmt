/**
 * 
 */
package com.xbmt.front.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xbmt.framework.web.controller.base.CoreController;

/***
 * 前台首页controller定义<p>
 * @author LingMin 
 * @date 2015年11月17日<br>
 * @version 1.0<br>
 */
@Controller
@RequestMapping(value="/front")
public class IndexController extends CoreController {

	

	/*****
	 * 跳转后台管理登录方法<p>
	 * @return <p>
	 * String
	 */
	@RequestMapping(value = "/index.jspx", method = RequestMethod.GET)
	public String index() {
		return "index";
	}
}
