/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.admin.sys.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xbmt.common.StringUtils;
import com.xbmt.framework.dao.common.ConditionParameter;
import com.xbmt.framework.dao.common.SQLCondition;
import com.xbmt.framework.dao.enums.CompareTypeEnum;
import com.xbmt.framework.service.base.ICoreBaseService;
import com.xbmt.framework.web.controller.DataBaseController;
import com.xbmt.sys.entity.Modules;
import com.xbmt.sys.service.IModulesService;

/** 
 * 系统模块 后台管理Controller 业务处理类<p>
 * @author LingMin 
 * @date 2015-10-15<br>
 * @version 1.0<br>
 */
@Controller
@RequestMapping(value="/sys/modules")
public class ModulesController extends DataBaseController<Modules,String> {

	/***自动注入service***/
	@Autowired
	protected IModulesService modulesService;
	
	
	/***
	 * 基础资料业务操作接口类<p>
	 * @return IDataBaseService <p>
	 */
	protected ICoreBaseService<Modules,String> getCoreBaseService(){
		return this.modulesService;
	}
	
	/*****
	 * 指定首页跳转地址<p>
	 * @return String<p>
	 */
	protected  String getIndexURL(){
		return "sys/modulesList";
	}
	
	
	
	/*****
	 * 指定 新增表单url<p>
	 * @return String<p>
	 */
	protected String getAddFormURL(){
		return "sys/modulesEdit";
	}
	
	/*****
	 * 指定 编辑表单url<p>
	 * @return String<p>
	 */
	protected String getEditFormURL(){
		return "sys/modulesEdit";
	}
	
	/*****
	 * 指定 查看表单url<p>
	 * @return String<p>
	 */
	protected String getViewFormURL(){
		return "sys/modulesEdit";
	}
	
	
	/****
	 * 指定 列表查询 条件对象<p>
	 * @param request
	 * @return SQLCondition<p>
	 */
	protected SQLCondition getSeachSQLCondition(HttpServletRequest request){
		String numbers = request.getParameter("numbers");
		String name = request.getParameter("name");
		SQLCondition condition = new SQLCondition();
		if(StringUtils.isNotEmpty(numbers)){
			condition.put(new ConditionParameter("numbers" , numbers , CompareTypeEnum.COMPARE_LIKE));
		}
		if(StringUtils.isNotEmpty(name)){
			condition.put(new ConditionParameter("name" , name , CompareTypeEnum.COMPARE_LIKE));
		}
		return condition;
	}
}
