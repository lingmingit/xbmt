/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xbmt.service.DataBaseEhcacheServiceImpl;
import com.xbmt.sys.dao.IFunctionActionDao;
import com.xbmt.sys.entity.FunctionAction;
import com.xbmt.sys.service.IFunctionActionService;

/** 
 * 系统操作功能信息实体类 业务操作接口实现类<p>
 * @author LingMin 
 * @date 2015-10-29<br>
 * @version 1.0<br>
 */
@Service("sys.functionActionService")
public class FunctionActionServiceImpl extends DataBaseEhcacheServiceImpl<FunctionAction, String> implements IFunctionActionService {

	/***数据库到操作接口类**/
	protected IFunctionActionDao functionActionDao;
	
	
	/***
	 * 构造方法 注入数据库操作接口类
	 * @param dataBaseDao
	 */
	@Autowired(required=true)
	public FunctionActionServiceImpl(IFunctionActionDao functionActionDao) {
		super(functionActionDao);
		this.functionActionDao = functionActionDao;
	}


	


}
