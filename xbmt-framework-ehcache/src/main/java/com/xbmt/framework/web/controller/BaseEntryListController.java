package com.xbmt.framework.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xbmt.common.CommonUtils;
import com.xbmt.common.ReflectionUtils;
import com.xbmt.common.StringUtils;
import com.xbmt.framework.dao.common.ConditionParameter;
import com.xbmt.framework.dao.common.SQLCondition;
import com.xbmt.framework.dao.enums.CompareTypeEnum;
import com.xbmt.framework.entity.AbstractBaseDataEntry;
import com.xbmt.framework.entity.base.Core;
import com.xbmt.framework.service.IBaseEntryService;
import com.xbmt.framework.web.constants.WebConstants;
import com.xbmt.framework.web.omui.model.GridDataModel;
import com.xbmt.framework.web.utils.EntryDataUtils;
import com.xbmt.framework.web.utils.PageUtils;


/** 
 * list列表界面 (主实体在新增、编辑时带有分录list)后台处理Controller基类<p>
 * 【分录新增、分录编辑、分录查看、分录删除】
 * @author LingMin 
 * @date 2015-10-27<br>
 * @version 1.0<br>
 */
public abstract class BaseEntryListController<E extends Core, ID extends java.io.Serializable , Entry extends AbstractBaseDataEntry , EntryID extends java.io.Serializable> extends BaseListController<E, ID> {

	/***分录关联parent实体的主键字段名称，默认为id****/
	protected String entryParentIdFieldName = "id";
	/**分录新增行 数据请求参数名称***/
	protected String AddedEntryRowsName = "addedEntryRows";
	/**分录修改行 数据请求参数名称***/
	protected String UpdatedEntryRowsName = "updatedEntryRows";
	/**分录删除行 数据请求参数名称***/
	protected String DeletedEntryRowsName = "deletedEntryRows";
	
	/*****当前分录实体class类型****/
	protected Class<Entry> entryClass = null;

	
	
	/**
	 * 获取泛型对应实体的Class对象<p>
	 * @return 泛型对应实体的Class对象<br>
	 */
	public Class<Entry> getEntryClass() {
		if (entryClass == null) {
			entryClass = (Class<Entry>)this.getControllerTypeClass(2);
		}
		return entryClass;
	}
	/****
	 * 指定 列表查询 条件对象<p>
	 * @param request
	 * @return <p>
	 * SQLCondition
	 */
	protected SQLCondition getEntryListSQLCondition(HttpServletRequest request){
		return null;
	}
	

	/****
	 * 指定 分录实体关联父对象字段名称，默认parent.id<p>
	 * @return 返回字段名称
	 */
	protected String getEntryParentFieldName(){
		return "parent";
	}
	
	/***
	 * 指定 主实体 分录list 字段名称：默认entryList<p>
	 * @return 返回字段名称
	 */
	protected String getEntryListFieldName(){
		return "entryList";
	}
	
	
	/****
	 * 指定 分录 实体引用 对象字段名称 数组
	 * @return 返回数组
	 */
	protected abstract String[] getEntryRefFieldNameArray();
    
	
	/***
	 * 分录业务操作接口类<p>
	 * @return  <p>
	 */
	protected abstract IBaseEntryService<Entry , EntryID> getBaseEntryService();
	
	/***
	 * 将分录实体 转换map对象，去除不需要的字段
	 * 如：将关联字段转换为id，action:{id:'' , name:''} 转换为 action:'id', actionName:'name'
	 * @param entity 分录实体对象
	 * @return 返回map对象
	 */
	protected abstract Map<String,Object> getEntryToMap(Entry entity);
	
	
	/****
	 * 分录实体编辑修改时， 指定哪些字段 是不需要进行更新的<p>
	 * @return <p>
	 * String[]
	 */
	protected String[] getEntryNotUpdateFieldNameArray(){
		return new String[]{"id" , "parent"};
	}
	
	/****
	 * 分录实体编辑修改时， 指定哪些字段 值为null，也要进行更新<p>
	 * @return String[]<p>
	 */
	protected String[] getEntryNullSetFieldNameArray(){
		return null;
	}
	
	
	
