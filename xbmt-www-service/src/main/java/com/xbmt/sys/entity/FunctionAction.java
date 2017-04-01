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
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.xbmt.framework.entity.AbstractDataBase;

/** 
 * 系统操作功能信息实体类<p>
 * @author LingMin 
 * @date 2015-10-29<br>
 * @version 1.0<br>
 */
@Entity 
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler", "creator" , "modifier"})  
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate=true)
@Table(name="t_sys_functionAction")
public class FunctionAction extends AbstractDataBase {

	/** 系统生成默认版本编号 **/
	private static final long serialVersionUID = -588502205837456185L;
	/** Action名称 **/
	@Column(length=30)
	private String actionName;
	/** 按钮 id 名称 **/
	@Column(length=30)
	private String buttonId;
	
	/**
	 * 获取Action名称<p>
	 * @return Action名称<br>
	 */
	public String getActionName() {
		return actionName;
	}
	
	/**
	 * 设置Action名称<p>
	 * @param actionName Action名称<br>
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	/**
	 * 获取按钮id名称<p>
	 * @return  buttonId  按钮id名称<br>
	 */
	public String getButtonId() {
		return buttonId;
	}

	/**
	 * 设置按钮id名称<p>
	 * @param  buttonId  按钮id名称<br>
	 */
	public void setButtonId(String buttonId) {
		this.buttonId = buttonId;
	}

	
	
}
