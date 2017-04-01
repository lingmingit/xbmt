package com.xbmt.admin.vo;


/***
 * 结果vo 对象
 * @author admin
 *
 */
public class HandleResultVo {

	
	private int code ;
	
	private String message = null;
	
	private Object entity = null;

	private Object entity2 = null;
	
	
	public HandleResultVo(){}
	
	public HandleResultVo(int code , String message){
		this.code = code;
		this.message = message;
	}
	
	public HandleResultVo(int code , String message , Object entity){
		this.code = code;
		this.message = message;
		this.entity = entity;
	}
	
	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the entity
	 */
	public Object getEntity() {
		return entity;
	}

	/**
	 * @param entity the entity to set
	 */
	public void setEntity(Object entity) {
		this.entity = entity;
	}

	/**
	 * @return the entity2
	 */
	public Object getEntity2() {
		return entity2;
	}

	/**
	 * @param entity2 the entity2 to set
	 */
	public void setEntity2(Object entity2) {
		this.entity2 = entity2;
	}
	
	
	
	
	
	
}
