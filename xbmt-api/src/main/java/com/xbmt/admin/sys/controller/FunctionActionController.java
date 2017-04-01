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
import com.xbmt.sys.entity.FunctionAction;
import com.xbmt.sys.service.IFunctionActionService;


/***
 * 系统操作功能 后台管理Controller定义<p>
 * @author LingMin 
 * @date 2015-10-29<br>
 * @version 1.0<br>
 */
@Controller
@RequestMapping(value="/sys/functionAction")
public class FunctionActionController extends DataBaseController<FunctionAction, String> {

	/***自动注入service***/
	@Autowired
	IFunctionActionService functionActionService;
	
	/***
	 * 基础资料业务操作接口类<p>
	 * @return IDataBaseService <p>
	 */
	protected ICoreBaseService<FunctionAction,String> getCoreBaseService(){
		return this.functionActionService;
	}
	
	/*****
	 * 指定首页跳转地址<p>
	 * @return String<p>
	 */
	protected  String getIndexURL(){
		return "/sys/functionActionList";
	}
	
	
	
	/*****
	 * 指定 新增表单url<p>
	 * @return String<p>
	 */
	protected String getAddFormURL(){
		return "/sys/functionActionEdit";
	}
	
	/*****
	 * 指定 编辑表单url<p>
	 * @return String<p>
	 */
	protected String getEditFormURL(){
		return "/sys/functionActionEdit";
	}
	
	/*****
	 * 指定 查看表单url<p>
	 * @return String<p>
	 */
	protected String getViewFormURL(){
		return "/sys/functionActionEdit";
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
