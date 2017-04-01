/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.framework.dao;

import com.xbmt.framework.entity.AbstractTreeData;

/** 
 * 树形基础资料基类数据操作接口类<p>
 * @author LingMin 
 * @date 2015-10-29<br>
 * @version 1.0<br>
 */
public interface ITreeDataDao<E extends AbstractTreeData, ID extends java.io.Serializable> extends IDataBaseDao<E, ID> {

	/**
	 * 根据结点关键字查询该结点下叶子结点信息 默认按numbers 字段升序<p>
	 * @param parentKey 结点关键字<br>
	 * @param mappedField 父结点关联字段名<br>
	 * @return 叶子结点信息集<br>
	 */
	public java.util.List<E> getChildrenNodeList(final ID parentKey, final String mappedField);
	
	/**
	 * 根据结点关键字查询该结点下叶子结点信息<p>
	 * @param parentKey 结点关键字<br>
	 * @param mappedField 父结点关联字段名<br>
	 * @param orderByFieldName 排序字段名称
	 * @return 叶子结点信息集<br>
	 */
	public java.util.List<E> getChildrenNodeList(final ID parentKey, final String mappedField , final String orderByFieldName);
	
	
}
