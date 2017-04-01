/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.sys.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.xbmt.framework.entity.AbstractTreeData;

/** 
 * 菜单实体定义<p>
 * @author LingMin 
 * @date 2015-10-29<br>
 * @version 1.0<br>
 */
@Entity 
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler", "creator" , "modifier" , "parent"})  
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate=true)
@Table(name="t_sys_menu")
public class Menu extends AbstractTreeData {

	/** 系统生成默认版本编号 **/
	private static final long serialVersionUID = 5147878199605009511L;
	/** 绑定URL **/
	@Column(length=200)
	private String url;
	/** 上级菜单 **/
	@OneToOne @JoinColumn
	private Menu parent;
	/** 显示序号 **/
	@Column(length=2)
	private Integer seqNo;
	/** 备用URL **/
	@Column(length=100)
	private String backUrl;
	/** 所属模块 **/
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(nullable=false)
	private Modules module;
	/** 功能名称 **/
	@Column(length=100)
	private String functionName;
	/** 菜单图标 **/
	@Column(length=100)
	private String menuIcon;
	
	
	
	/** 分录信息  **/
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="parent")
	private java.util.List<MenuFunAction> entryList = new java.util.ArrayList<MenuFunAction>();

	/**
	 * 获取绑定URL<p>
	 * @return  url  绑定URL<br>
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 设置绑定URL<p>
	 * @param  url  绑定URL<br>
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取上级菜单<p>
	 * @return  parent  上级菜单<br>
	 */
	public Menu getParent() {
		return parent;
	}

	/**
	 * 设置上级菜单<p>
	 * @param  parent  上级菜单<br>
	 */
	public void setParent(Menu parent) {
		this.parent = parent;
	}

	/**
	 * 获取显示序号<p>
	 * @return  seqNo  显示序号<br>
	 */
	public Integer getSeqNo() {
		return seqNo;
	}

	/**
	 * 设置显示序号<p>
	 * @param  seqNo  显示序号<br>
	 */
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}

	/**
	 * 获取备用URL<p>
	 * @return  backUrl  备用URL<br>
	 */
	public String getBackUrl() {
		return backUrl;
	}

	/**
	 * 设置备用URL<p>
	 * @param  backUrl  备用URL<br>
	 */
	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	/**
	 * 获取所属模块<p>
	 * @return  module  所属模块<br>
	 */
	public Modules getModule() {
		return module;
	}

	/**
	 * 设置所属模块<p>
	 * @param  module  所属模块<br>
	 */
	public void setModule(Modules module) {
		this.module = module;
	}

	/**
	 * 获取功能名称<p>
	 * @return  functionName  功能名称<br>
	 */
	public String getFunctionName() {
		return functionName;
	}

	/**
	 * 设置功能名称<p>
	 * @param  functionName  功能名称<br>
	 */
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	/**
	 * 获取菜单图标<p>
	 * @return  menuIcon  菜单图标<br>
	 */
	public String getMenuIcon() {
		return menuIcon;
	}

	/**
	 * 设置菜单图标<p>
	 * @param  menuIcon  菜单图标<br>
	 */
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	/**
	 * 获取分录信息<p>
	 * @return  entryList  分录信息<br>
	 */
	public java.util.List<MenuFunAction> getEntryList() {
		return entryList;
	}

	/**
	 * 设置分录信息<p>
	 * @param  entryList  分录信息<br>
	 */
	public void setEntryList(java.util.List<MenuFunAction> entryList) {
		this.entryList = entryList;
	}

	
	
}
