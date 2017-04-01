/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.framework.web.controller;

import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xbmt.common.CommonUtils;
import com.xbmt.common.StringUtils;
import com.xbmt.framework.dao.common.SQLCondition;
import com.xbmt.framework.entity.base.Core;
import com.xbmt.framework.service.base.ICoreBaseService;
import com.xbmt.framework.web.constants.WebConstants;
import com.xbmt.framework.web.controller.base.CoreController;
import com.xbmt.framework.web.omui.model.GridDataModel;
import com.xbmt.framework.web.utils.PageUtils;

/** 
 * list 列表 后台处理 Controller基类<p>
 * @author LingMin 
 * @date 2015-10-27<br>
 * @version 1.0<br>
 */
public abstract class CoreListController<E extends Core, ID extends java.io.Serializable> extends CoreController {
	
	
	/*****当前实体class类型****/
	protected Class<E> entityClass = null;
	
	/***
	 * 基础资料业务操作接口类<p>
	 * @return IDataBaseService <p>
	 */
	protected abstract ICoreBaseService<E , ID> getCoreBaseService();

	
	/****
	 * 指定 列表查询 条件对象<p>
	 * @param request
	 * @return <p>
	 * SQLCondition
	 */
	protected SQLCondition getSeachSQLCondition(HttpServletRequest request){
		return null;
	}
	/*****
	 * 指定首页跳转地址<p>
	 * @return String<p>
	 */
	protected abstract String getIndexURL();
	
	/****
	 * 调整列表首页界面<p>
	 * @return String<p>
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request , HttpServletResponse response) {
		return this.getIndexURL();
	}
	
	
	/****
	 * 列表数据加载后台业务处理<p>
	 * @return ModelAndView<p>
	 */
	@RequestMapping(value = "/list", method = {RequestMethod.GET , RequestMethod.POST})
	public ModelAndView list(HttpServletRequest request , HttpServletResponse response) {
		String pageStr = request.getParameter("page");
	    String limitStr = request.getParameter("rows");
	    logger.info("pageStr="+pageStr+" \t limitStr="+limitStr);
	    int page = 1;
	    if(StringUtils.isNotEmpty(pageStr)){
	    	page = Integer.valueOf(pageStr);
	    }
	    int limit = 10;
	    if(StringUtils.isNotEmpty(limitStr)){
	    	limit = Integer.valueOf(limitStr);
	    }
	    //列表查询 条件对象
		SQLCondition condition = this.getSeachSQLCondition(request);
		if(CommonUtils.isEmptyObject(condition)){
			condition = new SQLCondition();
		}
		//设置实体名称 ,用于缓存使用，缓存方法根据该参数进行识别  如：com.xbmt.TestEntity，否则第二个实体缓存不能识别
		condition.setEntityName(this.getEntityClass().getName());
		// 查询总记录数
		int totalRowCount = this.getCoreBaseService().count(condition, null);
		int start = PageUtils.getRowIndex(page, limit);//计算分页起始行
		List<E> list = this.getCoreBaseService().list(start, limit, condition);
		GridDataModel<E> model = new GridDataModel<E>();
        model.setRows(list);
        model.setTotal(totalRowCount);
		return new ModelAndView(WebConstants.XML_VIEW_NAME, "model", model);
	}
	
	
	
	/***
	 * 获取当前Controller 泛型 定义type 的class类型
	 * @param index 定义type索引位置
	 * @return 返回泛型class type类型
	 */
	public Class<?> getControllerTypeClass(int index){
		java.lang.reflect.Type type = getClass().getGenericSuperclass();
		Type[] types = ((java.lang.reflect.ParameterizedType) type).getActualTypeArguments();
		if(index >= types.length){
			index = 0; 
		}
		return (Class<?>)types[index];
	}
	
	
	/**
	 * 获取泛型对应实体的Class对象<p>
	 * @return 泛型对应实体的Class对象<br>
	 */
	public Class<E> getEntityClass() {
		if (entityClass == null) {
			entityClass = (Class<E>)this.getControllerTypeClass(0);
		}
		return entityClass;
	}
}
