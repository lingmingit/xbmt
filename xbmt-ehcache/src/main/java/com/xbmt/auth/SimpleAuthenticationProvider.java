package com.xbmt.auth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.xbmt.common.CommonUtils;
import com.xbmt.common.security.EncryptUtils;
import com.xbmt.framework.entity.sys.SysUser;
import com.xbmt.sys.service.IUserService;



/****
 * 安全认证操作适配器<p>
 * @author LingMin 
 * @date 2015-7-24<br>
 * @version 1.0<br>
 */
public class SimpleAuthenticationProvider implements AuthenticationProvider {

	//安全认证操作service
	private UserDetailsService userDetailsService;
	//用户业务操作接口类
	private IUserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		if (!supports(authentication.getClass())) {
			return null;
		}

		Object principal = authentication.getPrincipal();
		Object credentials = authentication.getCredentials();

		if (authentication.getPrincipal() == null) {
			return null;
		}

		if (!(principal instanceof String)) {
			return null;
		}

		if (!(credentials instanceof String)) {
			return null;
		}
		if (userService == null) {
			return null;
		}
		if (principal != null  || credentials != null) {
			String account = String.valueOf(principal);
			SysUser sysUser = userService.findUserByAccount(account);
			String password = String.valueOf(credentials).toString();
			String md5PD = EncryptUtils.md5Encode(password);
			if (sysUser != null && CommonUtils.isNotEmptyObject(sysUser.getPassword())) {
				if (!sysUser.getPassword().equals(md5PD)) {
					return null;
				}
			} else {
				return null;
			}
		}
		UsernamePasswordAuthenticationToken result = null; 
		if (result == null) {
			result = this.authenticatePrincipal((String) principal, (String) credentials);
		}

		return result;
	}

	private UsernamePasswordAuthenticationToken authenticatePrincipal(
			final String principal, final String credentials) {
		UserDetails userDetails = this.loadUserByPrincipal(principal);
		return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails, userDetails.getAuthorities());
	}

	protected UserDetails loadUserByPrincipal(final String principal) {
		return this.userDetailsService.loadUserByUsername(principal);
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	

}
