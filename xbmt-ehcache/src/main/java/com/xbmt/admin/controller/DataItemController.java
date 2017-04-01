/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.admin.controller;

import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xbmt.common.StringUtils;
import com.xbmt.framework.dao.common.ConditionParameter;
import com.xbmt.framework.dao.common.SQLCondition;
import com.xbmt.framework.dao.enums.CompareTypeEnum;
import com.xbmt.framework.web.constants.WebConstants;
import com.xbmt.framework.web.controller.base.CoreController;
import com.xbmt.sys.service.IFunctionActionService;
import com.xbmt.sys.service.IMenuService;
import com.xbmt.sys.service.IModulesService;

/**
 * 基础资料 查询 后台Controller定义 <p>
 * @author LingMin 
 * @date 2015-11-2<br>
 * @version 1.0<br>
 */
@Controller
@RequestMapping(value="/dataItem")
public class DataItemController extends CoreController {
	/***自动注入service***/
	@Autowired
	protected IMenuService menuService;

	/***自动注入service***/
	@Autowired
	protected IModulesService modulesService;
	
	/****自动注入service**********/
	@Autowired
	protected IFunctionActionService functionActionService;
	
	
	/****
	 * [模块]列表数据加载后台业务处理<p>
	 * @return ModelAndView<p>
	 */
	@RequestMapping(value = "/modules", method = { RequestMethod.GET , RequestMethod.POST })
	public ModelAndView modulesList(HttpServletRequest request, HttpServletResponse response) {
		//SQLCondition condition = new SQLCondition();
		//condition.put(new ConditionParameter("parent.id" , null , CompareTypeEnum.COMPARE_IS_NULL));
		SelectItem[] list = this.modulesService.getSelectItemForStatus(true, "name");
		return new ModelAndView(WebConstants.XML_VIEW_NAME, "model", list);
	}
	
	/****
	 * [菜单]列表数据加载后台业务处理<p>
	 * @return ModelAndView<p>
	 */
	@RequestMapping(value = "/menu", method = { RequestMethod.GET , RequestMethod.POST })
	public ModelAndView menuList(HttpServletRequest request, HttpServletResponse response) {
		SQLCondition condition = new SQLCondition();
		String moduleId = request.getParameter("moduleId");
		if(StringUtils.isNotEmpty(moduleId)){
			condition.put(new ConditionParameter("module.id" , moduleId , CompareTypeEnum.COMPARE_EQUEAL));
		}
		logger.info("moduleId="+moduleId);
		condition.put(new ConditionParameter("isLeaf" , "0" , CompareTypeEnum.COMPARE_EQUEAL));
		SelectItem[] list = this.menuService.getSelectItem(condition, "name");
		return new ModelAndView(WebConstants.XML_VIEW_NAME, "model", list);
	}
	
	
	/****
	 * [系统功能]列表数据加载后台业务处理<p>
	 * @return ModelAndView<p>
	 */
	@RequestMapping(value = "/functionAction", method = { RequestMethod.GET , RequestMethod.POST })
	public ModelAndView functionActionList(HttpServletRequest request, HttpServletResponse response) {
		SQLCondition condition = new SQLCondition();
		condition.put(new ConditionParameter("isEnable" , "1" , CompareTypeEnum.COMPARE_EQUEAL));
		SelectItem[] list = this.functionActionService.getSelectItem(condition, "name");
		return new ModelAndView(WebConstants.XML_VIEW_NAME, "model", list);
	}
}
