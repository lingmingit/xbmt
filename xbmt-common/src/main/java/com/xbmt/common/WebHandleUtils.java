package com.xbmt.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




/****
 * Java Web 开发处理   帮忙类<p>
 * @author LingMin 
 * @date 2015-02-28<br>
 * @version 1.0<br>
 */
public class WebHandleUtils {
	
	
	
	
	

	/***
	 * 对特殊字符串进行解码处理   【如：EAS BOS UUID】
	 * @param value  需要处理的id
	 * @return 返回已经编码处理的 uuid
	 */
	public static String getDecodeString(String value){
		if(StringUtils.isEmpty(value)){
			return null;
		}
		 try {
			value = URLDecoder.decode(value, "utf-8");
			value = StringUtils.replace(value, " ", "+");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}

	
	
	/***
	 * 对特殊字符串进行编码处理   【如：EAS BOS UUID】
	 * @param ctx 服务器上下环境变量
	 * @param value  需要处理的id
	 * @param encode 是否编码处理
	 * @return 返回已经编码处理的 uuid
	 */
	public static String getEncodeString(String value ){
		if(StringUtils.isEmpty(value)){
			return null;
		}
	    value = WebHandleUtils.encodeString(value, "utf-8");
	    value = StringUtils.replace(value, "+", "%2B");
		return value;
	}
	

	/****
	 * 对特殊字符进行 URL 编码处理 ，用于URL传参使用
	 * @param string 原字符串
	 * @param charset 编码方式
	 * @return
	 */
	  public static String encodeString(String string, String charset){
	    if (charset == null)
	      charset = "ISO-8859-1";
	    byte[] bytes = null;
	    try
	    {
	      bytes = string.getBytes(charset);
	    }
	    catch (UnsupportedEncodingException e)
	    {
	      bytes = string.getBytes();
	    }

	    int len = bytes.length;
	    byte[] encoded = new byte[bytes.length * 3];
	    int n = 0;
	    boolean noEncode = true;

	    for (int i = 0; i < len; ++i)
	    {
	      byte b = bytes[i];

	      if (b == 32)
	      {
	        noEncode = false;
	        encoded[(n++)] = 43;
	      }
	      else if (((b >= 97) && (b <= 122)) || ((b >= 65) && (b <= 90)) || ((b >= 48) && (b <= 57)))
	      {
	        encoded[(n++)] = b;
	      }
	      else
	      {
	        noEncode = false;
	        encoded[(n++)] = 37;
	        byte nibble = (byte)((b & 0xF0) >> 4);
	        if (nibble >= 10)
	          encoded[(n++)] = (byte)(65 + nibble - 10);
	        else
	          encoded[(n++)] = (byte)(48 + nibble);
	        nibble = (byte)(b & 0xF);
	        if (nibble >= 10)
	          encoded[(n++)] = (byte)(65 + nibble - 10);
	        else
	          encoded[(n++)] = (byte)(48 + nibble);
	      }
	    }

	    if (noEncode)
	      return string;

	    try
	    {
	      return new String(encoded, 0, n, charset);
	    }
	    catch (UnsupportedEncodingException e) {
	    }
	    return new String(encoded, 0, n);
	  }
	
	
	/****
	 * url传递中文参数 编码转换方法
	 * @param str 需要转换的字符串
	 * @return 返回转换字符串
	 */
	public static String codeToString(String str){
		if(StringUtils.isEmpty(str)){
			return null;
		}
		//处理中文字符的函数
		String temp = str;
		try{
			byte tempB[] = temp.getBytes("ISO-8859-1");
			temp = new String(tempB , "UTF-8");
			return temp;
		}catch(Exception e){
			return temp;
		}
	}


	/***
	 * 提示错误消息到前台
	 * @param message 错误消息
	 * @param request http请求对象
	 * @param response http响应对象
	 */ 
	public static String gotoMsgPage(String message , HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("message", message);
		return "/common/message";
	}
	
	/***
	 * 将错误信息 通过ajax 返回到前台
	 * @param message 错误消息
	 * @param request http请求对象
	 * @param response http响应对象
	 */ 
	public static void ajaxWriterMsgPage(String message , HttpServletRequest request, HttpServletResponse response){
		try {
			response.setContentType("text/plain");
			response.getWriter().write(message);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}
