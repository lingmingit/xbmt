/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.sys.service;

import com.xbmt.framework.entity.sys.SysUser;
import com.xbmt.framework.service.IDataBaseService;

/** 
 * 用户业务操作接口类<p>
 * @author LingMin 
 * @date 2015-9-2<br>
 * @version 1.0<br>
 */
public interface IUserService extends IDataBaseService<SysUser, String> {

	
	/***
	 * 检查 用户账户是否存在<p>
	 * @param userName用户登录账号
	 * @param userId 当前用户id
	 * @return 返回用户实体<p>
	 * User
	 */
	public SysUser checkUserByAccount(String userName , String userId);
	/***
	 * 查询 用户账户是否存在<p>
	 * @param userName用户登录账号
	 * @return User 返回用户实体<p>
	 * 
	 */
	public SysUser findUserByAccount(String userName);
}
