package com.xbmt.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import com.xbmt.common.CommonUtils;
import com.xbmt.framework.dao.ITreeDataDao;
import com.xbmt.framework.entity.AbstractTreeData;
import com.xbmt.framework.service.ITreeDataService;
import com.xbmt.framework.service.impl.DataBaseServiceImpl;



/** 
 * （Ehcache 缓存）树形基础资料基类业务操作接口实现类<p>
 * @author LingMin 
 * @date 2015-10-29<br>
 * @version 1.0<br>
 */
public abstract class TreeDataEhcacheServiceImpl<E extends AbstractTreeData, ID extends java.io.Serializable> extends DataBaseServiceImpl<E, ID> implements ITreeDataService<E, ID> {

	/**数据库操作接口类**/
	protected ITreeDataDao<E, ID> treeDataDao;
	
	/***
	 * 构造方法注入数据库操作接口dao
	 * @param treeDataDao
	 */
	public TreeDataEhcacheServiceImpl(ITreeDataDao<E, ID> treeDataDao) {
		super(treeDataDao);
		this.treeDataDao = treeDataDao;
	}
	
	/**
	 * 根据结点关键字查询该结点下叶子结点信息  默认按numbers 升序排列 <p>
	 * @param parentKey 结点关键字<br>
	 * @param mappedField 父结点关联字段名<br>
	 * @return 叶子结点信息集<br>
	 */
	@Cacheable(value = "xbmtCache", key = "#parentKey + #mappedField")
	public java.util.List<E> getChildrenNodeList(ID parentKey, String mappedField){
		return this.treeDataDao.getChildrenNodeList(parentKey, mappedField);
	}

	/**
	 * 根据结点关键字查询该结点下叶子结点信息<p>
	 * @param parentKey 结点关键字<br>
	 * @param mappedField 父结点关联字段名<br>
	 * @param orderByFieldName 排序字段名称
	 * @return 叶子结点信息集<br>
	 */
	@Cacheable(value = "xbmtCache", key = "#parentKey + #mappedField + #orderByFieldName")
	public java.util.List<E> getChildrenNodeList(final ID parentKey, final String mappedField , final String orderByFieldName){
		return this.treeDataDao.getChildrenNodeList(parentKey, mappedField, orderByFieldName);
	}
	
	/**
	 * 根据结点关键字查询该结点下叶子结点信息【多级】<p>
	 * @param parentKey 结点关键字<br>
	 * @param mappedField 父结点关联字段名<br>
	 * @param orderByFieldName 排序字段名称
	 * @return 叶子结点信息集<br>
	 */
	@Cacheable(value = "xbmtCache", key = "#parentKey + #mappedField + #orderByFieldName")
	public java.util.List<E> getMultipleLevelChildrenNodeList(final ID parentKey, final String mappedField , final String orderByFieldName){
		List<E> childrenList = this.treeDataDao.getChildrenNodeList(parentKey, mappedField, orderByFieldName);
		if(CommonUtils.isNotEmptyList(childrenList)){
			for(E entity : childrenList){
				//this.setChildrenList(entity, mappedField, orderByFieldName);
				List<E> tempchildrenList = this.getMultipleLevelChildrenNodeList((ID)entity.getId(), mappedField, orderByFieldName);
				entity.setChildren(tempchildrenList);
			}
		}
		return childrenList;
	}
	
	/***
	 * 递归  设置 子实体list<p>
	 * @param entity parent实体对象
	 * @param mappedField 映射实体parent字段
	 * @param orderByFieldName 排序字段<p>
	 * void
	 */
	public void setChildrenList(E entity ,  String mappedField ,  String orderByFieldName){
		List<E> childrenList =  this.getChildrenNodeList((ID)entity.getId(), mappedField , orderByFieldName);
		if(CommonUtils.isNotEmptyList(childrenList)){
			entity.setChildren(childrenList);
			for(E temp : childrenList){
				this.setChildrenList(temp , mappedField, orderByFieldName);
			}
		}
	}
	
}
