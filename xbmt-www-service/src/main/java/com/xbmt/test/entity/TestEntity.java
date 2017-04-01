package com.xbmt.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.xbmt.framework.entity.base.Core;
import com.xbmt.framework.entity.sys.SysUser;

/**
 * 
 *  测试实体<p>
 *  
 * @author LingMin 
 * @date 2015-05-08<br>
 * @version 1.0<br>
 */
@XStreamAlias("testEntity")
//@XmlRootElement(name = "testEntity")
@Entity @Table(name = "t_tst_Test")
public class TestEntity extends Core {

	
	/**  **/
	@XStreamAlias("编码")
	@Column(length=150)
	private String number;
	
	
	/** **/
	@XStreamAlias("名称")
	@Column(length=350)
	private String name;
	
	/***序号**/
	@Column(length=5)
	private Integer seq;
	
	/** 创建者 **/
	@OneToOne(fetch=FetchType.LAZY) @JoinColumn(updatable=false)
	protected SysUser creator;
	/** 创建时间 **/
	@Column(updatable=false) @Temporal(TemporalType.TIMESTAMP)
	protected java.util.Date createTime;

	/** 备注信息 **/
	@Column(length=250)
	protected java.lang.String description;
	/**
	 * 获取<p>
	 * @return  number  <br>
	 */
	
	//@XmlElement
	public String getNumber() {
		return number;
	}

	/**
	 * 设置<p>
	 * @param  number  <br>
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * 获取<p>
	 * @return  name  <br>
	 */
	
	//@XmlElement
	public String getName() {
		return name;
	}

	/**
	 * 设置<p>
	 * @param  name  <br>
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取序号<p>
	 * @return  seq  序号<br>
	 */
	public Integer getSeq() {
		return seq;
	}

	/**
	 * 设置序号<p>
	 * @param  seq  序号<br>
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	/**
	 * 获取创建者<p>
	 * @return  creator  创建者<br>
	 */
	public SysUser getCreator() {
		return creator;
	}

	/**
	 * 设置创建者<p>
	 * @param  creator  创建者<br>
	 */
	public void setCreator(SysUser creator) {
		this.creator = creator;
	}

	/**
	 * 获取创建时间<p>
	 * @return  createTime  创建时间<br>
	 */
	public java.util.Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置创建时间<p>
	 * @param  createTime  创建时间<br>
	 */
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取备注信息<p>
	 * @return  description  备注信息<br>
	 */
	public java.lang.String getDescription() {
		return description;
	}

	/**
	 * 设置备注信息<p>
	 * @param  description  备注信息<br>
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
	}


	
	
}
