package com.xbmt.framework.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xbmt.common.CommonUtils;
import com.xbmt.common.StringUtils;
import com.xbmt.common.exception.ExceptionUtils;
import com.xbmt.framework.entity.AbstractBaseDataEntry;
import com.xbmt.framework.entity.AbstractDataBase;
import com.xbmt.framework.entity.base.Core;
import com.xbmt.framework.service.IDataBaseService;
import com.xbmt.framework.web.constants.WebConstants;
import com.xbmt.framework.web.vo.JSONResultVo;



/** 
 * 基础资料带分录  后台管理 业务处理  Controller 抽象基类<p>
 * @author LingMin 
 * @date 2015-10-15<br>
 * @version 1.0<br>
 */
public abstract class EntryDataBaseController<E extends AbstractDataBase, ID extends java.io.Serializable , Entry extends AbstractBaseDataEntry , EntryID extends java.io.Serializable> extends BaseEntryListController<E, ID, Entry, EntryID> {

	
	/***
	 * 基础资料业务操作接口类<p>
	 * @return IDataBaseService <p>
	 */
	protected IDataBaseService<E , ID> getDataBaseService(){
		return (IDataBaseService<E , ID>)this.getCoreBaseService();
	}
	
	
	/****
	 * 实体编辑修改时， 指定哪些字段 是不需要进行更新的<p>
	 * @return <p>
	 * String[]
	 */
	protected String[] getNotUpdateFieldNameArray(){
		//String[] tempArray = super.getNotUpdateFieldNameArray();
		return new String[]{"id" , "isEnable",  "isSystem"};
	}
	
	/***
	 * 是否检查编码重复
	 * @return true验证， false不验证
	 */
	protected boolean isCheckNumberExists(){
		return true;
	}
	
	
	/***
	 * 验证是否允许保存操作<p>
	 * @param entity 保存操作实体
	 * @param request 请求对象
	 * @param jsonResultVo 错误提示消息对象
	 * @return boolean true 允许，false 不允许<p>
	 */
	protected boolean validateSave(E entity , HttpServletRequest request , JSONResultVo jsonResultVo){
		AbstractDataBase dbEntity = (AbstractDataBase)entity;
		//验证编码重复
		if(this.isCheckNumberExists()){
			/**检查基础资料编码 是否存在***/
			AbstractDataBase tempEntity = this.getCoreBaseService().checkEntityByField("numbers", entity.getNumbers(), dbEntity.getId());
			if(CommonUtils.isNotEmptyObject(tempEntity) && StringUtils.isNotEmpty(tempEntity.getId())){
				jsonResultVo.setResultMsg("编码："+entity.getNumbers()+"已经存在！！");
				return false;
			}
		}
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
		AbstractDataBase dbEntity = (AbstractDataBase)entity;
		if(CommonUtils.isNotEmptyObject(dbEntity.getIsSystem()) && dbEntity.getIsSystem()){
			jsonResultVo.setResultMsg(dbEntity.getNumbers()+"为系统初始化，不能删除!!");
			return false;
		}
		if(dbEntity.getIsEnable()){
			jsonResultVo.setResultMsg(dbEntity.getNumbers()+"为启用状态，不能删除!!");
			return false;
		}
		return true;
	}
	
	
	/***
	 * 验证是否允许启用操作<p>
	 * @param entity 操作实体
	 * @param request 请求对象
	 * @param jsonResultVo 错误提示消息对象
	 * @return boolean true 允许，false 不允许<p>
	 */
	protected boolean validateEnable(E entity , HttpServletRequest request , JSONResultVo jsonResultVo){
		AbstractDataBase dbEntity = (AbstractDataBase)entity;
		if(CommonUtils.isNotEmptyObject(dbEntity.getIsEnable()) &&  dbEntity.getIsEnable()){
			jsonResultVo.setResultMsg(dbEntity.getNumbers()+"为启用状态，不能启用!!");
			return false;
		}
		return true;
	}
	
	/***
	 * 验证是否允许禁用操作<p>
	 * @param entity 操作实体
	 * @param request 请求对象
	 * @param jsonResultVo 错误提示消息对象
	 * @return boolean true 允许，false 不允许<p>
	 */
	protected boolean validateUnEnable(E entity , HttpServletRequest request , JSONResultVo jsonResultVo){
		AbstractDataBase dbEntity = (AbstractDataBase)entity;
		if(CommonUtils.isNotEmptyObject(dbEntity.getIsEnable()) && !dbEntity.getIsEnable()){
			jsonResultVo.setResultMsg(dbEntity.getNumbers()+"为禁用状态，不能禁用!!");
			return false;
		}
		return true;
	}
	
