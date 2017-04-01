package com.xbmt.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xbmt.admin.vo.HandleResultVo;
import com.xbmt.admin.vo.UserVo;
import com.xbmt.auth2.AuthenticateParamVo;
import com.xbmt.auth2.MyDigestAuthUtils;
import com.xbmt.auth2.MyDigestAuthenticationEntryPoint;
import com.xbmt.auth2.MyTokenUtils;
import com.xbmt.auth3.MyUser;
import com.xbmt.auth3.TokenUtils;
import com.xbmt.framework.web.constants.WebConstants;
import com.xbmt.framework.web.controller.base.CoreController;
import com.xbmt.test.entity.TestEntity;
import com.xbmt.test.service.ITestEntityService;
import com.xbmt.utils.HandleResultUtils;



/***
 * 测试api 调用方法
 * @author admin
 *
 */
@Controller
@RequestMapping(value="/auth2")
public class LoginAuth2Controller extends CoreController {


	/***自动注入service***/
	@Autowired
	protected ITestEntityService testEntityService;
	
	 @Autowired
	 private UserDetailsService userService;
	 
	 /***自动注入service***/
	@Autowired
	protected MyDigestAuthenticationEntryPoint digestEntryPoint;
	 
	
	 @Autowired
     @Qualifier("authenticationManager")
     private AuthenticationManager authManager;
	
	int nonceValiditySeconds = 120;
	String key = "acegi";
	String realmName = "";
	
	/***
	 * @return <p>
	 * String
	 */
	@ResponseBody
	@RequestMapping(value = "/getToken", method = RequestMethod.GET )
	public HandleResultVo getToken(HttpServletRequest request, HttpServletResponse response){
		String userId = this.getCurrentSysUserId();
		logger.info("userId="+userId);
		
		//计算 签名 
		String nonceValueBase64 = MyTokenUtils.getNonceValueBase64(key, nonceValiditySeconds);
		String authDigest = MyTokenUtils.getAuthenticateHeader(digestEntryPoint.getRealmName(),  nonceValueBase64);
        response.addHeader(MyTokenUtils.RespAuthHeaderKey, authDigest);
        HandleResultVo vo = new HandleResultVo();
        vo.setCode(1);
        vo.setMessage("成功");
        //(String authHeaderName , String realmName , String key , int nonceValiditySeconds, String splitChar , String qop , String nonce , String authDigest){
        vo.setEntity(new AuthenticateParamVo(MyTokenUtils.RespAuthHeaderKey , MyTokenUtils.ReqAuthHeaderKey , digestEntryPoint.getRealmName() , digestEntryPoint.getKey() , digestEntryPoint.getNonceValiditySeconds(), MyTokenUtils.SplitChar , MyTokenUtils.Qop , nonceValueBase64 , authDigest));
		return vo;
	}

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
        logger.info("id="+myUser.getId()+" \t username="+myUser.getUsername());
        
        HandleResultVo resultVo = new HandleResultVo();
        resultVo.setCode(1);
       // resultVo.setEntity(new UserTransfer(userDetails.getUsername(), this.createRoleMap(userDetails)));
        resultVo.setEntity(new UserVo(myUser.getId() , myUser.getUsername() , myUser.getPassword() , null , myUser.getMobile()));
        resultVo.setMessage("成功");
        return resultVo;
    }

    /***
     * 用户登录验证方法
     * 登录方法 可以不用 验证用户是否合法，因为 MyDigestAuthenticationFilter中 已经进行验证 并且保存登录用户信息
     * 每次客户端 请求时，都会通过过滤器 验证摘要中的 用户信息、token时间等，都通过后才会 到资源方法中，所以这里自己返回登录用户信息即可
     * 这里再进行 用户相关验证 有些重复
     * @param username 登录用户名
     * @param password 登录密码
     * @param token token
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody()
    public HandleResultVo authenticate(@RequestParam String username, @RequestParam String password , @RequestParam String token){
    	
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
     
        
        HandleResultVo resultVo = new HandleResultVo();
        resultVo.setCode(1);
        //new TokenTransfer(TokenUtils.createToken(userDetails))
        resultVo.setEntity(new UserVo(myUser.getId() , myUser.getUsername() , myUser.getPassword() ,token , myUser.getMobile()));
        resultVo.setMessage("成功");
        
        return resultVo;
    }
    
    
	
	
	/***
	 * 请求json 对象【测试结果：该方法只支持json格式，不支持xml后缀请求(HTTP Status 406  org.springframework.web.HttpMediaTypeNotAcceptableException: Could not find acceptable representation)】<p>
	 *  注解 @ResponseBody 可以不增加，默认是json格式
	 * ,produces= MediaType.APPLICATION_JSON_VALUE
	 * ,produces= MediaType.APPLICATION_XML_VALUE
	 * ,headers = "Accept=* /*"
	 * @return <p>
	 * String
	 */
	@Secured("ROLE_USER")
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.GET )
	public HandleResultVo list(){
		List<TestEntity> list =  testEntityService.list(null);
		 HandleResultVo resultVo = new HandleResultVo();
         resultVo.setCode(1);
         resultVo.setEntity(list);
         resultVo.setMessage("成功");
	    return resultVo;
	}
	/***
	 * 请求xml 对象<p>
	 * 【测试结果：该方法只支持json格式，支持xml请求数据格式】
	 * @return <p>
	 * String
	 */
	@ResponseBody
	@RequestMapping(value = "/list2", method = RequestMethod.GET )
	public ModelAndView list2(){
		 List<TestEntity> list =  testEntityService.list(null);
		 HandleResultVo resultVo = new HandleResultVo();
         resultVo.setCode(1);
         resultVo.setEntity(list);
         resultVo.setMessage("成功");
		return new ModelAndView(WebConstants.XML_VIEW_NAME, "testEntityList", resultVo);
	}
}
