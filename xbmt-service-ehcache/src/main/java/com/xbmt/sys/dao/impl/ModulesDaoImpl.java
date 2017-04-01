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
import com.xbmt.sys.dao.IModulesDao;
import com.xbmt.sys.entity.Modules;

/** 
 * 系统模块数据库操作接口实现类<p>
 * @author LingMin 
 * @date 2015-10-15<br>
 * @version 1.0<br>
 */
@Repository("sys.modulesDao")
public class ModulesDaoImpl extends DataBaseDaoImpl<Modules, String, IModulesDao> implements IModulesDao {


}
