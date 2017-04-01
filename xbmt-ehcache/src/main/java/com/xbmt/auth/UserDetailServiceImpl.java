package com.xbmt.auth;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.xbmt.framework.entity.sys.SysUser;
import com.xbmt.sys.service.IUserService;


/***
 * 用户安全认证操作<p>
 * @author LingMin 
 * @date 2015-7-24<br>
 * @version 1.0<br>
 */
public class UserDetailServiceImpl implements UserDetailsService {
	
	//用户业务操作接口类
	private IUserService userService;
	/**
	 * 登录验证
	 */
	@Override
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
		SysUser sysUser = userService.findUserByAccount(account);
		String userId = sysUser.getId();
		// 取得用户的权限
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_HGMK_USER"));
		User user = new User(userId, "", authorities);
		return user;
	}

	/***
	 * <p>
	 * @param userService <p>
	 * void
	 */
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
}
