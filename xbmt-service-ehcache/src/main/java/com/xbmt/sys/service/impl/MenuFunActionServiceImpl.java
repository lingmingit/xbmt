package com.xbmt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xbmt.service.BaseEntryEhcacheServiceImpl;
import com.xbmt.sys.dao.IMenuFunActionDao;
import com.xbmt.sys.entity.MenuFunAction;
import com.xbmt.sys.service.IMenuFunActionService;


/** 
 * 菜单分录 (菜单功能) 业务操作接口实现实现类<p>
 * @author LingMin 
 * @date 2015-12-02<br>
 * @version 1.0<br>
 */
@Service("sys.menuFunActionService")
public class MenuFunActionServiceImpl extends BaseEntryEhcacheServiceImpl<MenuFunAction, String> implements
		IMenuFunActionService {
	/***数据库dao接口类***/
	protected IMenuFunActionDao menuFunActionDao;
	
	/***
	 * 构造方法注入数据库接口类
	 */
	@Autowired(required=true)
	public MenuFunActionServiceImpl(IMenuFunActionDao menuFunActionDao){
		super(menuFunActionDao);
		this.menuFunActionDao = menuFunActionDao;
	}
	
	/***
	 * 自定义sql查询 分录集合
	 * @param parentId
	 * @return
	 */
	public List getJpqlEntryList(String parentId){
		/**
		 * SELECT mfa.id , mfa.action_id , fa.name , mfa.actionName , mfa.buttonIdName , mfa.seq , mfa.description  FROM t_sys_menufunaction mfa 
			INNER JOIN t_sys_functionaction fa ON(fa.id = mfa.action_id)
			WHERE mfa.parent_id = '402896e4517b99da01517b9ae3a60000'
		 */
		StringBuffer jpql = new StringBuffer();
		jpql.append(" SELECT mfa.id , mfa.action.id  , fa.name , mfa.actionName , mfa.buttonIdName , mfa.seq , mfa.description  FROM MenuFunAction mfa , FunctionAction fa");
		jpql.append("  where fa.id = mfa.action.id");
		jpql.append(" and mfa.parent.id = ? ");
		return  this.menuFunActionDao.findByJPQL(jpql.toString() , new Object[]{parentId});
	}

}
