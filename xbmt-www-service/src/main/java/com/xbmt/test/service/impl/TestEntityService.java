/**
 * &lt;p&gt;
 * copyright &amp;copy; together 2014, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xbmt.framework.service.base.impl.CoreBaseServiceImpl;
import com.xbmt.test.dao.ITestEntityDao;
import com.xbmt.test.entity.TestEntity;
import com.xbmt.test.service.ITestEntityService;

/** 
 * 测试实体业务操作 接口实现类<p>
 * @author LingMin 
 * @date 2015-5-8<br>
 * @version 1.0<br>
 */
@Service("testEntityService")
public class TestEntityService extends CoreBaseServiceImpl<TestEntity, String> implements ITestEntityService {

	
	/***数据库操作接口类***/
	protected ITestEntityDao testEntityDao;
	
	/****
	 * 构造方法
	 * @param testEntityDao 数据库操作接口类
	 */
	@Autowired(required=true)
	public TestEntityService(ITestEntityDao testEntityDao) {
		super(testEntityDao);
	}


	
	
}
