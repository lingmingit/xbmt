/**
 * 
 */
package com.xbmt.framework.dao.impl;

import com.xbmt.common.CommonUtils;
import com.xbmt.common.StringUtils;
import com.xbmt.framework.dao.IBaseEntryDao;
import com.xbmt.framework.dao.base.impl.CoreBaseDaoImpl;
import com.xbmt.framework.dao.common.ConditionParameter;
import com.xbmt.framework.dao.common.OrderParameter;
import com.xbmt.framework.dao.common.SQLCondition;
import com.xbmt.framework.dao.enums.CompareTypeEnum;
import com.xbmt.framework.dao.enums.OrderTypeEnum;
import com.xbmt.framework.entity.AbstractBaseDataEntry;
import com.xbmt.framework.entity.base.Core;

/**
 * 分录  数据库操作dao 基础接口实现类 <p>
 * @author LingMin 
 * @date 2015-12-02<br>
 * @version 1.0<br>
 */
public class BaseEntryDaoImpl<E extends AbstractBaseDataEntry, ID extends java.io.Serializable , DAOImpl extends IBaseEntryDao<E, ID>> 
						extends CoreBaseDaoImpl<E, ID, DAOImpl> implements IBaseEntryDao<E, ID> {

	
	/**
	 * 根据主表关联字段名与主表关键字获取满足条件的数据关键字<p>
	 */
	public String[] getEntryKeys(String parentField, String parentKey) {
		String[] rtnA = null;
		// 合法性判断
		if (StringUtils.isNotEmpty(parentKey) && StringUtils.isNotEmpty(parentField)) {
			// 执行数据库查询
			java.util.List<?> result = this.getEntryList(parentField , (ID)parentKey);
			// 当查询结果集不为空时，遍历查询结果集
			if (CommonUtils.isNotEmptyList(result)) {
				java.util.List<String> rtnList = new java.util.ArrayList<String>();
				// 遍历查询结果集
				java.util.Iterator<?> iterator = result.iterator();
				while(iterator.hasNext()) {
					Core record = (Core) iterator.next();
					rtnList.add(record.getId());
				}
				rtnA = rtnList.toArray(new String[0]);
			}
		}
		return rtnA;
	}

	/**
	 * 根据主表关键字与主表关联字段名批量删除分录数据<p>
	 * @param mappedField 主表关联字段名<br>
	 * @param parentKey 主表关键字<br>
	 */
	public void deleteEntryList( String mappedField , ID parentKey) {
		// 当主表关键字不为空时，循环删除分录数据
		if (CommonUtils.isNotEmptyObject(parentKey)) {
			java.util.List<E> entryList = getEntryList(mappedField , parentKey);
			if (CommonUtils.isNotEmptyList(entryList)) {
				for (E entry : entryList) {
					delete(entry);
				}
			}
		}
	}
	
	/**
	 * 根据主表关键字与主表关联字段名获取分录信息<p>
	 */
	public java.util.List<E> getEntryList(String mappedField , ID parentKey) {
		// 声明返回值
		java.util.List<E> rtnList = null;
		// 当主表关键字不为空时,查询主表关联分录
		if (CommonUtils.isNotEmptyObject(parentKey)) {
			// 组装查询条件
			SQLCondition condition = new SQLCondition();
			condition.put(new ConditionParameter((StringUtils
					.isNotEmpty(mappedField) ? mappedField : "parent")
					.concat(".id"), parentKey, CompareTypeEnum.COMPARE_EQUEAL));
			condition.put(new OrderParameter("seq", OrderTypeEnum.ORDER_ASC));
			// 执行数据库查询
			rtnList = list(condition);
		}
		return rtnList;
	}
}
