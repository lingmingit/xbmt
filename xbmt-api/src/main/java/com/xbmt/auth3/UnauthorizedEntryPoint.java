package com.xbmt.auth3;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.xbmt.admin.vo.HandleResultVo;
import com.xbmt.framework.web.utils.JacksonUtils;


/***
 * 定义拦截器 切入点
 * @author admin
 *
 */
public class UnauthorizedEntryPoint implements AuthenticationEntryPoint{
   @Override
   public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
         throws IOException, ServletException{
    //  response.sendError( HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized: Authentication token was either missing or invalid.");
      
      HandleResultVo resultVo = new HandleResultVo();
      resultVo.setCode(HttpServletResponse.SC_UNAUTHORIZED);
      resultVo.setMessage("Unauthorized: Authentication token was either missing or invalid.");
  	  response.setCharacterEncoding("UTF-8");
	//response.setContentType("text/plain");
	//response.setContentType("text/json"); 
	  PrintWriter writer = response.getWriter();
	  String result = JacksonUtils.getObjectMapperJsonStr(resultVo);
	  writer.write(result);
	  writer.flush();
	  writer.close();
   }

}