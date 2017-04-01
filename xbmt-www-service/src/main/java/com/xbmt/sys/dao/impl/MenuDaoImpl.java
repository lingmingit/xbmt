package com.xbmt.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.xbmt.framework.dao.impl.TreeDataDaoImpl;
import com.xbmt.sys.dao.IMenuDao;
import com.xbmt.sys.entity.Menu;



/***
 * 菜单数据库操作接口实现类<p>
 * @author LingMin 
 * @date 2015-10-29<br>
 * @version 1.0<br>
 */
@Repository("sys.menuDao")
public class MenuDaoImpl extends TreeDataDaoImpl<Menu, String, IMenuDao>  implements IMenuDao{

	
}
