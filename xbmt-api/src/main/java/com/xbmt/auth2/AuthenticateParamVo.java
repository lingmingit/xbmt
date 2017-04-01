package com.xbmt.auth2;


/***
 * 认证参数配置
 * @author admin
 *
 */
public class AuthenticateParamVo {

	
	//响应认证头名称  获取token
	private String respAuthHeaderKey;
	//请求认证头 名称  请求资源
	private String reqAuthHeaderKey;
	//认证摘要名称
	private String realmName;
	//计算签名key
	private String key;
	//失效时间 单位秒
	private int nonceValiditySeconds;
	//摘要分隔符
	private  String splitChar;
	//认证方式
	private String qop;
	//计算 base64签名值
	private String nonce;
	//计数器
	private String noncecount;
	//
	private String opaque;
	//
	private String cnonce;
	
	//认证摘要 字符串 Digest 
	private String authDigest;
	
	
	
	
	
	
	
	public AuthenticateParamVo(){}
	
	/***
	 * 带参数构造方法  初始化参数
	 * @param respAuthHeaderKey
	 * @param reqAuthHeaderKey
	 * @param realmName
	 * @param key
	 * @param splitChar
	 * @param qop
	 * @param nonce
	 */
	public AuthenticateParamVo(String respAuthHeaderKey , String reqAuthHeaderKey , String realmName , String key , int nonceValiditySeconds, String splitChar , String qop , String nonce , String authDigest){
		this.respAuthHeaderKey = respAuthHeaderKey;
		this.reqAuthHeaderKey = reqAuthHeaderKey;
		this.realmName = realmName;
		this.key = key;
		this.nonceValiditySeconds = nonceValiditySeconds;
		this.splitChar = splitChar;
		this.qop = qop;
		this.nonce = nonce;
		this.authDigest = authDigest;
	}
	
	
	
	
	
	/**
	 * @return the respAuthHeaderKey
	 */
	public String getRespAuthHeaderKey() {
		return respAuthHeaderKey;
	}

	/**
	 * @param respAuthHeaderKey the respAuthHeaderKey to set
	 */
	public void setRespAuthHeaderKey(String respAuthHeaderKey) {
		this.respAuthHeaderKey = respAuthHeaderKey;
	}

	/**
	 * @return the reqAuthHeaderKey
	 */
	public String getReqAuthHeaderKey() {
		return reqAuthHeaderKey;
	}

	/**
	 * @param reqAuthHeaderKey the reqAuthHeaderKey to set
	 */
	public void setReqAuthHeaderKey(String reqAuthHeaderKey) {
		this.reqAuthHeaderKey = reqAuthHeaderKey;
	}

	/**
	 * @return the realmName
	 */
	public String getRealmName() {
		return realmName;
	}
	/**
	 * @param realmName the realmName to set
	 */
	public void setRealmName(String realmName) {
		this.realmName = realmName;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the splitChar
	 */
	public String getSplitChar() {
		return splitChar;
	}
	/**
	 * @param splitChar the splitChar to set
	 */
	public void setSplitChar(String splitChar) {
		this.splitChar = splitChar;
	}
	/**
	 * @return the qop
	 */
	public String getQop() {
		return qop;
	}
	/**
	 * @param qop the qop to set
	 */
	public void setQop(String qop) {
		this.qop = qop;
	}
	/**
	 * @return the nonce
	 */
	public String getNonce() {
		return nonce;
	}
	/**
	 * @param nonce the nonce to set
	 */
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	/**
	 * @return the nonceValiditySeconds
	 */
	public int getNonceValiditySeconds() {
		return nonceValiditySeconds;
	}

	/**
	 * @param nonceValiditySeconds the nonceValiditySeconds to set
	 */
	public void setNonceValiditySeconds(int nonceValiditySeconds) {
		this.nonceValiditySeconds = nonceValiditySeconds;
	}

	/**
	 * @return the authDigest
	 */
	public String getAuthDigest() {
		return authDigest;
	}

	/**
	 * @param authDigest the authDigest to set
	 */
	public void setAuthDigest(String authDigest) {
		this.authDigest = authDigest;
	}

	/**
	 * @return the noncecount
	 */
	public String getNoncecount() {
		return noncecount;
	}

	/**
	 * @param noncecount the noncecount to set
	 */
	public void setNoncecount(String noncecount) {
		this.noncecount = noncecount;
	}

	/**
	 * @return the opaque
	 */
	public String getOpaque() {
		return opaque;
	}

	/**
	 * @param opaque the opaque to set
	 */
	public void setOpaque(String opaque) {
		this.opaque = opaque;
	}

	/**
	 * @return the cnonce
	 */
	public String getCnonce() {
		return cnonce;
	}

	/**
	 * @param cnonce the cnonce to set
	 */
	public void setCnonce(String cnonce) {
		this.cnonce = cnonce;
	}
	
	
}
