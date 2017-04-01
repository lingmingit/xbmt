package com.xbmt.service;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.Cacheable;

import com.xbmt.framework.entity.sys.SysUser;



/**
 * 用户业务service 接口
 * @author LingMin 
 * @date 2016-08-25<br>
 * @version 1.0<br>
 */
public interface IUserService {
	
	 /***
	    * 根据用户名查找用户实体信息
	    * @param userName 用户名
	    * @return
	    */
	public SysUser findUserByLoginName(String userName);
	
	 /***
	    * 检查登录用户名是否存在
	    * @param userName
	    * @param id
	    * @return
	    */
	public SysUser getUserByAccount(String numbers ,String id);

	 // C
	public int add(SysUser entity); // 插入一条城市记录
	
	// R
	public List<SysUser> getList(Map map); // 查询list 
	
	// R
	public List<SysUser> getList2(String numbers); // 查询list 

	// R
	public SysUser get(String id); // 根据id 查询实体
	
	// U
	public int update(SysUser entity); // 更新实体
	
	// D
	public int delete(String id); // 删除实体
}
