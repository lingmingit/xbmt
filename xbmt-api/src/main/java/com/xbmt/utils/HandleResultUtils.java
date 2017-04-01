package com.xbmt.utils;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletResponse;

import com.xbmt.admin.vo.HandleResultVo;
import com.xbmt.framework.web.utils.JacksonUtils;


/***
 * 返回处理结果 Vo 处理类
 * @author admin
 *
 */
public class HandleResultUtils {

	
	/***
	 * 通过response 发送错误处理结果Vo 到前台
	 * @param response http响应对象
	 * @param code 错误码
	 * @param msg 错误消息
	 * @throws IOException
	 */
	public static void sendResponseHandleResult(ServletResponse response , int code , String msg) throws IOException{
		HandleResultUtils.sendResponseHandleResult(response , code , msg , null);
	}
	
	/***
	 * 通过response 发送错误处理结果Vo 到前台
	 * @param response http响应对象
	 * @param code 错误码
	 * @param msg 错误消息
	 * @param entity 返回数实体对象
	 * @throws IOException
	 */
	public static void sendResponseHandleResult(ServletResponse response , int code , String msg , Object entity) throws IOException{
    	  PrintWriter writer = response.getWriter();
    	  String result = JacksonUtils.getObjectMapperJsonStr(HandleResultUtils.getHandleResultVo(code , msg , entity));
    	  writer.write(result);
    	  writer.flush();
    	  writer.close();
	}
	
	/***
	 * 实例化 返回 处理结果vo 实体对象
	 * @param code
	 * @param msg
	 * @param entity
	 * @return
	 */
	public static HandleResultVo getHandleResultVo(int code , String msg , Object entity){
		return new HandleResultVo(code , msg , entity);
	}
	
	/***
	 * 实例化 返回 处理结果vo 实体对象
	 * @param code
	 * @param msg
	 * @param entity
	 * @return
	 */
	public static HandleResultVo getHandleResultVo(int code , String msg){
		return new HandleResultVo(code , msg);
	}
	
	
	
}
