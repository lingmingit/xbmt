/**
 * 
 */
package com.xbmt.test.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xbmt.common.StringUtils;
import com.xbmt.framework.dao.common.ConditionParameter;
import com.xbmt.framework.dao.common.OrderParameter;
import com.xbmt.framework.dao.common.SQLCondition;
import com.xbmt.framework.dao.enums.CompareTypeEnum;
import com.xbmt.framework.dao.enums.OrderTypeEnum;
import com.xbmt.framework.web.constants.WebConstants;
import com.xbmt.framework.web.controller.base.CoreController;
import com.xbmt.framework.web.omui.model.GridDataModel;
import com.xbmt.framework.web.utils.UploadFileOptUtil;
import com.xbmt.test.entity.TestEntity;
import com.xbmt.test.service.ITestEntityService;

/***
 * 测试 Spring MVC Web 框架搭建<p>
 * @author LingMin 
 * @date 2015年5月7日<br>
 * @version 1.0<br>
 */
@Controller
@RequestMapping(value="/test")
public class TestController extends CoreController{

	
	/***自动注入service***/
	@Autowired
	protected ITestEntityService testEntityService;
	
	/*****
	 * 指定首页跳转地址<p>
	 * @return String<p>
	 */
	protected  String getIndexURL(){
		return "";
	}
	/**
	 * @param request请求对象
	 * @param response 响应对象
	 * @return
	 */
	@RequestMapping()
	public String list(HttpServletRequest request , HttpServletResponse response,  ModelMap model) {
		String[] strlist = {"aaaa" , "bbbb" , "cccc" , "dddd"};
		//从数据库查询实体对象
		List<TestEntity> entityList =  testEntityService.list(null);
		model.addAttribute("strList", strlist);
		model.addAttribute("dataList", entityList);
		return "test"; 
	}
	
