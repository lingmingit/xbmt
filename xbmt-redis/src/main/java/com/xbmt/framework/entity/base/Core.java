package com.xbmt.framework.entity.base;


/** 
 * 实体基类【id主键】<p>
 * @author LingMin 
 * @date 2016-08-25<br>
 * @version 1.0<br>
 */
public abstract class Core implements java.io.Serializable {
	/** 系统生成版本编号 **/
	private static final long serialVersionUID = 6801636169153045886L;
	
	/** 日志书写对象**/
	protected org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());
	
	/** 关键字 **/
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

	/**
	 * @param 日志书写对象 the logger to set
	 */
	public void setLogger(org.apache.log4j.Logger logger) {
		this.logger = logger;
	}

	
	
}

