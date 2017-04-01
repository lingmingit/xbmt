/**
 * &lt;p&gt;
 * copyright &amp;copy; together 2014, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.test.dao.impl;

import org.springframework.stereotype.Repository;

import com.xbmt.framework.dao.base.impl.CoreBaseDaoImpl;
import com.xbmt.test.dao.ITestEntityDao;
import com.xbmt.test.entity.TestEntity;

/** 
 * 测试实体 数据库操作接口实现类<p>
 * @author LingMin 
 * @date 2015-5-8<br>
 * @version 1.0<br>
 */
@Repository("testEntityDao")
public class TestEntityDaoImpl extends CoreBaseDaoImpl<TestEntity, String, ITestEntityDao> implements ITestEntityDao {

}
