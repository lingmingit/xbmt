/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.admin.controller;


import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xbmt.common.date.DateUtils;
import com.xbmt.framework.entity.sys.SysUser;
import com.xbmt.framework.web.controller.base.CoreController;

/** 
 * 主页controller定义<p>
 * @author LingMin 
 * @date 2015-7-28<br>
 * @version 1.0<br>
 */
@Controller
public class IndexController extends CoreController {

	
	/****
	 * 登录成功跳转首页<p>
	 * @return <p>
	 * String
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String welcome() {
		logger.info("welcome");
		return "index";
	}
	/*****
	 * 指定首页跳转地址<p>
	 * @return String<p>
	 */
	protected  String getIndexURL(){
		return "main";
	}

	
	/****
	 * 登录成功跳转首页<p>
	 * 第二方式：通过访问主页 传入参数，如： /admin/index2?main=/admin/login2 ，controller中进行默认值
	 * @return <p>
	 * String
	 */
	@RequestMapping(value = "/index2", method = RequestMethod.GET)
	public ModelAndView welcome2() {
		ModelAndView mav = new ModelAndView("index");
		Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.info("index2.object="+object);
		return mav;
	}
	
	
	
	
	
	/****
	 *测试跳转 页面<p>
	 * @return <p>
	 * String
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public ModelAndView test() {
		ModelAndView mav = new ModelAndView("test");
		//Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//mav.addObject("userId", object);
		return mav;
	}
	
	
	
	
	/****
	 * 头部页面 <p>
	 * @return <p>
	 * String
	 */
	@RequestMapping(value = "/head", method = {RequestMethod.GET , RequestMethod.POST})
	public ModelAndView head() {
		ModelAndView mav = new ModelAndView("admin/head");
		Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		logger.info("head.object="+object);
		mav.addObject("currentDate", DateUtils.getCurrentDateBySpecifiedFormatter("yyyy-MM-dd HH:mm:ss"));
		SysUser user = this.getCurrentSysUser();
		mav.addObject("user",user);
		return mav;
	}
	
	/****
	 * 左边页面 <p>
	 * @return <p>
	 * String
	 */
	@RequestMapping(value = "/left", method =  {RequestMethod.GET , RequestMethod.POST})
	public ModelAndView left() {
		ModelAndView mav = new ModelAndView("admin/left");
		//Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return mav;
	}
	
	
	/****
	 * 头部页面 <p>
	 * @return <p>
	 * String
	 */
	@RequestMapping(value = "/main", method =  {RequestMethod.GET , RequestMethod.POST})
	public ModelAndView main() {
		ModelAndView mav = new ModelAndView("admin/main");
		//Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//logger.info("object="+object);
		return mav;
	}
	
	
	/****
	 * 头部页面 <p>
	 * @return <p>
	 * String
	 */
	@RequestMapping(value = "/bottom", method =  {RequestMethod.GET , RequestMethod.POST})
	public ModelAndView bottom() {
		ModelAndView mav = new ModelAndView("admin/bottom");
//		Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		logger.info("object="+object);
		return mav;
	}
	
	
	
	/****
	 * 调整 个人信息修改页面 <p>
	 * @return <p>
	 * String
	 */
	@RequestMapping(value = "/userInfoUpdate", method = {RequestMethod.GET , RequestMethod.POST})
	public ModelAndView openUserInfoUpdate() {
		ModelAndView mav = new ModelAndView("admin/sys/userInfoUpdate");
		SysUser user = this.getCurrentSysUser();
		mav.addObject("data",user);
		return mav;
	}
}
