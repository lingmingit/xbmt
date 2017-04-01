/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.framework.dao.impl;

import com.xbmt.common.StringUtils;
import com.xbmt.framework.dao.ITreeDataDao;
import com.xbmt.framework.dao.common.ConditionParameter;
import com.xbmt.framework.dao.common.OrderParameter;
import com.xbmt.framework.dao.common.SQLCondition;
import com.xbmt.framework.dao.enums.CompareTypeEnum;
import com.xbmt.framework.dao.enums.OrderTypeEnum;
import com.xbmt.framework.entity.AbstractTreeData;

/** 
 * 树形基础资料基类数据操作接口实现类<p>
 * @author LingMin 
 * @date 2015-10-29<br>
 * @version 1.0<br>
 */
public abstract class TreeDataDaoImpl<E extends AbstractTreeData, ID extends java.io.Serializable , DAOImpl extends ITreeDataDao<E, ID>> extends DataBaseDaoImpl<E, ID, DAOImpl> implements ITreeDataDao<E, ID> {

	/**
	 * 根据结点关键字查询该结点下叶子结点信息<p>
	 */
	public java.util.List<E> getChildrenNodeList(final ID parentKey, final String mappedField) {
		return this.getChildrenNodeList(parentKey, mappedField, "numbers");
	}
	
	/**
	 * 根据结点关键字查询该结点下叶子结点信息<p>
	 */
	public java.util.List<E> getChildrenNodeList(final ID parentKey,  final String mappedField , final String orderByFieldName) {
		// 合法性验证
		SQLCondition condition = new SQLCondition();
		// 组装查询条件
		if (StringUtils.isEmpty((String) parentKey)) {
			condition.put(new ConditionParameter((StringUtils
					.isNotEmpty(mappedField) ? mappedField : "parent")
					.concat(".id"), null, CompareTypeEnum.COMPARE_IS_NULL));
		} else {
			condition.put(new ConditionParameter((StringUtils
					.isNotEmpty(mappedField) ? mappedField : "parent")
					.concat(".id"), parentKey, CompareTypeEnum.COMPARE_EQUEAL));
		}
		// 设置排序字段
		condition.put(new OrderParameter(orderByFieldName, OrderTypeEnum.ORDER_ASC));
		// 执行数据库查询
		return list(condition);
	}
	
}
