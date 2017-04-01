/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.sys.dao;

import com.xbmt.framework.dao.IDataBaseDao;
import com.xbmt.framework.entity.sys.SysUser;

/** 
 * 用户数据库操作接口类<p>
 * @author LingMin 
 * @date 2015-9-2<br>
 * @version 1.0<br>
 */
public interface IUserDao extends IDataBaseDao<SysUser, String> {

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