	/***
	 * <p>
	 * @return <p>
	 * String
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String list1(@PathVariable String id){
		logger.info("id=="+id);
		return "test"; 
	}
	
	
	/***
	 * 请求两个参数<p>
	 * @return <p>
	 * String
	 */
	@RequestMapping(value = "/{id}/and/{name}", method = RequestMethod.GET)
	public String list2(@PathVariable String id , @PathVariable String name){
		logger.info("id=="+id+" \t name="+name);
		
		org.springframework.oxm.xstream.XStreamMarshaller xs;
		//xs.
		//xs.setOmittedFields(arg0)
		
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
	public List list3(@PathVariable String id){
		logger.info("id=="+id+" \t name=");
//		List list = new ArrayList();
//		Map map = new HashMap();
//		map.put("number", "0001");
//		map.put("name", "张三");
//		map.put("type", "类型1");
//		map.put("address", "成都市");
//		list.add(map);
//		
//		map = new HashMap();
//		map.put("number", "0002");
//		map.put("name", "李思");
//		map.put("type", "类型2");
//		map.put("address", "成都市xxx");
//		list.add(map);
		
		List<TestEntity> list =  testEntityService.list(null);
		return list; 
	}
	
	
	/***
	 * 请求xml 对象<p>
	 * 【测试结果：该方法只支持json格式，支持xml请求数据格式】
	 * @return <p>
	 * String
	 */
	@ResponseBody
	@RequestMapping(value = "/data2/{id}", method = RequestMethod.GET )
	public ModelAndView list4(@PathVariable String id){
		logger.info("id=="+id+" \t name=");
		//ModelAndView mav = new ModelAndView(XML_VIEW_NAME); 
		List<TestEntity> list =  testEntityService.list(null);
		//String str = null;
		//str.length();
	    //mav.addObject(list);
	   // return mav;
	    
	    return new ModelAndView(WebConstants.XML_VIEW_NAME, "testEntityList", list);
	}
	
	
	/***
	 * 测试 Operamasks UI<p>
	 * @param request
	 * @param response
	 * @return <p>
	 * ModelAndView
	 */
	@RequestMapping(value = "/gotoOMUI", method = RequestMethod.GET )
	public ModelAndView gotoOMUI(HttpServletRequest request , HttpServletResponse response){
		ModelAndView mav = new ModelAndView("test/omUI");
		return mav;
	}

	/***
	 * 测试 easyui UI<p>
	 * @param request
	 * @param response
	 * @return <p>
	 * ModelAndView
	 */
	@RequestMapping(value = "/gotoEasyui", method = RequestMethod.GET )
	public ModelAndView gotoEasyUI(HttpServletRequest request , HttpServletResponse response){
		String pageName = request.getParameter("pageName");
		if(StringUtils.isEmpty(pageName)){
			pageName = "easyui";
		}
		ModelAndView mav = new ModelAndView(pageName);
		return mav;
	}
	
	
	/***
	 * 请求xml 对象<p>
	 * 【测试结果：该方法只支持json格式，支持xml请求数据格式】
	 * @return <p>
	 * String
	 */
	@ResponseBody
	@RequestMapping(value = "/listData", method = RequestMethod.GET)
	public ModelAndView listData(HttpServletRequest request , HttpServletResponse response){
		
		
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
	    
	    SQLCondition condition = new SQLCondition();//升序排列
	    condition.put(new OrderParameter("seq", OrderTypeEnum.ORDER_ASC));
	    
	    int total = testEntityService.count(condition, null);
		List<TestEntity> list =  testEntityService.list(start ,limit , condition);
		
		GridDataModel<TestEntity> model = new GridDataModel<TestEntity>();
        model.setRows(list);
        model.setTotal(total);
		
	    return new ModelAndView(WebConstants.XML_VIEW_NAME, "model", model);
	}
	
	
	
	
	
	
	
	/***
	 * 测试 编辑器使用<p>
	 * @param request
	 * @param response
	 * @return <p>
	 * ModelAndView
	 */
	@RequestMapping(value = "/gotoUeditor", method = RequestMethod.GET )
	public ModelAndView gotoUeditor(HttpServletRequest request , HttpServletResponse response){
		ModelAndView mav = new ModelAndView("ueditor_test");
		return mav;
	}
	
	/***
	 * 测试 编辑器使用<p>
	 * @param request
	 * @param response
	 * @return <p>
	 * ModelAndView
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST )
	public ModelAndView saveUeditor(HttpServletRequest request , HttpServletResponse response){
		ModelAndView mav = new ModelAndView("test");
		String content = request.getParameter("content");
		logger.info("content=="+content);
		mav.addObject("content", content);
		
		return mav;
	}
	

	/***
	 * 跳转上传文件<p>
	 * @param request
	 * @param response
	 * @return <p>
	 * ModelAndView
	 */
	@RequestMapping(value = "/gotoUpload", method = RequestMethod.GET )
	public ModelAndView gotoUploadFile(HttpServletRequest request , HttpServletResponse response){
		ModelAndView mav = new ModelAndView("upload_test");
		return mav;
	}
	
	/***
	 * 测试上传文件<p>
	 * @param request
	 * @param response
	 * @return <p>
	 * ModelAndView
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView upload(HttpServletRequest request , HttpServletResponse response){
		ModelAndView mav = new ModelAndView("upload_test");
		try {
			String fileName = ServletRequestUtils.getStringParameter(request, "fileName");
			//logger.info("fileName=="+fileName);
			
			List<Map<String, Object>> result = UploadFileOptUtil.uploadApache(request, null , null , new String[]{fileName});
			for(Map<String, Object> map : result){
				/***
				 *  map.put(UploadFileOptUtil.REALNAME, storeName);//上传文件名：重命名名称
					map.put(UploadFileOptUtil.STORENAME, storeName);
					map.put(UploadFileOptUtil.SIZE, new File(filePath).length());//文件大小
					map.put(UploadFileOptUtil.SUFFIX, UploadFileOptUtil.getFileNameSuffix(fileName));//文件后缀
					map.put(UploadFileOptUtil.CONTENTTYPE, "application/octet-stream");
					map.put(UploadFileOptUtil.CREATETIME, new Date());
				 */
				logger.info(UploadFileOptUtil.ORIGINALNAME+"="+map.get(UploadFileOptUtil.ORIGINALNAME));
				logger.info(UploadFileOptUtil.REALNAME+"="+map.get(UploadFileOptUtil.REALNAME));
				logger.info(UploadFileOptUtil.PARAMNAME+"="+map.get(UploadFileOptUtil.PARAMNAME));
				logger.info(UploadFileOptUtil.SIZE+"="+map.get(UploadFileOptUtil.SIZE));
				logger.info(UploadFileOptUtil.SUFFIX+"="+map.get(UploadFileOptUtil.SUFFIX));
				logger.info(UploadFileOptUtil.CONTENTTYPE+"="+map.get(UploadFileOptUtil.CONTENTTYPE));
				logger.info(UploadFileOptUtil.CREATETIME+"="+map.get(UploadFileOptUtil.CREATETIME));
				
			}
			mav.addObject("message", "上传文件成功!!");
		} catch (ServletRequestBindingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	
	/****
	 * [系统功能]列表数据加载后台业务处理<p>
	 * @return ModelAndView<p>
	 */
	@RequestMapping(value = "/functionAction", method = { RequestMethod.GET , RequestMethod.POST })
	public ModelAndView functionActionList(HttpServletRequest request, HttpServletResponse response) {
		// 初始化返回值
		java.util.List<SelectItem> itemList = new ArrayList<SelectItem>(); 
		for(int i = 0 ; i < 5 ; i++){
			itemList.add(new SelectItem("id"+i, "name"+i, null));
		}
		return new ModelAndView(WebConstants.XML_VIEW_NAME, "model", itemList);
	}
}
