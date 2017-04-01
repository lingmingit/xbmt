package com.xbmt.framework.entity.base;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.xbmt.common.CommonUtils;
import com.xbmt.common.StringUtils;
import com.xbmt.framework.entity.sys.SysUser;
import com.xbmt.framework.web.utils.WebCommonUtils;


/**
 * 实体核心基础抽象类
 * 创建人、创建时间、修改人、修改时间<p>
 * @author LingMin 
 * @date 2015-08-03<br>
 * @version 1.0<br>
 */
@javax.persistence.MappedSuperclass
public abstract class BaseCore extends Core{

	/** 系统生成默认版本编号 **/
	private static final long serialVersionUID = 1286111570351869166L;
	/** 创建者 **/
	//@JsonBackReference
	@OneToOne(fetch=FetchType.LAZY) @JoinColumn(updatable=false)
	protected SysUser creator;
	/** 创建时间 **/
	@Column(updatable=false) @Temporal(TemporalType.TIMESTAMP)
	protected java.util.Date createTime;
	/** 修改者 **/
	//@JsonBackReference
	@OneToOne(fetch=FetchType.LAZY) @JoinColumn
	protected SysUser modifier;
	/** 修改时间 **/
	@Column @Temporal(TemporalType.TIMESTAMP)
	protected java.util.Date modifyTime;
	/** 备注信息 **/
	@Column(length=250)
	protected java.lang.String description;
	
	/***由于 前台json转换 存在如下异常：
	 * 		jackson 报JsonMappingException: Direct self-reference leading to cycle
	 * 		Direct self-reference leading to cycle (through reference chain
	 * 分析原因为：创建人、修改人("creator" , "modifier") 转换json对象时，存在 双向引用导 致的无限递归（infinite recursion）问题。
	 *         jackson1.x 只能使用注解忽悠双向引用字段  @JsonBackReference | @JsonIgnore | @JsonIgnoreProperties 
	 *         jackson2.x 使用 New annotation: @JsonIdentityInfo
	 *         如： @JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
	 * 目前解决方法：先将 ("creator" , "modifier")通过注解忽悠， 再 增加非数据库字段(主要字段，如：创建人Id、创建名称、修改人Id、修改名称)，实体字段
	 * ***************/
	/***创建用户id****/
	@Transient
	protected String creatorId;
	/***创建用户名称****/
	@Transient
	protected String creatorName;
	/***创建用户账户****/
	@Transient
	protected String creatorNumber;
	
	/**修改用户id****/
	@Transient
	protected String modifierId;
	/***修改用户名称****/
	@Transient
	protected String modifierName;
	/***修改用户账户****/
	@Transient
	protected String modifierNumber;
	
	/***
	 * 保存实体时，执行该方法<p>
	 *  <p>
	 * void
	 */
	@PrePersist
    public void prePersist(){
		this.createTime = new Date();//创建时间
		this.creator = WebCommonUtils.getCurrentSysUser();//获取当前登陆用户
		this.modifyTime = new Date();//修改时间
		logger.info("@PrePersist=执行");
    }
     
	/***
	 * 修改实体时，执行该方法<p>
	 *  <p>
	 * void
	 */
    @PreUpdate
    public void preUpdate(){
    	this.modifyTime = new Date();//修改时间
    	this.modifier = WebCommonUtils.getCurrentSysUser();//获取当前登陆用户
    	logger.info("@PreUpdate=执行");
    }
	
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

	/**
	 * 获取由于前台json转换<p>
	 * @return  creatorId  由于前台json转换<br>
	 */
	public String getCreatorId() {
		if(CommonUtils.isNotEmptyObject(this.getCreator())){
			return this.creator.getId();
		}
		return creatorId;
	}

	/**
	 * 设置由于前台json转换<p>
	 * @param  creatorId  由于前台json转换<br>
	 */
	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	/**
	 * 获取创建用户名称<p>
	 * @return  creatorName  创建用户名称<br>
	 */
	public String getCreatorName() {
		if(CommonUtils.isNotEmptyObject(this.getCreator())){
			return this.creator.getName();
		}
		return creatorName;
	}

	/**
	 * 设置创建用户名称<p>
	 * @param  creatorName  创建用户名称<br>
	 */
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	/**
	 * 获取创建用户账户<p>
	 * @return  creatorNumber  创建用户账户<br>
	 */
	public String getCreatorNumber() {
		if(CommonUtils.isNotEmptyObject(this.getCreator())){
			return this.creator.getNumbers();
		}
		return creatorNumber;
	}

	/**
	 * 设置创建用户账户<p>
	 * @param  creatorNumber  创建用户账户<br>
	 */
	public void setCreatorNumber(String creatorNumber) {
		this.creatorNumber = creatorNumber;
	}

	/**
	 * 获取修改用户id<p>
	 * @return  modifierId  修改用户id<br>
	 */
	public String getModifierId() {
		if(CommonUtils.isNotEmptyObject(this.getModifier())){
			return this.modifier.getNumbers();
		}
		return modifierId;
	}

	/**
	 * 设置修改用户id<p>
	 * @param  modifierId  修改用户id<br>
	 */
	public void setModifierId(String modifierId) {
		this.modifierId = modifierId;
	}

	/**
	 * 获取修改用户名称<p>
	 * @return  modifierName  修改用户名称<br>
	 */
	public String getModifierName() {
		if(CommonUtils.isNotEmptyObject(this.getModifier())){
			return this.modifier.getName();
		}
		return modifierName;
	}

	/**
	 * 设置修改用户名称<p>
	 * @param  modifierName  修改用户名称<br>
	 */
	public void setModifierName(String modifierName) {
		this.modifierName = modifierName;
	}

	/**
	 * 获取修改用户账户<p>
	 * @return  modifierNumber  修改用户账户<br>
	 */
	public String getModifierNumber() {
		if(CommonUtils.isNotEmptyObject(this.getModifier())){
			return this.modifier.getName();
		}
		return modifierNumber;
	}

	/**
	 * 设置修改用户账户<p>
	 * @param  modifierNumber  修改用户账户<br>
	 */
	public void setModifierNumber(String modifierNumber) {
		this.modifierNumber = modifierNumber;
	}
	
	
}
