/**
 * 
 */
package com.xbmt.framework.dao;

import com.xbmt.framework.dao.base.ICoreBaseDao;
import com.xbmt.framework.entity.AbstractBaseDataEntry;

/**
 * 分录  数据库操作dao 基础接口类 <p>
 * @author LingMin 
 * @date 2015-12-02<br>
 * @version 1.0<br>
 */
public interface IBaseEntryDao<E extends AbstractBaseDataEntry, ID extends java.io.Serializable> extends ICoreBaseDao<E, ID> {

	
	/**
	 * 根据主表关键字与主表关联字段名批量删除分录数据<p>
	 * @param mappedField 主表关联字段名<br>
	 * @param parentKey 主表关键字<br>
	 */
	public void deleteEntryList(String mappedField , ID parentKey);
	
	/**
	 * 根据主表关联字段名与主表关键字获取满足条件的数据关键字<p>
	 * @param parentField 主表关联字段名<br>
	 * @param parentKey 主表信息关键字<br>
	 */
	public String[] getEntryKeys(String parentField, String parentKey);
	
	/**
	 * 根据主表关键字与主表关联字段名获取分录信息<p>
	 * @param mappedField 主表关联字段名<br>
	 * @param parentKey 主表关键字<br>
	 * @return 分录信息<br>
	 */
	public java.util.List<E> getEntryList(String mappedField , ID parentKey);
}
