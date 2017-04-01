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

import com.xbmt.service.TreeDataEhcacheServiceImpl;
import com.xbmt.sys.dao.IMenuDao;
import com.xbmt.sys.entity.Menu;
import com.xbmt.sys.service.IMenuService;

/**
 * 菜单业务操作service <p>
 * @author LingMin 
 * @date 2015-10-29<br>
 * @version 1.0<br>
 */
@Service("sys.menService")
public class MenuServiceImpl extends TreeDataEhcacheServiceImpl<Menu, String> implements IMenuService {

	/**数据库操作接口类**/
	protected IMenuDao menuDao;
	/***
	 * 构造方法注入 数据库操作接口类
	 * @param menuDao
	 */
	@Autowired(required=true)
	public MenuServiceImpl(IMenuDao menuDao) {
		super(menuDao);
		this.menuDao = menuDao;
	}


}
