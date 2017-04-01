package com.xbmt.framework.entity.base;

import com.xbmt.framework.entity.sys.SysUser;


/**
 * 实体核心基础抽象类
 * 创建人、创建时间、修改人、修改时间<p>
 * @author LingMin 
 * @date 2015-08-03<br>
 * @version 1.0<br>
 */
public abstract class BaseCore extends Core{

	/** 系统生成默认版本编号 **/
	private static final long serialVersionUID = 1286111570351869166L;
	/** 创建者 **/
	//@JsonBackReference
	protected SysUser creator;
	/** 创建时间 **/
	protected java.util.Date createTime;
	/** 修改者 **/
	//@JsonBackReference
	protected SysUser modifier;
	/** 修改时间 **/
	protected java.util.Date modifyTime;
	/** 备注信息 **/
	protected java.lang.String description;
	
	
	
	/**
	 * 获取创建者信息<p>
	 * @return 创建者<br>
	 */
	public SysUser getCreator() {
		return creator;
	}
	
	/**
	 * 设置创建者信息<p>
	 * @param creator 创建者<br>
	 */
	public void setCreator(SysUser creator) {
		this.creator = creator;
	}
	
	/**
	 * 获取创建时间<p>
	 * @return 创建时间<br>
	 */
	public java.util.Date getCreateTime() {
		return createTime;
	}
	
	/**
	 * 设置创建时间<p>
	 * @param createTime 创建时间<br>
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	
	/**
	 * 获取修改者信息<p>
	 * @return 修改者信息<br>
	 */
	public SysUser getModifier() {
		return modifier;
	}
	
	/**
	 * 设置修改者信息<p>
	 * @param modifier 修改者信息<br>
	 */
	public void setModifier(SysUser modifier) {
		this.modifier = modifier;
	}
	
	/**
	 * 获取修改时间<p>
	 * @return 修改时间<br>
	 */
	public java.util.Date getModifyTime() {
		return modifyTime;
	}
	
	/**
	 * 设置修改时间<p>
	 * @param modifyTime 修改时间<br>
	 */
	public void setModifyTime(java.util.Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	/**
	 * 获取描述信息<p>
	 * @return 描述信息<br>
	 */
	public java.lang.String getDescription() {
		return description;
	}

	/**
	 * 设置描述信息<p>
	 * @param description 描述信息<br>
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	
}
