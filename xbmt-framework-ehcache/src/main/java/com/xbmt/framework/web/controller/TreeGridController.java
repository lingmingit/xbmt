/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.framework.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xbmt.framework.dao.common.ConditionParameter;
import com.xbmt.framework.dao.common.SQLCondition;
import com.xbmt.framework.dao.enums.CompareTypeEnum;
import com.xbmt.framework.entity.AbstractTreeData;
import com.xbmt.framework.service.ITreeDataService;
import com.xbmt.framework.web.constants.WebConstants;
import com.xbmt.framework.web.enums.PageStatusEnum;

/**
 *  树形列表 基类Controller定义<p>
 * @author LingMin 
 * @date 2015-10-29<br>
 * @version 1.0<br>
 */
public abstract class TreeGridController<E extends AbstractTreeData, ID extends java.io.Serializable> extends DataBaseController<E, ID> {


	
	
	
	/***
	 * 树形业务操作接口类<p>
	 * @return IDataBaseService <p>
	 */
	protected ITreeDataService<E , ID> getTreeDataService(){
		return (ITreeDataService<E , ID>)this.getCoreBaseService();
	}
	
	/***
	 * 指定 查询结果排序字段<p>
	 * @return <p>
	 * String
	 */
	protected String getOrderbyFieldName(){
		return "seqNo";
	}
	
	/*****
	 * 指定 新增表单url<p>
	 * @return String<p>
	 */
	protected String getAddChildrenFormURL(){
		return this.getAddFormURL();
	}
	
	/****
	 * 重写 列表数据加载后台业务处理<p>
	 * @return ModelAndView<p>
	 */
	@RequestMapping(value = "/list", method = { RequestMethod.GET , RequestMethod.POST })
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
//		SQLCondition condition = new SQLCondition();
//		condition.put(new ConditionParameter("parent.id" , null , CompareTypeEnum.COMPARE_IS_NULL));
//		List<E> list = this.getTreeDataService().list(condition);
		
		 //列表查询 条件对象
		SQLCondition condition = this.getSeachSQLCondition(request);
		List<E> list = this.getTreeDataService().getMultipleLevelChildrenNodeList(null, "parent", this.getOrderbyFieldName());
		
		return new ModelAndView(WebConstants.XML_VIEW_NAME, "model", list);
	}
	
	
	
	/****
	 * 跳转表单新增下级界面<p>
	 * @return ModelAndView<p>
	 */
	//@Token(save=true)
	@RequestMapping(value = "/addChildren/form", method = RequestMethod.GET)
	public ModelAndView addChildrenNew(HttpServletRequest request , HttpServletResponse response) {
		//logger.info("addNew=");
		ModelAndView mav = new ModelAndView(this.getAddChildrenFormURL());
		mav.addObject("pageStatus", PageStatusEnum.PAGE_ADDNEW.getValue());
		mav.addObject("data", this.createNewEntity());
		this.addParamToModelAndView(request, mav, PageStatusEnum.PAGE_ADDNEW.getValue());//添加 自定义参数到 界面展示
		
		return mav;
	}
	
	
}
