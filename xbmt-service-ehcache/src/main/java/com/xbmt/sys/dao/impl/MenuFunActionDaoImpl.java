package com.xbmt.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.xbmt.framework.dao.impl.BaseEntryDaoImpl;
import com.xbmt.sys.dao.IMenuFunActionDao;
import com.xbmt.sys.entity.MenuFunAction;


/** 
 * 菜单分录 (菜单功能) 数据库操作接口实现类<p>
 * @author LingMin 
 * @date 2015-12-02<br>
 * @version 1.0<br>
 */
@Repository("sys.menuFunActionDao")
public class MenuFunActionDaoImpl extends
		BaseEntryDaoImpl<MenuFunAction, String, IMenuFunActionDao> implements
		IMenuFunActionDao {


}
