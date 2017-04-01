package com.xbmt.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.xbmt.framework.dao.base.ICoreBaseDao;
import com.xbmt.framework.dao.common.SQLCondition;
import com.xbmt.framework.service.base.impl.CoreBaseServiceImpl;



/**
 * 基于JPA + Ehcache缓存 的业务逻辑层操作核心接口实现类<p>
 * @author LingMin 
 * @date 2016-07-27<br>
 * @version 1.0<br>
 * @param <E>  实体对象<br>
 * @param <ID> 实体关键字<br>
 */
@SuppressWarnings({"rawtypes"})
public abstract class CoreBaseEhcacheServiceImpl<E, ID extends java.io.Serializable> extends CoreBaseServiceImpl<E, ID>{

	
	/**
	 * 构造函数:初始化数据库层核心操作对象<p>
	 * @param jpaBaseDao 数据库层核心操作对象<br>
	 */
	public CoreBaseEhcacheServiceImpl(ICoreBaseDao<E, ID> jpaBaseDao) {
		super(jpaBaseDao);
	}
	
	/**
	 * 根据关键字获取指定的数据记录<p>
	 */
	@Cacheable(value = "xbmtCache", key = "#key")
	public E get(final ID key) {
		return super.get(key);
	}
	
	/**
	 * 新增指定的数据记录<p>
	 */
	@Transactional(propagation=Propagation.REQUIRED)
    @CacheEvict(value="xbmtCache",allEntries=true)
	public ID save(E entity) {
		return super.save(entity);
	}
	
	/**
	 * 根据关键字与实体CLASS对象返回指定的对象<p>
	 */
	@Cacheable(value="xbmtCache" , key = "#clazz.name + #key")
	public Object get(Class clazz,ID key) {
		return super.get(clazz, key);
	}
	
	/**
	 * 根据给定的detached（游离状态）对象实例的标识更新对应的持久化实例<p>
	 */
	@Transactional(propagation=Propagation.REQUIRED)
    @CacheEvict(value="xbmtCache",allEntries=true)
	public ID update(E entity) {
		return super.update(entity);
	}
	
	
	/**
	 * 根据实体对象进行删除<p>
	 * @param entity 实体对象<br>
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	 @CacheEvict(value="xbmtCache",allEntries=true)
	public void delete(E entity){
		super.delete(entity);
	}
	/**
	 * 根据关键字删除指定的数据记录<p>
	 */
	@Transactional(propagation=Propagation.REQUIRED)
    @CacheEvict(value="xbmtCache",allEntries=true)
	public void delete(final ID key) {
		super.delete(key);
	}

    
    /**
     * 根据条件批量删除指定数据记录<p>
     */
    @Transactional(propagation=Propagation.REQUIRED)
    @CacheEvict(value="xbmtCache",allEntries=true)
    public void delete(final SQLCondition condition) {
    	super.delete(condition);
    }
    
	
	/**
	 * 以JPA的方式批量删除指定的实体对象集合<p>
	 */
	@Transactional(propagation=Propagation.REQUIRED)
    @CacheEvict(value="xbmtCache",allEntries=true)
	public void batchDelete(final java.util.List<E> list) {
		super.batchDelete(list);
	}
	
	/**
	 * 以JPA的方式批量保存|更新实体对象集合<p>
	 */
	@Transactional(propagation=Propagation.REQUIRED)
    @CacheEvict(value="xbmtCache",allEntries=true)
	public void batchSaveOrUpdate(java.util.List<E> list) {
		super.batchSaveOrUpdate(list);
	}

	/**
	 * 判断指定的结点下是否包含数据信息<p>
	 */
	@Cacheable(value="xbmtCache")
	public boolean isExistenceRecord(String groupFiled, String groupKey) {
		return super.isExistenceRecord(groupFiled, groupKey);
	}

	
  /**
	 * 以JPA的方式执行带参数的数据库查询<p>
	 * @param jpql JPQL语句<br>
	 * @param param 参数<br>
	 * @return 查询结果集<br>
	 */
    @Cacheable(value="xbmtCache" , key = "#jpql + #params[0]")
	public java.util.List<Object> findByJPQL(final String jpql, final Object... params) {
		return super.findByJPQL(jpql, params);
	}
    
    
    /**
     * 根据条件查询记录的总条数<p>
     * @param condition 查询条件<br>
     * @return 查询结果集<p>
     */
    @Cacheable(value="xbmtCache"  , key = "#condition.entityName + #difference")
    public int count(final SQLCondition condition, final String difference) {
    	return super.count(condition , difference);
    }
    
    
    /**
	 * 以JPA分页的方式执行数据库查询<p>
	 */
    @Cacheable(value="xbmtCache" , key = "#start + #condition.entityName")
	public java.util.List<E> list(final int start, final int limit, final SQLCondition condition) {
		return super.list(start, limit, condition);
	}
	
	/**
	 * 以JPQL的方式查询分页数据信息<p>
	 */
	@Cacheable(value="xbmtCache" , key = "#start + #jpql +#params[0]")
	public java.util.List<Object> list(final int start, final int limit, final String jpql, final Object... params) {
		return super.list(start, limit, jpql, params);
	}
		
}
