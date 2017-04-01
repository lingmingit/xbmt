/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xbmt.framework.entity.sys.SysUser;
import com.xbmt.service.DataBaseEhcacheServiceImpl;
import com.xbmt.sys.dao.IUserDao;
import com.xbmt.sys.service.IUserService;

/**
 * 用户业务操作接口实现类 <p>
 * @author LingMin 
 * @date 2015-9-2<br>
 * @version 1.0<br>
 */
@Service("sys.userService")
public class UserServiceImpl extends DataBaseEhcacheServiceImpl<SysUser, String> implements IUserService {


	/***数据库操作接口类***/
	protected IUserDao userDao;
	
	/****
	 * 构造方法
	 * @param userDao  数据库操作接口类
	 */
	@Autowired(required=true)
	public UserServiceImpl(IUserDao userDao) {
		super(userDao);
		this.userDao = userDao;
	}
	
	
	/***
	 * 检查 用户账户是否存在<p>
	 * @param userName用户登录账号
	 * @param userId 当前用户id
	 * @return 返回用户实体<p>
	 * User
	 */
	public SysUser checkUserByAccount(String userName , String userId){
		return this.userDao.checkUserByAccount(userName, userId);
	}
	/***
	 * 查询 用户账户是否存在<p>
	 * @param userName用户登录账号
	 * @return User 返回用户实体<p>
	 * 
	 */
	public SysUser findUserByAccount(String userName){
		return this.userDao.findUserByAccount(userName);
	}
}