	/***
	 * 执行启用方法<p>
	 * @param request请求对象
	 * @param entity 操作实体对象<p>
	 * void
	 */
	protected void procesEnableRecord(HttpServletRequest request , E entity){
		this.getDataBaseService().enable(entity , this.getCurrentSysUser());
	}
	
	/***
	 * 执行禁用方法<p>
	 * @param request请求对象
	 * @param entity 操作实体对象<p>
	 * void
	 */
	protected void processUnEnableRecord(HttpServletRequest request , E entity){
		this.getDataBaseService().disable(entity , this.getCurrentSysUser());
	}
	
	/****
	 * 批量实体启用处理操作<p>
	 * @return JSONResultVo<p>
	 */
	@ResponseBody
	@RequestMapping(value = "/enables/{ids}", method = {RequestMethod.GET , RequestMethod.POST})
	public JSONResultVo enables(@PathVariable String[] ids , HttpServletRequest request , HttpServletResponse response) {
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
				boolean flag = this.validateEnable(entity, request , tempJRVo);
				if(flag){
					//this.getDataBaseService().enable(entity, this.getCurrentSysUser());//启用
					this.procesEnableRecord(request, entity);//执行启用操作
					successCount++;
				}else{
					errorMsgs.append(tempJRVo.getResultMsg());
				}
			}
			int failureCount = ids.length - successCount;
			StringBuffer msgs = new StringBuffer();
			msgs.append("您共提交").append(ids.length).append("条数据，其中成功启用").append(successCount).append("条，");
			msgs.append("失败").append(failureCount).append("条。");
			if(failureCount > 0){
				msgs.append("失败详细信息如下：<br/>").append(errorMsgs);
			}
			
