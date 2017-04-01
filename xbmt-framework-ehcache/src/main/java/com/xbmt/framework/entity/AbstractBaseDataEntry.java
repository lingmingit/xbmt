/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.framework.entity;

import javax.persistence.Column;

import com.xbmt.framework.entity.base.Core;

/** 
 * 分录基类实体定义<p>
 * @author LingMin 
 * @date 2015-10-29<br>
 * @version 1.0<br>
 */
@javax.persistence.MappedSuperclass
public abstract class AbstractBaseDataEntry extends Core {

	/** 系统生成版本编号 **/
	private static final long serialVersionUID = -8291448841185345403L;
	/**序列号**/
	@javax.persistence.Column
	protected Integer seq;
	/** 备注信息 **/
	@Column(length=250)
	protected java.lang.String description;
	
	/**
	 * 获取分录序列号<p>
	 * @return 分录序列号<br>
	 */
	public Integer getSeq() {
		return seq;
	}
	
	/**
	 * 设置分录序列号<p>
	 * @param seq 分录序列号<br>
	 */
	public void setSeq(Integer seq) {
		this.seq = seq;
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
