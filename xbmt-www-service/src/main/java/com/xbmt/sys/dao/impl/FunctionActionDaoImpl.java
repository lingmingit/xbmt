/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.xbmt.framework.dao.impl.DataBaseDaoImpl;
import com.xbmt.sys.dao.IFunctionActionDao;
import com.xbmt.sys.entity.FunctionAction;

/** 
 *  系统操作功能信息实体类 数据库操作接口实现类<p>
 * @author LingMin 
 * @date 2015-10-29<br>
 * @version 1.0<br>
 */
@Repository("sys.functionActionDao")
public class FunctionActionDaoImpl extends DataBaseDaoImpl<FunctionAction, String, IFunctionActionDao> implements IFunctionActionDao{

}
