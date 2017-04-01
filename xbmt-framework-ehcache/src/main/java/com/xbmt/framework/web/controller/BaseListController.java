/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.framework.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xbmt.common.CommonUtils;
import com.xbmt.common.ReflectionUtils;
import com.xbmt.common.StringUtils;
import com.xbmt.common.exception.ExceptionUtils;
import com.xbmt.framework.dao.common.ConditionParameter;
import com.xbmt.framework.dao.common.SQLCondition;
import com.xbmt.framework.dao.enums.CompareTypeEnum;
import com.xbmt.framework.entity.AbstractBaseDataEntry;
import com.xbmt.framework.entity.base.Core;
import com.xbmt.framework.service.IBaseEntryService;
import com.xbmt.framework.web.constants.WebConstants;
import com.xbmt.framework.web.enums.PageStatusEnum;
import com.xbmt.framework.web.omui.model.GridDataModel;
import com.xbmt.framework.web.utils.PageUtils;
import com.xbmt.framework.web.vo.JSONResultVo;

/** 
 * list列表界面 后台处理Controller基类<p>
 * 【新增、编辑、查看、删除】
 * @author LingMin 
 * @date 2015-10-27<br>
 * @version 1.0<br>
 */
public abstract class BaseListController<E extends Core, ID extends java.io.Serializable> extends CoreListController<E, ID> {

	
	/*****
	 * 指定 新增表单url<p>
	 * @return String<p>
	 */
	protected abstract String getAddFormURL();
	
	/*****
	 * 指定 编辑表单url<p>
	 * @return String<p>
	 */
	protected abstract String getEditFormURL();
	
	/*****
	 * 指定 查看表单url<p>
	 * @return String<p>
	 */
	protected abstract String getViewFormURL();
	
	
	
	/***
	 * 验证是否允许保存操作<p>
	 * @param entity 保存操作实体
	 * @param request 请求对象
	 * @param jsonResultVo 错误提示消息对象
	 * @return boolean true 允许，false 不允许<p>
	 */
	protected boolean validateSave(E entity , HttpServletRequest request , JSONResultVo jsonResultVo){
		return true;
	}
	
	/***
	 * 验证是否允许删除操作<p>
	 * @param entity 删除操作实体
	 * @param request 请求对象
	 * @param jsonResultVo 错误提示消息对象
	 * @return boolean true 允许，false 不允许<p>
	 */
	protected boolean validateDelete(E entity , HttpServletRequest request , JSONResultVo jsonResultVo){
		return true;
	}
	
	
	/***
	 * 新增界面初始化 实体字段<p>
	 * @return <p>
	 * E
	 */
	protected E createNewEntity(){
		return null;
	}
	

	
	/****
	 * 实体编辑修改时， 指定哪些字段 是不需要进行更新的<p>
	 * @return <p>
	 * String[]
	 */
	protected String[] getNotUpdateFieldNameArray(){
		return new String[]{"id"};
	}
	
	/****
	 * 实体编辑修改时， 指定哪些字段 值为null，也要进行更新<p>
	 * @return String[]<p>
	 */
	protected String[] getNullSetFieldNameArray(){
		return null;
	}
	
	
	/***
	 * 添加 自定义参数到 界面展示<p>
	 * @param request 请求对象
	 * @param mav 传递参数对象ModelAndView
	 * @param pageStatus 界面状态<p>
	 * void
	 */
	protected void addParamToModelAndView(HttpServletRequest request , ModelAndView mav , String pageStatus){
	}
	
	/***
	 * 处理新增分录行操作方法<p>
	 * @param request请求对象
	 * @param entity 操作实体对象<p>
	 * void
	 */
	protected void procesAddEntry(HttpServletRequest request , E entity){
	}
	
	/***
	 * 处理修改分录行操作方法<p>
	 * @param request请求对象
	 * @param entity 操作实体对象<p>
	 * void
	 */
	protected void procesUpdateEntry(HttpServletRequest request , E entity){
	}
	
	
	/***
	 * 处理删除分录行操作方法<p>
	 * @param request请求对象
	 * @param entity 操作实体对象<p>
	 * void
	 */
	protected void procesDeleteEntry(HttpServletRequest request , E entity){
	}
	
