/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.sys.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.xbmt.common.CommonUtils;
import com.xbmt.common.StringUtils;
import com.xbmt.framework.dao.impl.DataBaseDaoImpl;
import com.xbmt.framework.entity.sys.SysUser;
import com.xbmt.sys.dao.IUserDao;

/**
 * 用户数据库操作接口实现类 <p>
 * @author LingMin 
 * @date 2015-9-2<br>
 * @version 1.0<br>
 */
@Repository("sys.userDao")
public class UserDaoImpl extends DataBaseDaoImpl<SysUser, String, IUserDao> implements IUserDao {

	
	/***
	 * 检查 用户账户是否存在<p>
	 * @param userName用户登录账号
	 * @param userId 当前用户id
	 * @return 返回用户实体<p>
	 * User
	 */
	public SysUser checkUserByAccount(String userName , String userId) {
		String sql = "SELECT u From SysUser u WHERE  u.numbers=:account";
		if(StringUtils.isNotBlank(userId)){
			sql = sql.concat(" and u.id <>'").concat(userId).concat("'");
		}
		Query query = entityManager.createQuery(sql);
		query.setParameter("account", userName);
		//query.setParameter("id", userId);
		List<SysUser> userList = query.getResultList();
		SysUser sysUser = null;
		if (CommonUtils.isNotEmptyList(userList)) {
			sysUser = userList.get(0);
		}
		return sysUser;
	}
	
	/***
	 * 查询 用户账户是否存在<p>
	 * @param userName用户登录账号
	 * @return 返回用户实体<p>
	 * User
	 */
	public SysUser findUserByAccount(String userName) {
		String sql = "SELECT u From SysUser u WHERE  u.numbers=:account AND u.isEnable=1 AND u.isArchive=0 AND u.isLocked=0 AND u.isDelete=0";
		Query query = entityManager.createQuery(sql);
		query.setParameter("account", userName);
		List<SysUser> userList = query.getResultList();
		logger.info("test.......................");
		SysUser sysUser = null;
		if (CommonUtils.isNotEmptyList(userList)) {
			sysUser = userList.get(0);
		}
		return sysUser;
	}
}
