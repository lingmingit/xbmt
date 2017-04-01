/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.framework.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Transient;
import org.codehaus.jackson.annotate.JsonProperty;

/** <p>
 * @author LingMin 
 * @date 2015-10-29<br>
 * @version 1.0<br>
 */
@javax.persistence.MappedSuperclass
public abstract class AbstractTreeData extends AbstractDataBase {

	/** 系统生成版本编号 **/
	private static final long serialVersionUID = -705039093796156974L;
	/** 所属层级 **/
	@Column(length=2)
	protected Integer levels;
	/** 长编码 **/
	@Column(length=200)
	protected String longNumber;
	/** 是否子节点 **/
	@Column(length=1, nullable=false)
	protected java.lang.Boolean isLeaf = false;
	
	
	/*** 子菜单 用于前台列表 显示使用 children  ***/
	@JsonProperty("children")
	@Transient
	private List children;
	
	/**
	 * 获取长编码<p>
	 * @return longNumber 长编码 <br>
	 */
	public String getLongNumber() {
		return longNumber;
	}
	
	/**
	 * 设置长编码<p>
	 * @param longNumber 长编码<br>
	 */
	public void setLongNumber(String longNumber) {
		this.longNumber = longNumber;
	}
	
	/**
	 * 获取所属层级<p>
	 * @return level 所属层级<br>
	 */
	public Integer getLevels() {
		return levels;
	}
	
	/**
	 * 设置所属层级<p>
	 * @param level 所属层级<br>
	 */
	public void setLevels(Integer levels) {
		this.levels = levels;
	}
	
	/**
	 * 查看是否为子节点(是 true,否 false)<p>
	 * @return isLeaf 是否子节点<br>
	 */
	public java.lang.Boolean getIsLeaf() {
		return isLeaf;
	}
	
	/**
	 * 设置是否子节点(是 true,否 false)<p>
	 * @param isLeaf 是否子节点<br>
	 */
	public void setIsLeaf(java.lang.Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	
	/**
	 * 获取子菜单用于前台列表显示使用children<p>
	 * @return  children  子菜单用于前台列表显示使用children<br>
	 */
	public List getChildren() {
		return children;
	}

	/**
	 * 设置子菜单用于前台列表显示使用children<p>
	 * @param  children  子菜单用于前台列表显示使用children<br>
	 */
	public void setChildren(List children) {
		this.children = children;
	}
	
}