	/***
	 * 执行保存save方法 之前调用<p>
	 * @param request请求对象
	 * @param entity 操作实体对象<p>
	 * void
	 */
	protected void beforeSave(HttpServletRequest request , E entity){
	}
	
	
	/***
	 * 执行保存save方法 之后调用<p>
	 * @param request请求对象
	 * @param entity 操作实体对象<p>
	 * void
	 */
	protected void afterSave(HttpServletRequest request , E entity){
	}
	
	
	/***
	 * 执行修改update方法 之前调用<p>
	 * @param request请求对象
	 * @param entity 操作实体对象<p>
	 * void
	 */
	protected void beforeUpdate(HttpServletRequest request , E entity){
	}
	/***
	 * 执行修改update方法 之后调用<p>
	 * @param request请求对象
	 * @param entity 操作实体对象<p>
	 * void
	 */
	protected void afterUpdate(HttpServletRequest request , E entity){
	}
	
	
	
	
	
	
	/***
	 * 执行保存save方法<p>
	 * @param request请求对象
	 * @param entity 操作实体对象<p>
	 * void
	 */
	protected void procesSaveRecord(HttpServletRequest request , E entity){
		this.getCoreBaseService().save(entity);
	}
	
	/***
	 * 执行删除delete方法<p>
	 * @param request请求对象
	 * @param entity 操作实体对象<p>
	 * void
	 */
	protected void processDeleteRecord(HttpServletRequest request , E entity){
		this.getCoreBaseService().delete(entity);
	}
	
	
	/****
	 * 跳转表单新增界面<p>
	 * @return ModelAndView<p>
	 */
	//@Token(save=true)
	@RequestMapping(value = "/add/form", method = RequestMethod.GET)
	public ModelAndView addNew(HttpServletRequest request , HttpServletResponse response) {
		//logger.info("addNew=");
		ModelAndView mav = new ModelAndView(this.getAddFormURL());
		mav.addObject("pageStatus", PageStatusEnum.PAGE_ADDNEW.getValue());
		mav.addObject("data", this.createNewEntity());
		this.addParamToModelAndView(request, mav, PageStatusEnum.PAGE_ADDNEW.getValue());//添加 自定义参数到 界面展示
		
		return mav;
	}
	
	
	
