/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.xbmt.framework.entity.AbstractBaseDataEntry;

/** 
 * 菜单对应的功能分录实体定义<p>
 * @author LingMin 
 * @date 2015-10-29<br>
 * @version 1.0<br>
 */
@Entity 
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler","parent"})  
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate=true)
@Table(name="t_sys_menuFunAction")
public class MenuFunAction extends AbstractBaseDataEntry {

	
	/**系统自动生成默认版本号**/
	private static final long serialVersionUID = -97080151009190851L;
	/** 菜单信息**/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private Menu parent ;
	/** 操作功能 **/
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private FunctionAction action;
	/** Action名称 **/
	@Column(length=30)
	private String actionName;
	/** 按钮 id 名称 **/
	@Column(length=30)
	private String buttonIdName;
	
	
	
	/**
	 * 获取菜单信息<p>
	 * @return  parent  菜单信息<br>
	 */
	public Menu getParent() {
		return parent;
	}
	/**
	 * 设置菜单信息<p>
	 * @param  parent  菜单信息<br>
	 */
	public void setParent(Menu parent) {
		this.parent = parent;
	}
	/**
	 * 获取操作功能<p>
	 * @return  action  操作功能<br>
	 */
	public FunctionAction getAction() {
		return action;
	}
	/**
	 * 设置操作功能<p>
	 * @param  action  操作功能<br>
	 */
	public void setAction(FunctionAction action) {
		this.action = action;
	}
	/**
	 * 获取Action名称<p>
	 * @return  actionName  Action名称<br>
	 */
	public String getActionName() {
		return actionName;
	}
	/**
	 * 设置Action名称<p>
	 * @param  actionName  Action名称<br>
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}
	/**
	 * 获取按钮id名称<p>
	 * @return  buttonIdName  按钮id名称<br>
	 */
	public String getButtonIdName() {
		return buttonIdName;
	}
	/**
	 * 设置按钮id名称<p>
	 * @param  buttonIdName  按钮id名称<br>
	 */
	public void setButtonIdName(String buttonIdName) {
		this.buttonIdName = buttonIdName;
	}
	
	
	
}