			jsonResultVo.setResultFlag(true);
			jsonResultVo.setResultMsg(WebConstants.SuccessStatusCode);
			jsonResultVo.setResultMsg(msgs.toString());
		}catch(Exception e){
			e.printStackTrace();
			jsonResultVo.setResultFlag(false);
			jsonResultVo.setResultMsg(WebConstants.ExceptionStatusCode);
			// getDetailExceptionMessage   getExceptionStack
			String exeMsg = ExceptionUtils.getDetailExceptionMessage(e);
			jsonResultVo.setResultMsg("处理异常,"+exeMsg);
			//jsonResultVo.setExceptionMsg(e.getMessage());
			jsonResultVo.setExceptionMsg(exeMsg);
			jsonResultVo.setExceptionName(e.getClass().getName());
		}
		return jsonResultVo;
	}
	
	/****
	 * 实体启用处理操作<p>
	 * @return JSONResultVo<p>
	 */
	@ResponseBody
	@RequestMapping(value = "/enable/{id}", method = {RequestMethod.GET , RequestMethod.POST})
	public JSONResultVo enable(@PathVariable String id , HttpServletRequest request , HttpServletResponse response) {
		JSONResultVo jsonResultVo = new JSONResultVo();
		if(StringUtils.isEmpty(id)){
			jsonResultVo.setResultFlag(false);
			jsonResultVo.setResultMsg(WebConstants.FailureStatusCode);
			jsonResultVo.setResultMsg("请求参数为空!!");
			return jsonResultVo;
		}
		try{
			E entity = (E)this.getDataBaseService().get((ID)id);
			//验证是否允许删除
			if(!this.validateEnable(entity, request, jsonResultVo)){
				if(CommonUtils.isEmptyObject(jsonResultVo.getResultFlag())){
					jsonResultVo.setResultFlag(false);
				}
				if(StringUtils.isEmpty(jsonResultVo.getResultCode())){
					jsonResultVo.setResultCode(WebConstants.FailureStatusCode);
				}
				if(StringUtils.isEmpty(jsonResultVo.getResultMsg())){
					jsonResultVo.setResultMsg("启用验证失败！！");
				}
				return jsonResultVo;
			}
			//this.getDataBaseService().enable(entity, this.getCurrentSysUser());//启用
			this.procesEnableRecord(request, entity);//执行启用操作
			jsonResultVo.setResultFlag(true);
			jsonResultVo.setResultMsg(WebConstants.SuccessStatusCode);
			jsonResultVo.setResultMsg("启用成功");
			jsonResultVo.setKeyParam(id);
		}catch(Exception e){
			e.printStackTrace();
			jsonResultVo.setResultFlag(false);
			jsonResultVo.setResultMsg(WebConstants.ExceptionStatusCode);
			// getDetailExceptionMessage   getExceptionStack
			String exeMsg = ExceptionUtils.getDetailExceptionMessage(e);
			jsonResultVo.setResultMsg("处理异常,"+exeMsg);
			//jsonResultVo.setExceptionMsg(e.getMessage());
			jsonResultVo.setExceptionMsg(exeMsg);
			jsonResultVo.setExceptionName(e.getClass().getName());
		}
		return jsonResultVo;
	}
	
	
	
	/****
	 * 批量实体禁处理操作<p>
	 * @return JSONResultVo<p>
	 */
	@ResponseBody
	@RequestMapping(value = "/unEnables/{ids}", method = {RequestMethod.GET , RequestMethod.POST})
	public JSONResultVo unEnables(@PathVariable String[] ids , HttpServletRequest request , HttpServletResponse response) {
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
				boolean flag = this.validateUnEnable(entity, request , tempJRVo);
				if(flag){
					//this.getDataBaseService().disable(entity, this.getCurrentSysUser());//禁用
					this.processUnEnableRecord(request, entity);//执行禁用操作
					successCount++;
				}else{
					errorMsgs.append(tempJRVo.getResultMsg());
				}
			}
			int failureCount = ids.length - successCount;
			StringBuffer msgs = new StringBuffer();
			msgs.append("您共提交").append(ids.length).append("条数据，其中成功禁用").append(successCount).append("条，");
			msgs.append("失败").append(failureCount).append("条。");
			if(failureCount > 0){
				msgs.append("失败详细信息如下：<br/>").append(errorMsgs);
			}
			jsonResultVo.setResultFlag(true);
			jsonResultVo.setResultMsg(WebConstants.SuccessStatusCode);
			jsonResultVo.setResultMsg(msgs.toString());
		}catch(Exception e){
			e.printStackTrace();
			jsonResultVo.setResultFlag(false);
			jsonResultVo.setResultMsg(WebConstants.ExceptionStatusCode);
			// getDetailExceptionMessage   getExceptionStack
			String exeMsg = ExceptionUtils.getDetailExceptionMessage(e);
			jsonResultVo.setResultMsg("处理异常,"+exeMsg);
			//jsonResultVo.setExceptionMsg(e.getMessage());
			jsonResultVo.setExceptionMsg(exeMsg);
			jsonResultVo.setExceptionName(e.getClass().getName());
		}
		return jsonResultVo;
	}
	
	/****
	 * 实体禁用处理操作<p>
	 * @return JSONResultVo<p>
	 */
	@ResponseBody
	@RequestMapping(value = "/unEnable/{id}", method = {RequestMethod.GET , RequestMethod.POST})
	public JSONResultVo unEnable(@PathVariable String id , HttpServletRequest request , HttpServletResponse response) {
		JSONResultVo jsonResultVo = new JSONResultVo();
		if(StringUtils.isEmpty(id)){
			jsonResultVo.setResultFlag(false);
			jsonResultVo.setResultMsg(WebConstants.FailureStatusCode);
			jsonResultVo.setResultMsg("请求参数为空!!");
			return jsonResultVo;
		}
		try{
			E entity = (E)this.getDataBaseService().get((ID)id);
			//验证是否允许删除
			if(!this.validateUnEnable(entity, request, jsonResultVo)){
				if(CommonUtils.isEmptyObject(jsonResultVo.getResultFlag())){
					jsonResultVo.setResultFlag(false);
				}
				if(StringUtils.isEmpty(jsonResultVo.getResultCode())){
					jsonResultVo.setResultCode(WebConstants.FailureStatusCode);
				}
				if(StringUtils.isEmpty(jsonResultVo.getResultMsg())){
					jsonResultVo.setResultMsg("禁用验证失败！！");
				}
				return jsonResultVo;
			}
			//this.getDataBaseService().disable(entity, this.getCurrentSysUser());//禁用
			this.processUnEnableRecord(request, entity);//执行禁用操作
			
			jsonResultVo.setResultFlag(true);
			jsonResultVo.setResultMsg(WebConstants.SuccessStatusCode);
			jsonResultVo.setResultMsg("禁用成功");
			jsonResultVo.setKeyParam(id);
		}catch(Exception e){
			e.printStackTrace();
			jsonResultVo.setResultFlag(false);
			jsonResultVo.setResultMsg(WebConstants.ExceptionStatusCode);
			// getDetailExceptionMessage   getExceptionStack
			String exeMsg = ExceptionUtils.getDetailExceptionMessage(e);
			jsonResultVo.setResultMsg("处理异常,"+exeMsg);
			//jsonResultVo.setExceptionMsg(e.getMessage());
			jsonResultVo.setExceptionMsg(exeMsg);
			jsonResultVo.setExceptionName(e.getClass().getName());
		}
		return jsonResultVo;
	}
}
