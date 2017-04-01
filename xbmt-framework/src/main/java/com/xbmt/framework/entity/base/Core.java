package com.xbmt.framework.entity.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/** 
 * JPA实体基类【id主键】<p>
 * @author LingMin 
 * @date 2015-5-8<br>
 * @version 1.0<br>
 */
@javax.persistence.MappedSuperclass
public abstract class Core implements java.io.Serializable {
	/** 系统生成版本编号 **/
	private static final long serialVersionUID = 6801636169153045886L;
	
	/** 日志书写对象**/
	@Transient
	protected org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());
	
	/** 关键字 **/
	@Id @Column(length=32, nullable=false)
	@GenericGenerator(name="idGenerator", strategy="uuid")
	@GeneratedValue(generator="idGenerator")
	protected java.lang.String id;
	
	/**
	 * 获取关键字信息<p>
	 * @return 关键字<br>
	 */
	public java.lang.String getId() {
		return id;
	}

	/**
	 * 设置关键字信息<p>
	 * @param id 关键字<br>
	 */
	public void setId(java.lang.String id) {
		this.id = id;
	}

	
	
}

