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
import com.xbmt.sys.dao.IModulesDao;
import com.xbmt.sys.entity.Modules;
import com.xbmt.sys.service.IModulesService;

/** <p>
 * @author LingMin 
 * @date 2015-10-15<br>
 * @version 1.0<br>
 */
@Service("sys.modulesService")
public class ModulesServiceImpl extends DataBaseEhcacheServiceImpl<Modules, String> implements IModulesService {


	/***数据库操作dao接口***/
	protected IModulesDao modulesDao = null;

	/***
	 * 构造方法注入dao接口类
	 * @param modulesDao
	 */
	@Autowired(required=true)
	public ModulesServiceImpl(IModulesDao modulesDao){
		super(modulesDao);
		this.modulesDao = modulesDao;
		
	}
}