	/****
	 * 跳转表单编辑界面<p>
	 * @return ModelAndView<p>
	 */
	//@Token(save=true)
	@RequestMapping(value = "/edit/{id}/form", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable String id , HttpServletRequest request , HttpServletResponse response) {
		ModelAndView mav = new ModelAndView(this.getEditFormURL());
		this.addParamToModelAndView(request, mav, PageStatusEnum.PAGE_EDIT.getValue());//添加 自定义参数到 界面展示
		if(StringUtils.isNotEmpty(id)){//新增
			mav.addObject("pageStatus", PageStatusEnum.PAGE_EDIT.getValue());
			E entity = (E)this.getCoreBaseService().get((ID)id);
			mav.addObject("data", entity);
			return mav;
		}else{
			request.setAttribute("message", "编辑失败，参数异常");
			return null;
		}
	}
	/****
	 * 跳转表单查看界面<p>
	 * @return ModelAndView<p>
	 */
	//@Token(save=true)
	@RequestMapping(value = "/view/{id}/form", method = RequestMethod.GET)
	public ModelAndView view(@PathVariable String id , HttpServletRequest request , HttpServletResponse response) {
		ModelAndView mav = new ModelAndView(this.getViewFormURL());
		this.addParamToModelAndView(request, mav, PageStatusEnum.PAGE_VIEW.getValue());//添加 自定义参数到 界面展示
		if(StringUtils.isNotEmpty(id)){//新增
			mav.addObject("pageStatus", PageStatusEnum.PAGE_VIEW.getValue());
			E entity = (E)this.getCoreBaseService().get((ID)id);
			mav.addObject("data", entity);
			return mav;
		}else{
			request.setAttribute("message", "查看失败，参数异常");
			return null;
		}
	}

	
	/****
	 * 表单实体保存<p>
	 * @return JSONResultVo<p>
	 */
	//@Token(remove=true)
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JSONResultVo save(@ModelAttribute E entity , HttpServletRequest request , HttpServletResponse response) {
		JSONResultVo jsonResultVo = new JSONResultVo();
		try{
			if(StringUtils.isEmpty(entity.getId())){//新增
				//验证是否允许保存操作
				if(!this.validateSave(entity, request, jsonResultVo)){
					if(CommonUtils.isEmptyObject(jsonResultVo.getResultFlag())){
						jsonResultVo.setResultFlag(false);
					}
					if(StringUtils.isEmpty(jsonResultVo.getResultCode())){
						jsonResultVo.setResultCode(WebConstants.FailureStatusCode);
					}
					if(StringUtils.isEmpty(jsonResultVo.getResultMsg())){
						jsonResultVo.setResultMsg("保存验证失败！！");
					}
					return jsonResultVo;
				}
				
				//执行save 方法之前调用
				this.beforeSave(request, entity);
				//this.getCoreBaseService().save(entity);
				this.procesAddEntry(request, entity);//处理新增分录行数据方法
				this.procesSaveRecord(request, entity);//执行删除方法
				//执行save 方法之后调用
				this.afterSave(request, entity);
				//logger.info("id="+id);
				jsonResultVo.setResultFlag(true);
				jsonResultVo.setResultCode(WebConstants.SuccessStatusCode);
				jsonResultVo.setResultMsg("保存成功");
				jsonResultVo.setKeyParam(entity.getId());
				jsonResultVo.setResultObj(entity);
			}else{
				
				//执行修改update 方法之前调用
				this.beforeUpdate(request, entity);
				this.procesAddEntry(request, entity);//处理新增分录行数据方法
				E oldEntity = (E)this.getCoreBaseService().get((ID)entity.getId());
				//更新数据实体对象所有属性 值
				ReflectionUtils.updateEntityObjectFields(oldEntity , entity , this.getNullSetFieldNameArray() , this.getNotUpdateFieldNameArray());
				
				this.getCoreBaseService().update(oldEntity);
				this.procesUpdateEntry(request, oldEntity);//处理修改分录行数据方法
				this.procesDeleteEntry(request, oldEntity);//处理删除分录行数据方法
				//执行修改update 方法之后调用
				this.afterUpdate(request, oldEntity);
				
				jsonResultVo.setResultFlag(true);
				jsonResultVo.setResultCode(WebConstants.SuccessStatusCode);
				jsonResultVo.setResultMsg("更新成功");
				jsonResultVo.setKeyParam(oldEntity.getId());
				//重写查询 json 转换实体， 否则会出现延迟加载问题
				oldEntity = (E)this.getCoreBaseService().get((ID)entity.getId());
				jsonResultVo.setResultObj(oldEntity);
			}
		}catch(Exception e){
			e.printStackTrace();
			jsonResultVo.setResultFlag(false);
			jsonResultVo.setResultMsg(WebConstants.ExceptionStatusCode);
			// getDetailExceptionMessage   getExceptionStack
			String exeMsg = ExceptionUtils.getDetailExceptionMessage(e , 1);
			jsonResultVo.setResultMsg("处理异常,"+exeMsg);
			//jsonResultVo.setExceptionMsg(e.getMessage());
			jsonResultVo.setExceptionMsg(exeMsg);
			jsonResultVo.setExceptionName(e.getClass().getName());
		}
		return jsonResultVo;
	}
	
