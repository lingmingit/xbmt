package com.xbmt.auth;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.xbmt.auth3.MyUser;
import com.xbmt.common.CommonUtils;
import com.xbmt.common.StringUtils;
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
		if(StringUtils.isEmpty(account)){
			return null;
		}
		SysUser sysUser = userService.findUserByAccount(account);
		if(CommonUtils.isEmptyObject(sysUser)){
			//return null;
			throw new UsernameNotFoundException("用户名不存在!!");
		}
		String userId = sysUser.getId();
		// 取得用户的权限
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		// Spring security User类
		//User user = new User(account, sysUser.getPassword(), authorities);
		//自定义 User类 继承 Spring security User类 增加自定义字段
		MyUser user = new MyUser(sysUser.getId() , account, sysUser.getPassword(), authorities , sysUser.getMobilePhone());
		
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
