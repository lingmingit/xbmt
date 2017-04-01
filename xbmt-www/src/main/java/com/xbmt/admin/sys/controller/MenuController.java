/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.admin.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.xbmt.common.CommonUtils;
import com.xbmt.common.StringUtils;
import com.xbmt.framework.dao.common.ConditionParameter;
import com.xbmt.framework.dao.common.SQLCondition;
import com.xbmt.framework.dao.enums.CompareTypeEnum;
import com.xbmt.framework.service.IBaseEntryService;
import com.xbmt.framework.service.base.ICoreBaseService;
import com.xbmt.framework.web.controller.EntryTreeGridController;
import com.xbmt.framework.web.enums.PageStatusEnum;
import com.xbmt.sys.entity.Menu;
import com.xbmt.sys.entity.MenuFunAction;
import com.xbmt.sys.service.IMenuFunActionService;
import com.xbmt.sys.service.IMenuService;
import com.xbmt.sys.service.IModulesService;

/** 
 * 菜单后台管理业务 Controller定义<p>
 * @author LingMin 
 * @date 2015-10-29<br>
 * @version 1.0<br>
 */
@Controller
@RequestMapping(value="/sys/menu")
public class MenuController extends EntryTreeGridController<Menu, String , MenuFunAction , String> {

	
	/***自动注入service***/
	@Autowired
	protected IMenuService menuService;
	
	/***自动注入service***/
	@Autowired
	protected IModulesService modulesService;
	
	/***自动注入service***/
	@Autowired
	protected IMenuFunActionService menuFunActionService;
	
	/***
	 * 是否检查编码重复
	 * @return true验证， false不验证
	 */
	protected boolean isCheckNumberExists(){
		return false;
	}
	
	/***
	 * 基础资料业务操作接口类<p>
	 * @return IDataBaseService <p>
	 */
	protected ICoreBaseService<Menu,String> getCoreBaseService(){
		return this.menuService;
	}
	
	/****
	 * 分录业务操作接口类<p>
	 * @return  <p>
	 */
	protected  IBaseEntryService<MenuFunAction , String> getBaseEntryService(){
		return menuFunActionService;
	}
	
	/****
	 * 指定 分录 实体引用 对象字段名称 数组
	 * @return 返回数组
	 */
	protected String[] getEntryRefFieldNameArray() {
		return new String[]{"action"};
	}

	/***
	 * 将分录实体 转换map对象，去除不需要的字段
	 * 如：将关联字段转换为id，action:{id:'' , name:''} 转换为 action:'id', actionName:'name'
	 * @param entity 分录实体对象
	 * @return 返回map对象
	 */
	protected Map<String, Object> getEntryToMap(MenuFunAction entity) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", entity.getId());
		map.put("action", entity.getAction().getId());
		map.put("aName", entity.getAction().getName());
		map.put("actionName", entity.getActionName());
		map.put("buttonIdName", entity.getButtonIdName());
		
		return map;
	}
	
	
	/*****
	 * 指定首页跳转地址<p>
	 * @return String<p>
	 */
	protected  String getIndexURL(){
		return "sys/menuList";
	}
	
	/*****
	 * 指定 新增表单url<p>
	 * @return String<p>
	 */
	protected String getAddFormURL(){
		return "sys/menuEdit";
	}
	
	/*****
	 * 指定 编辑表单url<p>
	 * @return String<p>
	 */
	protected String getEditFormURL(){
		return "sys/menuEdit";
	}
	
	/*****
	 * 指定 查看表单url<p>
	 * @return String<p>
	 */
	protected String getViewFormURL(){
		return "sys/menuEdit";
	}
	
	
	/***
	 * 执行保存save方法<p>
	 * @param request请求对象
	 * @param entity 操作实体对象<p>
	 * void
	 */
	protected void procesSaveRecord(HttpServletRequest request , Menu entity){
		if(CommonUtils.isNotEmptyObject(entity.getParent())){
			if(StringUtils.isEmpty(entity.getParent().getId())){
				entity.setParent(null);
			}
		}
		if(CommonUtils.isEmptyObject(entity.getLevels())){
			entity.setLevels(0);//默认0 为父级
		}
		//设置 长编码
		String longNumber = entity.getLongNumber();
		if(StringUtils.isNotEmpty(longNumber)){
			longNumber = longNumber.concat(".").concat(entity.getNumbers());
		}else{
			longNumber = entity.getNumbers();
		}
		entity.setLongNumber(longNumber);
		
		this.getCoreBaseService().save(entity);
	}
	
	
	/***
	 * 执行修改update方法 之前调用<p>
	 * @param request请求对象
	 * @param entity 操作实体对象<p>
	 * void
	 */
	protected void beforeUpdate(HttpServletRequest request , Menu entity){
		if(CommonUtils.isNotEmptyObject(entity.getParent())){
			if(StringUtils.isEmpty(entity.getParent().getId())){
				entity.setParent(null);
			}
		}
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
	
	
	/***
	 * 添加 自定义参数到 界面展示<p>
	 * @param request 请求对象
	 * @param mav 传递参数对象ModelAndView
	 * @param pageStatus 界面状态<p>
	 * void
	 */
	protected void addParamToModelAndView(HttpServletRequest request , ModelAndView mav , String pageStatus){
		if(PageStatusEnum.PAGE_ADDNEW.getValue().equals(pageStatus)){
			mav.addObject("moduleId" , request.getParameter("moduleId"));//模块id
			mav.addObject("pId", request.getParameter("pId"));//菜单父id
			mav.addObject("pNumbers", request.getParameter("pNumbers"));//菜单父编码
			String pLevels = request.getParameter("pLevels");
			if(StringUtils.isEmpty(pLevels)){
				pLevels = "0";//默认父级
			}
			mav.addObject("pLevels", pLevels);//菜单父级次
		}
		
	}
	
}
