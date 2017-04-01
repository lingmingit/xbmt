package com.xbmt.api.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xbmt.admin.vo.HandleResultVo;
import com.xbmt.admin.vo.UserVo;
import com.xbmt.auth3.MyUser;
import com.xbmt.auth3.TokenUtils;
import com.xbmt.utils.HandleResultUtils;


/***
 * 登录controller 定义
 * @author admin
 *
 */
//@Controller
//@RequestMapping(value="/auth3")
public class LoginAuth3Controller {

	   @Autowired
	    private UserDetailsService userService;

	    @Autowired
	    @Qualifier("authenticationManager")
	    private AuthenticationManager authManager;

	    /**
	     * Retrieves the currently logged in user.
	     *
	     * @return A transfer containing the username and the roles.
	     */
	    @RequestMapping(value = "/get_login_user",method= RequestMethod.GET)
	    @ResponseBody
	    public HandleResultVo getUser()
	    {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        Object principal = authentication.getPrincipal();
	        if (principal instanceof String && ((String) principal).equals("anonymousUser")) {
	            throw new BadCredentialsException("Bad Credentials");
	        }
	        UserDetails userDetails = (UserDetails) principal;
	        MyUser myUser = null;
	        if(userDetails instanceof MyUser){
	        	myUser = (MyUser)userDetails;
	        }
	        HandleResultVo resultVo = new HandleResultVo();
	        resultVo.setCode(1);
	       // resultVo.setEntity(new UserTransfer(userDetails.getUsername(), this.createRoleMap(userDetails)));
	        resultVo.setEntity(new UserVo(myUser.getId() , myUser.getUsername() , myUser.getPassword() , TokenUtils.createToken(userDetails), myUser.getMobile()));
	        resultVo.setMessage("成功");
	        return resultVo;
	    }

	    /***
	     * 用户登录验证方法
	     * @param username
	     * @param password
	     * @return
	     */
	    @RequestMapping(value = "/login",method = RequestMethod.POST)
	    @ResponseBody()
	    public HandleResultVo authenticate(@RequestParam String username, @RequestParam String password){
	    	
	    	try{
		    	//1、通过用户和密码 构建认证对象
		        UsernamePasswordAuthenticationToken authenticationToken =
		                new UsernamePasswordAuthenticationToken(username, password);
		        //2、通过认证管理器进行 验证，如果验证失败，抛出该异常  BadCredentialsException
		        Authentication authentication = this.authManager.authenticate(authenticationToken);
		        //3、验证通过后， 设置上下文认证信息
		        SecurityContextHolder.getContext().setAuthentication(authentication);
	    	}catch(org.springframework.security.authentication.BadCredentialsException e){
	    		e.printStackTrace();
	    		//org.springframework.security.authentication.BadCredentialsException: Bad credentials
	    		return HandleResultUtils.getHandleResultVo(-10, "认证失败，请检查用户密码是否正确");
	    	}
	        
	      /*
	       * Reload user as password of authentication principal will be null after authorization and
	       * password is needed for token generation
	       */
	        UserDetails userDetails = this.userService.loadUserByUsername(username);
	        MyUser myUser = null;
	        if(userDetails instanceof MyUser){
	        	myUser = (MyUser)userDetails;
	        }
//	        Map<String, Object> map = new HashMap<String, Object>();
//	        map.put("token", new TokenTransfer(TokenUtils.createToken(userDetails)));
	        //4、返回 token字符串
	       // return new TokenTransfer(TokenUtils.createToken(userDetails));
	        HandleResultVo resultVo = new HandleResultVo();
	        resultVo.setCode(1);
	        //new TokenTransfer(TokenUtils.createToken(userDetails))
	        resultVo.setEntity(new UserVo(myUser.getId() , myUser.getUsername() , myUser.getPassword() , TokenUtils.createToken(userDetails), myUser.getMobile()));
	        resultVo.setMessage("成功");
	        
	        return resultVo;
	    }
	    
	    
	    /***
	     * 
	     * @param userDetails
	     * @return
	     */
	    private Map<String, Boolean> createRoleMap(UserDetails userDetails)
	    {
	        Map<String, Boolean> roles = new HashMap<String, Boolean>();
	        for (GrantedAuthority authority : userDetails.getAuthorities()) {
	            roles.put(authority.getAuthority(), Boolean.TRUE);
	        }

	        return roles;
	    }
}