	/****
	 * 分录列表数据加载后台业务处理<p>
	 * @return ModelAndView<p>
	 */
	@RequestMapping(value = "/entryList/{parentId}", method = {RequestMethod.GET , RequestMethod.POST})
	public ModelAndView entryList(@PathVariable String parentId , HttpServletRequest request , HttpServletResponse response) {
		String pageStr = request.getParameter("page");
	    String limitStr = request.getParameter("rows");
	    logger.info("pageStr="+pageStr+" \t limitStr="+limitStr);
	    
	    int page = 1;
	    if(StringUtils.isNotEmpty(pageStr)){
	    	page = Integer.valueOf(pageStr);
	    }
	    int limit = 10;
	    if(StringUtils.isNotEmpty(limitStr)){
	    	limit = Integer.valueOf(limitStr);
	    }
	    int totalRowCount = 0;
	    List<Entry> list = null;
	    //
	    List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
	    if(StringUtils.isNotEmpty(parentId)){
	    	 //列表查询 条件对象
			SQLCondition condition = this.getEntryListSQLCondition(request);
			if(CommonUtils.isEmptyObject(condition)){
				condition = new SQLCondition();
				condition.put(new ConditionParameter(this.getEntryParentFieldName().concat(".").concat(entryParentIdFieldName) , parentId , CompareTypeEnum.COMPARE_EQUEAL));
			}
			// 查询总记录数
			totalRowCount = this.getBaseEntryService().count(condition, null);
			int start = PageUtils.getRowIndex(page, limit);//计算分页起始行
			//通过最基类接口调用查询数据
			list = this.getBaseEntryService().list(start, limit, condition);
			//通过分录基类接口调用查询数据
			//list = this.getBaseEntryService().getEntryList(this.getEntryParentFieldName(), parentId);
			for(Entry entity : list){
				mapList.add(this.getEntryToMap(entity));
			}
	    }
		GridDataModel<Map<String,Object>> model = new GridDataModel<Map<String,Object>>();
        model.setRows(mapList);
        model.setTotal(totalRowCount);
		return new ModelAndView(WebConstants.XML_VIEW_NAME, "model", model);
	}
	
	
	/***
	 * 处理分录操作方法<p>
	 * 设置实体分录信息
	 * @param request请求对象
	 * @param entity 操作实体对象<p>
	 * void
	 */
	protected void procesAddEntry(HttpServletRequest request , E entity){
		String addedEntryRows = request.getParameter(AddedEntryRowsName);
		//String deletedEntryRows = request.getParameter("deletedEntryRows");
		//String updatedEntryRows = request.getParameter("updatedEntryRows");
		//logger.info("addedEntryList="+addedEntryRows+" \t updatedEntryList="+updatedEntryRows+" \t deletedEntryList="+deletedEntryRows);
		logger.info("addedEntryList="+addedEntryRows);
		//将分录 新增行数据 转换list 并添加到当前主实体中
		this.addNewEntryListToEntry(addedEntryRows, entity);
	}
	
	
	/***
	 * 处理修改分录行操作方法<p>
	 * @param request请求对象
	 * @param entity 操作实体对象<p>
	 * void
	 */
	protected void procesUpdateEntry(HttpServletRequest request , E entity){
		String updatedEntryRows = request.getParameter(UpdatedEntryRowsName);
		logger.info("updatedEntryRows="+updatedEntryRows);
		List<Entry> list = EntryDataUtils.getJSONStrToEntityList(this.getEntryClass(), updatedEntryRows, this.getEntryRefFieldNameArray() , entity , this.getEntryParentFieldName());
		if(CommonUtils.isNotEmptyList(list)){
			for(Entry entryObj : list){
				EntryID entryId = (EntryID)entryObj.getId();
				Entry oldEntity = (Entry)this.getBaseEntryService().get(entryId);
				//更新数据实体对象所有属性 值
				ReflectionUtils.updateEntityObjectFields(oldEntity , entryObj , this.getEntryNullSetFieldNameArray() , this.getEntryNotUpdateFieldNameArray() );
				this.getBaseEntryService().update(oldEntity);
			}
		}
	}
	
	
	/***
	 * 处理删除分录行操作方法<p>
	 * @param request请求对象
	 * @param entity 操作实体对象<p>
	 * void
	 */
	protected void procesDeleteEntry(HttpServletRequest request , E entity){
		String deletedEntryRows = request.getParameter(DeletedEntryRowsName);
		logger.info("deletedEntryRows="+deletedEntryRows);
		List<Entry> list = EntryDataUtils.getJSONStrToEntityList(this.getEntryClass(), deletedEntryRows, this.getEntryRefFieldNameArray() , entity , this.getEntryParentFieldName());
		if(CommonUtils.isNotEmptyList(list)){
			for(Entry entry : list){
				EntryID entryId = (EntryID)entry.getId();
				logger.info("entryId="+entryId);
				if(CommonUtils.isNotEmptyObject(entryId) && StringUtils.isNotEmpty(entryId.toString())){
					this.getBaseEntryService().delete(entryId);
				}
			}
		}
	}
	
	
	
	
	/***
	 * 将分录 新增行数据 转换list 并添加到当前主实体中
	 * @param addedEntryRowsJSONStr 新增行json 字符串
	 * @param entity 主实体对象
	 */
	protected void addNewEntryListToEntry(String addedEntryRowsJSONStr , E entity){
		//将 前台提交分录json字符串转换 为list
		List<Entry> list = EntryDataUtils.getJSONStrToEntityList(this.getEntryClass(), addedEntryRowsJSONStr, this.getEntryRefFieldNameArray() , entity , this.getEntryParentFieldName());
		if(CommonUtils.isNotEmptyList(list)){
			//根据 属性名称 设置 set方法名称
			String methodName = "set".concat(StringUtils.firstCharToUpperCase(this.getEntryListFieldName()));
			//设置 主实体 分录list
			ReflectionUtils.invokeMethod(entity, methodName, new Object[]{list}, new Class[]{List.class});//List.class
		}
	}
	
}
