/**
 * 
 */
package com.xbmt.sys.service;

import java.util.List;

import com.xbmt.framework.service.IBaseEntryService;
import com.xbmt.sys.entity.MenuFunAction;

/** 
 * 菜单分录 (菜单功能) 业务操作接口实现类<p>
 * @author LingMin 
 * @date 2015-12-02<br>
 * @version 1.0<br>
 */
public interface IMenuFunActionService extends IBaseEntryService<MenuFunAction, String> {

	
	/***
	 * 自定义sql查询 分录集合
	 * @param parentId
	 * @return
	 */
	public List getJpqlEntryList(String parentId);
}
