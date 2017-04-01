package com.xbmt.auth3;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import com.xbmt.common.StringUtils;
import com.xbmt.utils.HandleResultUtils;


/***
 * 定义该过滤器，用于拦截需要认证的URL资源链接
 * 
 * @author admin
 *
 */
public class AuthenticationTokenProcessingFilter extends GenericFilterBean{

   private final UserDetailsService userService;

   
   
   public AuthenticationTokenProcessingFilter(UserDetailsService userService){
      this.userService = userService;
   }


   /***
    * 对请求认证处理逻辑
    * 2016-07-13
    * 经过测试发现：如果登录成功后，前台退出，但是后台session未注销，还是可以进行访问，说明此种方式是有状态的
    * 如果要做到完全无状态的处理，就在该filter中增加逻辑，如果没有token直接返回401未认证错误，因为所有通过该过滤器的请求都必须要认证的
    * 如果不需要认证的请求，通过配置文件中进行过滤掉，如：<security:http pattern="/api/account/login" security="none" />
    * 
    * 其中 1 和 2的判断 开发调试时，可以先注释掉，这样前台开发者 就可以直接在浏览器中访问接口可以获取数据，否则无法获取
    * 当开发完成后，就需要还原回来，保证接口的安全性
    * 
    * 根据用户名 查询数据用户信息时，可以做适当缓存，否则每次查询数据库，效率低下，可以参考 HTTP摘要认证过滤器,如下：
    * org.springframework.security.web.authentication.www.DigestAuthenticationFilter
    * UserCache userCache = new NullUserCache();
    */
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
      HttpServletRequest httpRequest = this.getAsHttpRequest(request);
      //1、获取请求token信息[请求头 或 请求参数]
      String authToken = this.extractAuthTokenFromRequest(httpRequest);
      if(StringUtils.isEmpty(authToken)){
    	  //直接 返回 401 未认证错误，要求前台必须登录后才 才可以进行访问
    	  HandleResultUtils.sendResponseHandleResult(response, HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Authentication token was either missing or invalid.");
    	  return; //不return 会继续执行后面代码，出现异常：java.lang.IllegalStateException: Cannot create a session after the response has been committed
      }
      //2、根据token分割用户名  "user:1468401003052:41f0a8ee0a79263b7f5f7ca4fe18b6b0"
      String userName = TokenUtils.getUserNameFromToken(authToken);
      if(StringUtils.isEmpty(userName)){
    	  //直接 返回 401 未认证错误，要求前台必须登录后才 才可以进行访问
    	  HandleResultUtils.sendResponseHandleResult(response, HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Authentication token was either missing or invalid.");
    	  return; //不return 会继续执行后面代码，出现异常：java.lang.IllegalStateException: Cannot create a session after the response has been committed
      }
      try{
	      if (userName != null) {
	    	 
		         UserDetails userDetails = this.userService.loadUserByUsername(userName);
		         //3、验证token 是否合法
		         if (TokenUtils.validateToken(authToken, userDetails)) {
		            UsernamePasswordAuthenticationToken authentication =
		                  new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
		            SecurityContextHolder.getContext().setAuthentication(authentication);
		         }
	      }
	      chain.doFilter(request, response);
      }catch(UsernameNotFoundException e){//用户名不存在
    	  e.printStackTrace();
		  StringBuffer msg = new StringBuffer();
		  msg.append("认证失败，用户名或密码错误!!");
		  //msg.append("Exception.msg=").append(e.getClass().getName());
		 // msg.append(":");
		 // msg.append(e.getMessage());
		  HandleResultUtils.sendResponseHandleResult(response, -10 , msg.toString());
      }catch(Exception e){
		  e.printStackTrace();
		  StringBuffer msg = new StringBuffer();
		  msg.append("Unauthorized:Authentication token was either missing or invalid.");
		  msg.append("Exception.msg=").append(e.getClass().getName());
		  msg.append(":");
		  msg.append(e.getMessage());
		  HandleResultUtils.sendResponseHandleResult(response, HttpServletResponse.SC_UNAUTHORIZED, msg.toString());
	  }
   }


   private HttpServletRequest getAsHttpRequest(ServletRequest request)
   {
      if (!(request instanceof HttpServletRequest)) {
         throw new RuntimeException("Expecting an HTTP request");
      }

      return (HttpServletRequest) request;
   }


   /***
    * 1、首先获取请求头 认证 X-Auth-Token信息
    * 2、如果认证头token为空，则获取请求参数token名称
    * @param httpRequest
    * @return
    */
   private String extractAuthTokenFromRequest(HttpServletRequest httpRequest)
   {
      /* Get token from header */
      String authToken = httpRequest.getHeader("X-Auth-Token");

      /* If token not found get it from request parameter */
      if (authToken == null) {
         authToken = httpRequest.getParameter("token");
      }

      return authToken;
   }
}
