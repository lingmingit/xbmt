/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.framework.dao;

import com.xbmt.framework.dao.base.ICoreBaseDao;
import com.xbmt.framework.entity.AbstractDataBase;
import com.xbmt.framework.entity.sys.SysUser;

/**
 * 基础资料 数据库操作dao 基础接口类 <p>
 * @author LingMin 
 * @date 2015-9-2<br>
 * @version 1.0<br>
 */
public interface IDataBaseDao<E extends AbstractDataBase, ID extends java.io.Serializable> extends ICoreBaseDao<E, ID> {

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
}
