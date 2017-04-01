/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.framework.dao.impl;


import com.xbmt.common.CommonUtils;
import com.xbmt.framework.dao.IDataBaseDao;
import com.xbmt.framework.dao.base.impl.CoreBaseDaoImpl;
import com.xbmt.framework.dao.common.ConditionParameter;
import com.xbmt.framework.dao.common.OrderParameter;
import com.xbmt.framework.dao.common.SQLCondition;
import com.xbmt.framework.dao.enums.CompareTypeEnum;
import com.xbmt.framework.dao.enums.OrderTypeEnum;
import com.xbmt.framework.entity.AbstractDataBase;
import com.xbmt.framework.entity.sys.SysUser;

/** 
 * 基础资料信息数据库层操作接口实现类<p>
 * @author LingMin 
 * @date 2015-9-2<br>
 * @version 1.0<br>
 * @param <E> 实体对象信息类<br>
 * @param <ID> 实体对象关键字<br>
 */
public abstract class DataBaseDaoImpl<E extends AbstractDataBase, ID extends java.io.Serializable, DAOImpl extends IDataBaseDao<E, ID>> 
			extends CoreBaseDaoImpl<E, ID, DAOImpl> implements IDataBaseDao<E, ID> {

	
	
	
	 /****
	  * 检查实体中 numbers字段是否重复 存在<p>
	  * @param numbers 编码
	  * @param id 实体id
	  * @return E 返回实体对象，如果为null 不存在，否则 存在<p>
	  */
	public E checkEntityByNumbers(String numbers , String id) {
		return this.checkEntityByField("numbers", numbers , id);
	}
	
	
	
	
	
	/**
	 * 【启用】按钮数据库层监听函数<p>
	 */
	public void enable(E entity, SysUser oprter) {
		//entity.setModifier(oprter);
		entity.setIsEnable(Boolean.TRUE);
		//entity.setModifyTime(new java.util.Date());
		this.update(entity);
	}
	
	/**
	 * 【禁用】按钮数据库层监听函数<p>
	 */
	public void disable(E entity, SysUser oprter) {
		//entity.setModifier(oprter);
		entity.setIsEnable(Boolean.FALSE);
		//entity.setModifyTime(new java.util.Date());
		this.update(entity);
	}
	
	/**
	 * 根据数据记录状态获取满足条件的数据记录集合<p>
	 */
	public java.util.List<E> getListForStatus(Boolean status) {
		SQLCondition condition = new SQLCondition();
		// 设置排序条件
		condition.put(new OrderParameter("numbers", OrderTypeEnum.ORDER_ASC));
		// 组装查询条件
		if (CommonUtils.isNotEmptyObject(status)) {
			condition.put(new ConditionParameter("isEnable", CommonUtils
					.getStringFromBoolean(status), CompareTypeEnum.COMPARE_EQUEAL));
		}
		return list(condition);
	}
}
