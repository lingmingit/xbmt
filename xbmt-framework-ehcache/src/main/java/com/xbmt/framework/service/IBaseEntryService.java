/**
 * 
 */
package com.xbmt.framework.service;

import com.xbmt.framework.entity.AbstractBaseDataEntry;
import com.xbmt.framework.service.base.ICoreBaseService;

/**
 * 分录  业务操作service 基础接口类 <p>
 * @author LingMin 
 * @date 2015-12-02<br>
 * @version 1.0<br>
 */
public interface IBaseEntryService<E extends AbstractBaseDataEntry, ID extends java.io.Serializable> extends ICoreBaseService<E, ID> {

	
	
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
