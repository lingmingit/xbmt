/**
 * 
 */
package com.xbmt.test.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xbmt.common.StringUtils;

/***
 * 测试 Spring MVC Web 框架搭建<p>
 * @author LingMin 
 * @date 2015年5月7日<br>
 * @version 1.0<br>
 */
@Controller
@RequestMapping(value="/test")
public class TestController{

	/** 日志书写对象**/
	protected org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());
	
	/*****
	 * 指定首页跳转地址<p>
	 * @return String<p>
	 */
	protected  String getIndexURL(){
		return "";
	}
	
	
	private void displaySession(HttpServletRequest request){
		HttpSession mysession = request.getSession(false);
		if(mysession==null){
			mysession = request.getSession(true);
			mysession.setAttribute("appname","value-A");
			logger.info("new session:"+mysession.getId());
		}else{
			logger.info("old session:"+mysession.getId());
		}
		logger.info("appname="+mysession.getAttribute("appname"));
	}
	/**
	 * @param request请求对象
	 * @param response 响应对象
	 * @return
	 */
	@RequestMapping()
	public String list(HttpServletRequest request , HttpServletResponse response,  ModelMap model) {
		this.displaySession(request);
		String[] strlist = {"aaaa" , "bbbb" , "cccc" , "dddd"};
		//从数据库查询实体对象
		model.addAttribute("strList", strlist);
		//model.addAttribute("dataList", entityList);
		return "test"; 
	}
	
	/***
	 * <p>
	 * @return <p>
	 * String
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String list1(HttpServletRequest request , @PathVariable String id){
		logger.info("id=="+id);
		this.displaySession(request);
		return "test"; 
	}
	
	
	/***
	 * 请求两个参数<p>
	 * @return <p>
	 * String
	 */
	@RequestMapping(value = "/{id}/and/{name}", method = RequestMethod.GET)
	public String list2(HttpServletRequest request , @PathVariable String id , @PathVariable String name){
		logger.info("id=="+id+" \t name="+name);
		this.displaySession(request);
		return "test"; 
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
	@ResponseBody
	@RequestMapping(value = "/data/{id}", method = RequestMethod.GET )
	public List list3(HttpServletRequest request , @PathVariable String id){
		logger.info("id=="+id+" \t name=");
		this.displaySession(request);
		List list = new ArrayList();
		Map map = new HashMap();
		map.put("number", "0001");
		map.put("name", "张三");
		map.put("type", "类型1");
		map.put("address", "成都市");
		list.add(map);
//		
		map = new HashMap();
		map.put("number", "0002");
		map.put("name", "李思");
		map.put("type", "类型2");
		map.put("address", "成都市xxx");
		list.add(map);
		
		return list; 
	}
	
	
	
	
	
	/***
	 * 请求xml 对象<p>
	 * 【测试结果：该方法只支持json格式，支持xml请求数据格式】
	 * @return <p>
	 * String
	 */
	@ResponseBody
	@RequestMapping(value = "/listData", method = RequestMethod.GET)
	public List listData(HttpServletRequest request , HttpServletResponse response){
		
		this.displaySession(request);
		String startStr = request.getParameter("start");
	    String limitStr = request.getParameter("limit");
	    
	    int start = 0;
	    if(StringUtils.isNotEmpty(startStr)){
	    	start = Integer.valueOf(startStr);
	    }
	    int limit = 10;
	    if(StringUtils.isNotEmpty(limitStr)){
	    	limit = Integer.valueOf(limitStr);
	    }
	    logger.info("start="+start+" \t limit="+limit);
		List list = new ArrayList();
		Map map = new HashMap();
		map.put("number", "0001");
		map.put("name", "张三");
		map.put("type", "类型1");
		map.put("address", "成都市");
		list.add(map);
//		
		map = new HashMap();
		map.put("number", "0002");
		map.put("name", "李思");
		map.put("type", "类型2");
		map.put("address", "成都市xxx");
		list.add(map);
		
	    return list;
	}
	
}
