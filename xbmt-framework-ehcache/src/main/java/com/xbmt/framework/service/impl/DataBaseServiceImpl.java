/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.framework.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import javax.faces.model.SelectItem;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xbmt.common.CommonUtils;
import com.xbmt.common.ReflectionUtils;
import com.xbmt.common.StringUtils;
import com.xbmt.framework.dao.IDataBaseDao;
import com.xbmt.framework.dao.common.SQLCondition;
import com.xbmt.framework.entity.AbstractDataBase;
import com.xbmt.framework.entity.sys.SysUser;
import com.xbmt.framework.service.IDataBaseService;
import com.xbmt.framework.service.base.impl.CoreBaseServiceImpl;

/** 
 * 基础资料数据实体业务操作 基类service<p>
 * @author LingMin 
 * @date 2015-9-2<br>
 * @version 1.0<br>
 */
public abstract class DataBaseServiceImpl<E extends AbstractDataBase, ID extends java.io.Serializable>  
		extends CoreBaseServiceImpl<E, ID> implements IDataBaseService<E, ID> {

	
	/** 基础资料信息数据库层操作对象 **/
	protected IDataBaseDao<E, ID> dataBaseDao;
	
	/**
	 * 构造函数:初始化数据库层操作对象<p>
	 * @param baseDataDao 数据库层操作对象<br>
	 */
	public DataBaseServiceImpl(IDataBaseDao<E, ID> dataBaseDao) {
		super(dataBaseDao);
		this.dataBaseDao = dataBaseDao;
	}
	
	 /****
	  * 检查实体中 numbers字段是否重复 存在<p>
	  * @param numbers 编码
	  * @param id 实体id
	  * @return E 返回实体对象，如果为null 不存在，否则 存在<p>
	  */
	public E checkEntityByNumbers(String numbers , String id){
		return this.dataBaseDao.checkEntityByNumbers(numbers, id);
	}
	
	
	/**
	 * 【启用】按钮数据库层监听函数<p>
	 * @param entity 实体对象<br>
	 * @param oprter 操作者<br>
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void enable(E entity, SysUser oprter){
		this.dataBaseDao.enable(entity, oprter);
	}
	
	/**
	 * 【禁用】按钮数据库层监听函数<p>
	 * @param entity  实体对象<br>
	 * @param oprter 操作者<br>
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void disable(E entity, SysUser oprter){
		this.dataBaseDao.disable(entity, oprter);
	}
	
	/**
	 * 根据数据记录状态获取满足条件的数据记录集合<p>
	 * @param status 数据记录状态<br>
	 * @return 数据记录集合<br>
	 */
	public java.util.List<E> getListForStatus(Boolean status){
		return this.dataBaseDao.getListForStatus(status);
	}
	
	
	/**
	 * 根据数据状态获取满足条件的下拉列表信息集合，指定显示值字段<p>
	 */
	public SelectItem[] getSelectItem(SQLCondition condition, String disField) {
		// 初始化返回值
		java.util.List<SelectItem> itemList = new ArrayList<SelectItem>(); 
		// 根据条件查询数据库
		if (StringUtils.isNotEmpty(disField)) {
			java.util.List<E> rsList = this.list(condition);
			if (CommonUtils.isNotEmptyList(rsList)) {
				itemList = new java.util.ArrayList<SelectItem>();
				for (E record : rsList) {
					String id = (String) ReflectionUtils.invokeMethod(record, "getId", null, null);
					String name = (String) ReflectionUtils.invokeMethod(
						record, "get".concat(StringUtils.firstCharToUpperCase(disField)), null, null
					);
					itemList.add(new SelectItem(id, StringUtils.getLegalString(name), null));
				}
			}
		}
		return itemList.toArray(new SelectItem[0]);
	}
	
	/**
	 * 根据数据状态获取满足条件的下拉列表信息集合，指定显示值字段<p>
	 */
	public SelectItem[] getSelectItemForStatus(Boolean status, String disField) {
		// 初始化返回值
		java.util.List<SelectItem> itemList = new ArrayList<SelectItem>(); 
		// 根据条件查询数据库
		if (StringUtils.isNotEmpty(disField)) {
			java.util.List<E> rsList = this.getListForStatus(status);
			if (CommonUtils.isNotEmptyList(rsList)) {
				itemList = new java.util.ArrayList<SelectItem>();
				for (E record : rsList) {
					String id = (String) ReflectionUtils.invokeMethod(record, "getId", null, null);
					String name = (String) ReflectionUtils.invokeMethod(
						record, "get".concat(StringUtils.firstCharToUpperCase(disField)), null, null
					);
					itemList.add(new SelectItem(id, StringUtils.getLegalString(name), null));
				}
			}
		}
		return CommonUtils.isNotEmptyList(itemList) ? itemList.toArray(new SelectItem[0]) : null;
	}

	/**
	 * 根据数据记录状态获取满足条件的数据记录集合，并将其组装为HashMap对象返回<p>
	 */
	public java.util.Map<String, String> getHashMapForStatus(Boolean status, String disField) {
		java.util.HashMap<String, String> rtnHM = new HashMap<String, String>();
		// 根据数据记录状态获取满足条件的数据记录集合
		java.util.List<E> resultList = this.getListForStatus(status);
		if (CommonUtils.isNotEmptyList(resultList)) {
			rtnHM = new java.util.HashMap<String, String>();
			for (E record : resultList) {
				String id = (String) ReflectionUtils.invokeMethod(record, "getId", null, null);
				String name = (String) ReflectionUtils.invokeMethod(
					record, "get".concat(StringUtils.firstCharToUpperCase(disField)), null, null
				);
				rtnHM.put(id, StringUtils.getLegalString(name));
			}
		}
		return rtnHM;
	}

}
