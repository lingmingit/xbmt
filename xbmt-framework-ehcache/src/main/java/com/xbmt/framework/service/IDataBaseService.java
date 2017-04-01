/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.framework.service;

import javax.faces.model.SelectItem;

import com.xbmt.framework.dao.common.SQLCondition;
import com.xbmt.framework.entity.AbstractDataBase;
import com.xbmt.framework.entity.sys.SysUser;
import com.xbmt.framework.service.base.ICoreBaseService;

/** 
 * 基础资料 业务操作 基类接口<p>
 * @author LingMin 
 * @date 2015-9-2<br>
 * @version 1.0<br>
 */
public interface IDataBaseService<E extends AbstractDataBase, ID extends java.io.Serializable> extends ICoreBaseService<E, ID> {

	 /****
	  * 检查实体中 numbers字段是否重复 存在<p>
	  * @param numbers 编码
	  * @param id 实体id
	  * @return E 返回实体对象，如果为null 不存在，否则 存在<p>
	  */
	public E checkEntityByNumbers(String numbers , String id);
	
	/**
	 * 【启用】按钮数据库层监听函数<p>
	 * @param entity 实体对象<br>
	 * @param oprter 操作者<br>
	 */
	public void enable(E entity, SysUser oprter);
	
	/**
	 * 【禁用】按钮数据库层监听函数<p>
	 * @param entity  实体对象<br>
	 * @param oprter 操作者<br>
	 */
	public void disable(E entity, SysUser oprter);
	
	/**
	 * 根据数据记录状态获取满足条件的数据记录集合<p>
	 * @param status 数据记录状态<br>
	 * @return 数据记录集合<br>
	 */
	public java.util.List<E> getListForStatus(Boolean status);
	
	/**
	 * 根据数据状态获取满足条件的下拉列表信息集合，指定显示值字段<p>
	 * @param condition 查询条件对象<br>
	 * @param disField 显示字段<br>
	 * @return 下拉列表信息集合<br>
	 */
	public SelectItem[] getSelectItem(SQLCondition condition, String disField);
	
	/**
	 * 根据数据状态获取满足条件的下拉列表信息集合，指定显示值字段<p>
	 * @param status 数据状态<br>
	 * @param disField 显示字段<br>
	 * @return 下拉列表信息集合<br>
	 */
	public SelectItem[] getSelectItemForStatus(Boolean status, String disField);
	
	/**
	 * 根据数据记录状态获取满足条件的数据记录集合，并将其组装为HashMap对象返回<p>
	 * @param status 数据记录状态<br>
	 * @param disField 下拉显示字段名<br>
	 * @return 数据记录集合<br>
	 */
	public java.util.Map<String, String> getHashMapForStatus(Boolean status, String disField);
}
