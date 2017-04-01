package com.xbmt.sys.entity;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Entity;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.xbmt.framework.entity.AbstractDataBase;


/**
 * 系统模块信息实体信息类<p>  "creator" , 
 * @author LingMin 
 * @date 2015-10-15<br>
 * @version 1.0<br>
 */
@Entity 
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler", "modifier"})  
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate=true)
@Table(name="t_sys_modules")
public class Modules extends AbstractDataBase{
	/** 系统生成默认版本编号 **/
	private static final long serialVersionUID = 6865010129604962065L;
	/** 模块序号 **/
	@Column(length=2)
	private Integer seqNo;
	/** 模块URL **/
	@Column(length=50)
	private String moduleURL;
	/** 模块图标 **/
	@Column(length=50)
	private String moduleIcon;
	/** 所属系统 **/
	@Column(length=6)
	private String belongSystem;
	/** LOGO图标 **/
	@Column(length=50)
	private String moduleLogoIcon;
	
	/**
	 * 获取模块序号<p>
	 * @return 模块序号<br>
	 */
	public Integer getSeqNo() {
		return seqNo;
	}
	
	/**
	 * 设置模块序号<p>
	 * @param seqNo 模块序号<br>
	 */
	public void setSeqNo(Integer seqNo) {
		this.seqNo = seqNo;
	}
	
	/**
	 * 获取模块图标<p>
	 * @return 模块图标<br>
	 */
	public String getModuleIcon() {
		return moduleIcon;
	}
	
	/**
	 * 设置模块图标<p>
	 * @param moduleIcon 模块图标<br>
	 */
	public void setModuleIcon(String moduleIcon) {
		this.moduleIcon = moduleIcon;
	}
	
	/**
	 * 获取模块URL<p>
	 * @return 模块URL<br>
	 */
	public String getModuleURL() {
		return moduleURL;
	}
	
	/**
	 * 设置模块URL<p>
	 * @param moduleURL 模块URL<br>
	 */
	public void setModuleURL(String moduleURL) {
		this.moduleURL = moduleURL;
	}
	
	/**
	 * 获取所属系统信息<p>
	 * @return 所属系统信息<br>
	 */
	public String getBelongSystem() {
		return belongSystem;
	}

	/**
	 * 设置所属系统信息<p>
	 * @param belongSystem 所属系统信息<br>
	 */
	public void setBelongSystem(String belongSystem) {
		this.belongSystem = belongSystem;
	}

	/**
	 * 获取模块的LOGO图标<p>
	 * @return 模块的LOGO图标<br>
	 */
	public String getModuleLogoIcon() {
		return moduleLogoIcon;
	}

	/**
	 * 设置模块的LOGO图标<p>
	 * @param moduleLogoIcon 模块的LOGO图标<br>
	 */
	public void setModuleLogoIcon(String moduleLogoIcon) {
		this.moduleLogoIcon = moduleLogoIcon;
	}
}
