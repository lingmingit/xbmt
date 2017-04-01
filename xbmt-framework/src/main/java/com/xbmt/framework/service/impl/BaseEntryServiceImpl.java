package com.xbmt.framework.service.impl;

import com.xbmt.framework.dao.IBaseEntryDao;
import com.xbmt.framework.entity.AbstractBaseDataEntry;
import com.xbmt.framework.service.IBaseEntryService;
import com.xbmt.framework.service.base.impl.CoreBaseServiceImpl;


/**
 * 分录  业务操作service 基础接口类 <p>
 * @author LingMin 
 * @date 2015-12-02<br>
 * @version 1.0<br>
 */
public class BaseEntryServiceImpl<E extends AbstractBaseDataEntry, ID extends java.io.Serializable> extends CoreBaseServiceImpl<E, ID> implements
		IBaseEntryService<E, ID> {

	/**分录数据操作接口***/
	IBaseEntryDao<E, ID> baseEntryDao;
	
	/***
	 * 构造方法注入dao数据库接口
	 * @param baseEntryDao
	 */
	public BaseEntryServiceImpl(IBaseEntryDao<E, ID> baseEntryDao) {
		super(baseEntryDao);
		this.baseEntryDao = baseEntryDao;
	}

	
	
	/**
	 * 根据主表关联字段名与主表关键字获取满足条件的数据关键字<p>
	 */
	public String[] getEntryKeys(String parentField, String parentKey) {
		return this.baseEntryDao.getEntryKeys(parentField, parentKey);
	}

	/**
	 * 根据主表关键字与主表关联字段名批量删除分录数据<p>
	 * @param mappedField 主表关联字段名<br>
	 * @param parentKey 主表关键字<br>
	 */
	public void deleteEntryList( String mappedField , ID parentKey) {
		this.baseEntryDao.deleteEntryList(mappedField, parentKey);
	}
	
	/**
	 * 根据主表关键字与主表关联字段名获取分录信息<p>
	 */
	public java.util.List<E> getEntryList(String mappedField , ID parentKey) {
		return this.baseEntryDao.getEntryList(mappedField, parentKey);
	}
	
	

}