	/****
	 * 批量实体删除<p>
	 * @return JSONResultVo<p>
	 */
	@ResponseBody
	@RequestMapping(value = "/deletes/{ids}", method = RequestMethod.GET)
	public JSONResultVo deletes(@PathVariable String[] ids , HttpServletRequest request , HttpServletResponse response) {
		JSONResultVo jsonResultVo = new JSONResultVo();
		if(CommonUtils.isEmptyObjectArray(ids)){
			jsonResultVo.setResultFlag(false);
			jsonResultVo.setResultMsg(WebConstants.FailureStatusCode);
			jsonResultVo.setResultMsg("请求参数为空!!");
			return jsonResultVo;
		}
		try{
			StringBuffer errorMsgs = new StringBuffer();
			int successCount = 0;//删除成功统计数量
			for(String id : ids){
				E entity = (E)this.getCoreBaseService().get((ID)id);
				JSONResultVo tempJRVo = new JSONResultVo();
				//验证是否允许删除
				boolean flag = this.validateDelete(entity, request , tempJRVo);
				if(flag){
					//this.getCoreBaseService().delete((ID)id);//删除
					this.processDeleteRecord(request, entity);//执行删除方法
					successCount++;
				}else{
					errorMsgs.append(tempJRVo.getResultMsg());
					errorMsgs.append("<br/>");
				}
			}
			int failureCount = ids.length - successCount;
			StringBuffer msgs = new StringBuffer();
			msgs.append("您共提交").append(ids.length).append("条数据，其中成功删除").append(successCount).append("条，");
			msgs.append("失败").append(failureCount).append("条。");
			if(failureCount > 0){
				msgs.append("失败详细信息如下：<br/>").append(errorMsgs);
			}
			jsonResultVo.setResultFlag(true);
			jsonResultVo.setResultMsg(WebConstants.SuccessStatusCode);
			jsonResultVo.setResultMsg(msgs.toString());
			jsonResultVo.setKeyParam("");
		}catch(Exception e){
			e.printStackTrace();
			jsonResultVo.setResultFlag(false);
			jsonResultVo.setResultMsg(WebConstants.ExceptionStatusCode);
			jsonResultVo.setResultMsg("处理异常");
			jsonResultVo.setExceptionMsg(e.getMessage());
			jsonResultVo.setExceptionName(e.getClass().getName());
		}
		return jsonResultVo;
	}
	
	
	/****
	 * 实体删除<p>
	 * @return String<p>
	 */
	@ResponseBody
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public JSONResultVo delete(@PathVariable String id , HttpServletRequest request , HttpServletResponse response) {
		JSONResultVo jsonResultVo = new JSONResultVo();
		if(StringUtils.isEmpty(id)){
			jsonResultVo.setResultFlag(false);
			jsonResultVo.setResultMsg(WebConstants.FailureStatusCode);
			jsonResultVo.setResultMsg("请求参数为空!!");
			return jsonResultVo;
		}
		try{
			E entity = (E)this.getCoreBaseService().get((ID)id);
			//验证是否允许删除
			if(!this.validateDelete(entity, request , jsonResultVo)){
				if(CommonUtils.isEmptyObject(jsonResultVo.getResultFlag())){
					jsonResultVo.setResultFlag(false);
				}
				if(StringUtils.isEmpty(jsonResultVo.getResultCode())){
					jsonResultVo.setResultCode(WebConstants.FailureStatusCode);
				}
				if(StringUtils.isEmpty(jsonResultVo.getResultMsg())){
					jsonResultVo.setResultMsg("删除验证失败！！");
				}
				return jsonResultVo;
			}
			//this.getCoreBaseService().delete((ID)id);//删除
			this.processDeleteRecord(request, entity);//执行删除方法
			
			jsonResultVo.setResultFlag(true);
			jsonResultVo.setResultMsg(WebConstants.SuccessStatusCode);
			jsonResultVo.setResultMsg("删除成功");
			jsonResultVo.setKeyParam(id);
		}catch(Exception e){
			e.printStackTrace();
			jsonResultVo.setResultFlag(false);
			jsonResultVo.setResultMsg(WebConstants.ExceptionStatusCode);
			jsonResultVo.setResultMsg("处理异常");
			jsonResultVo.setExceptionMsg(e.getMessage());
			jsonResultVo.setExceptionName(e.getClass().getName());
		}
		return jsonResultVo;
	}
	
	
	/***
	 * 检查实体字段 是否已经存在方法<p>
	 * @param fieldName 实体字段名称
	 * @param fieldValue 实体字段值
	 * @param id 实体id值
	 * @return JSONResultVo<p>
	 */
	@ResponseBody
	@RequestMapping(value = "/checkRepeat/{fieldName}/{fieldValue}/{id}", method = { RequestMethod.GET , RequestMethod.POST})
	public JSONResultVo checkUserAccount(@PathVariable String fieldName , @PathVariable String fieldValue , @PathVariable String id ,HttpServletRequest request , HttpServletResponse response){
		
		JSONResultVo jsonResultVo = new JSONResultVo();
		if(StringUtils.isEmpty(fieldName) || StringUtils.isEmpty(fieldValue)){
			jsonResultVo.setResultFlag(false);
			jsonResultVo.setResultMsg(WebConstants.FailureStatusCode);
			jsonResultVo.setResultMsg("请求参数为空!!");
			return jsonResultVo;
		}
		try{
			Core entity = this.getCoreBaseService().checkEntityByField(fieldName, fieldValue, id);
			if(CommonUtils.isEmptyObject(entity) || StringUtils.isEmpty(entity.getId())){
				jsonResultVo.setResultFlag(true);
				jsonResultVo.setResultMsg(WebConstants.SuccessStatusCode);
				jsonResultVo.setResultMsg("该字段值不存在!!");
			}else{
				jsonResultVo.setResultFlag(false);
				jsonResultVo.setResultMsg(WebConstants.FailureStatusCode);
				jsonResultVo.setResultMsg("该字段值已经存在!!");
			}
		}catch(Exception e){
			e.printStackTrace();
			jsonResultVo.setResultFlag(false);
			jsonResultVo.setResultMsg(WebConstants.ExceptionStatusCode);
			jsonResultVo.setResultMsg("处理异常");
			jsonResultVo.setExceptionMsg(e.getMessage());
			jsonResultVo.setExceptionName(e.getClass().getName());
		}
		return jsonResultVo;
	}
	
}
