package com.xbmt.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;


/***
 * 安全认证操作filter<p>
 * @author LingMin 
 * @date 2015-7-24<br>
 * @version 1.0<br>
 */
public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	public AuthenticationFilter() {
		super("/j_security_check");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException,
			IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

		this.setDetails(request, authRequest);
		Authentication authentication = this.getAuthenticationManager().authenticate(authRequest);

		return authentication;
	}

	protected void setDetails(HttpServletRequest request, UsernamePasswordAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

}
