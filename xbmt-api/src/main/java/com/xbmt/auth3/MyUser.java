/**
 * 
 */
package com.xbmt.auth3;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * 定义自己的MyUser 类 继承 Spring security User类
 * （由于 它的User 没有用户id 或者自定义字段）
 * @author admin
 *
 */
public class MyUser extends User {

	/***
	 * 用户id
	 */
	private String id = null;
	//手机号码
	private String mobile = null;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3849967110763946577L;
	
	/***
	 * 重写构造方法
	 * @param userId 用户id
	 * @param username
	 * @param password
	 * @param authorities
	 * @param mobile 手机号码
	 */
	 public MyUser(String userId , String username, String password, Collection<? extends GrantedAuthority> authorities , String mobile) {
	        super(username, password, true, true, true, true, authorities);
	        this.id = userId;
	        this.mobile = mobile;
	  }
	/***
	 * 重写构造方法
	 * @param username
	 * @param password
	 * @param enabled
	 * @param accountNonExpired
	 * @param credentialsNonExpired
	 * @param accountNonLocked
	 * @param authorities
	 */
	public MyUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
	}
	
	
	
	/**
	 * @return the 用户id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param 用户id the 用户id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	

	
}
