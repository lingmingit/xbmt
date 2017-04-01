/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.framework.web.controller.base;


import com.xbmt.framework.entity.sys.SysUser;
import com.xbmt.framework.service.base.ICoreBaseService;
import com.xbmt.framework.web.utils.WebCommonUtils;

/** 
 * 所有 controller 的 抽象基类<p>
 * @author LingMin 
 * @date 2015-5-13<br>
 * @version 1.0<br>
 */
public abstract class CoreController {

	/** 日志书写对象**/
	protected org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());
	

	
	
	/***
	 * 获取当前登录用户id<p>
	 * @return String 返回userid<p>
	 */
	protected String getCurrentSysUserId(){
		return WebCommonUtils.getCurrentUserId();
	}
	
	/**
	 * 获取当前登录用户实体对象<p>
	 * @return <p>
	 * SysUser
	 */
	protected SysUser getCurrentSysUser(){
		return WebCommonUtils.getCurrentSysUser();
	}
	/**
	 * 获取当前登录用户实体对象<p>
	 * @return <p>
	 * SysUser
	 */
	protected Object getCurrentSysUser(ICoreBaseService userService){
		return userService.get(getCurrentSysUserId());
	}
	
	
	
}
