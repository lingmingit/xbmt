package com.xbmt.framework.web.vo;



/** 
 * 处理结果、消息提示 vo实体<p>
 * @author LingMin 
 * @date 2015-09-25<br>
 * @version 1.0<br>
 */
public class JSONResultVo {

	/**处理结果编码  **/
	private String resultCode;
	/***处理结果flag true 成功| false 失败***/
	private Boolean resultFlag;
	/**处理结果消息**/
	private String resultMsg;
	/**异常名称**/
	private String exceptionName;
	/**异常信息**/
	private String exceptionMsg;
	/**关键参数***/
	private String keyParam;
	/**实体对象***/
	private Object resultObj;
	
	/***
	 * 默认构造方法
	 */
	public JSONResultVo(){
	}
	
	/***
	 * 带参数构造方法
	 * @param resultCode 处理结果编码
	 * @param resultFlag 处理结果flag true 成功| false 失败
	 * @param resultMsg *处理结果消息
	 */
	public JSONResultVo(String resultCode , Boolean resultFlag , String resultMsg){
		this.resultCode = resultCode;
		this.resultFlag = resultFlag;
		this.resultMsg = resultMsg;
	}
	
	/***
	 * 带参数构造方法
	 * @param resultCode 处理结果编码
	 * @param resultFlag 处理结果flag true 成功| false 失败
	 * @param resultMsg *处理结果消息
	 * @param exceptionName 异常名称
	 * @param exceptionMsg 异常信息
	 */
	public JSONResultVo(String resultCode , Boolean resultFlag , String resultMsg , String exceptionName , String exceptionMsg){
		this.resultCode = resultCode;
		this.resultFlag = resultFlag;
		this.resultMsg = resultMsg;
		this.exceptionName = exceptionName;
		this.exceptionMsg = exceptionMsg;
	}
	
	/**
	 * 获取处理结果编码<p>
	 * @return  resultCode  处理结果编码<br>
	 */
	public String getResultCode() {
		return resultCode;
	}
	/**
	 * 设置处理结果编码<p>
	 * @param  resultCode  处理结果编码<br>
	 */
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	/**
	 * 获取处理结果flag<p>
	 * @return  resultFlag  处理结果flag<br>
	 */
	public Boolean getResultFlag() {
		return resultFlag;
	}
	/**
	 * 设置处理结果flag<p>
	 * @param  resultFlag  处理结果flag<br>
	 */
	public void setResultFlag(Boolean resultFlag) {
		this.resultFlag = resultFlag;
	}
	/**
	 * 获取处理结果消息<p>
	 * @return  resultMsg  处理结果消息<br>
	 */
	public String getResultMsg() {
		return resultMsg;
	}
	/**
	 * 设置处理结果消息<p>
	 * @param  resultMsg  处理结果消息<br>
	 */
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	/**
	 * 获取异常名称<p>
	 * @return  exceptionName  异常名称<br>
	 */
	public String getExceptionName() {
		return exceptionName;
	}
	/**
	 * 设置异常名称<p>
	 * @param  exceptionName  异常名称<br>
	 */
	public void setExceptionName(String exceptionName) {
		this.exceptionName = exceptionName;
	}
	/**
	 * 获取异常信息<p>
	 * @return  exceptionMsg  异常信息<br>
	 */
	public String getExceptionMsg() {
		return exceptionMsg;
	}
	/**
	 * 设置异常信息<p>
	 * @param  exceptionMsg  异常信息<br>
	 */
	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}

	/**
	 * 获取关键参数<p>
	 * @return  keyParam  关键参数<br>
	 */
	public String getKeyParam() {
		return keyParam;
	}

	/**
	 * 设置关键参数<p>
	 * @param  keyParam  关键参数<br>
	 */
	public void setKeyParam(String keyParam) {
		this.keyParam = keyParam;
	}

	/**
	 * 获取实体对象<p>
	 * @return  resultObj  实体对象<br>
	 */
	public Object getResultObj() {
		return resultObj;
	}

	/**
	 * 设置实体对象<p>
	 * @param  resultObj  实体对象<br>
	 */
	public void setResultObj(Object resultObj) {
		this.resultObj = resultObj;
	}
	
	
}
