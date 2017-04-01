package com.xbmt.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.xbmt.framework.entity.sys.SysUser;
import com.xbmt.framework.mapper.SysUserMapper;
import com.xbmt.service.IUserService;


/**
 * 用户业务service 接口实现类
 * CRUD (Create 创建，Retrieve 读取，Update 更新，Delete 删除) 
 * @author LingMin 
 * @date 2016-08-25<br>
 * @version 1.0<br>
 */
@Service
public class UserServiceImpl implements IUserService {
	//日志打印对应
		protected Logger logger = LoggerFactory.getLogger(this.getClass());
	//注入 mapper操作类
	@Autowired
	protected SysUserMapper userMapper;
	
  /***
    * 根据用户名查找用户实体信息
    * @Cacheable(value = "xbmtCache", key = "#key")
    * @CacheEvict(value="xbmtCache",allEntries=true)
    *  #clazz.name
    * @param userName 用户名
    * @return
    */
	@Cacheable(value = {"default", "commonCache"} , key="#root.targetClass + #root.methodName + #userName") 
	public SysUser findUserByLoginName(String userName){
		return this.userMapper.findUserByLoginName(userName);
	}
	
	  /***
	    * 检查登录用户名是否存在
	    * @param userName
	    * @param id
	    * @return
	    */
	@Cacheable(value = {"default", "commonCache"} , key="#root.methodName + #numbers + #id") 
	public SysUser getUserByAccount(String numbers ,String id){
		return this.userMapper.getUserByAccount(numbers, id);
	}
	
	/***
	 * 新增方法
	 *  
	 */
	//@CachePut(value= {"default", "commonCache"}, key="#entity.id")//  列表查询 不能显示
	@CacheEvict(value= {"default", "commonCache"}, allEntries = true)
	public int add(SysUser entity) {
		int num = userMapper.add(entity);
		logger.info("num="+num+" \t id="+entity.getId());
		return num;
	}

	/***
	 * 查询方法
	 */
	@Cacheable(value = {"default", "commonCache"} , key="#root.targetClass + #root.methodName + #map") 
	public List<SysUser> getList(Map map) {
		return userMapper.getList(map);
	}
	
	// R
	/***
	 * 查询方法
	 */
	@Cacheable(value = {"default", "commonCache"} , key="#root.targetClass + #root.methodName + #numbers") 
	public List<SysUser> getList2(String numbers){
		return userMapper.getList2(numbers);
	}

	/***
	 * 查询方法
	 */
	@Cacheable(value = {"default", "commonCache"} , key="#id") 
	public SysUser get(String id) {
		return userMapper.get(id);
	}

	/***
	 * 修改方法
	 *  , key="#entity.id"
	 */
	//@CachePut(value= {"default", "commonCache"}, key="#entity.id")//  列表查询 不能显示
	@CacheEvict(value= {"default", "commonCache"}, allEntries = true)
	public int update(SysUser entity) {
		return userMapper.update(entity);
	}

	/***
	 * 删除方法
	 */
	@CacheEvict(value= {"default", "commonCache"}, allEntries = true)
	public int delete(String id) {
		return userMapper.delete(id);
	}

}
