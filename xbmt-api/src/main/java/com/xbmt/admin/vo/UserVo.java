package com.xbmt.admin.vo;


/***
 * 用户信息Vo实体对象
 * @author admin
 *
 */
public class UserVo {

	//用户id
	private String id;
	//用户名
	private String username;
	//用户密码
	private String password;
	//用户token
	private String token;
	//邀请码
	private String promocode;
	//授权码
	private String authKey;
	//手机号码
	private String mobile;
	//角色
	private String roles;
	
	public UserVo(){}
	
	
	public UserVo(String id , String username , String password , String token){
		this.id = id;
		this.username = username;
		this.password = password;
		this.token = token;
	}
	
	public UserVo(String id , String username , String password , String token , String mobile){
		this.id = id;
		this.username = username;
		this.password = password;
		this.token = token;
		this.mobile = mobile;
	}
	
	public UserVo(String id , String username , String password , String token , String mobile , String roles){
		this.id = id;
		this.username = username;
		this.password = password;
		this.token = token;
		this.mobile = mobile;
		this.roles  = roles;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}


	/**
	 * @return the promocode
	 */
	public String getPromocode() {
		return promocode;
	}


	/**
	 * @param promocode the promocode to set
	 */
	public void setPromocode(String promocode) {
		this.promocode = promocode;
	}


	/**
	 * @return the authKey
	 */
	public String getAuthKey() {
		return authKey;
	}


	/**
	 * @param authKey the authKey to set
	 */
	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}


	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}


	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	/**
	 * @return the roles
	 */
	public String getRoles() {
		return roles;
	}


	/**
	 * @param roles the roles to set
	 */
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	
	
}
