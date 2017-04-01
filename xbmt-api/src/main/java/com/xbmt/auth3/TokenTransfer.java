package com.xbmt.auth3;


/***
 * 定义 token vo 实体类
 * @author admin
 *
 */
public class TokenTransfer {

	 public void setToken(String token) {
	      this.token = token;
	   }

	   private String token;


	   public TokenTransfer(String token)
	   {
	      this.token = token;
	   }


	   public String getToken()
	   {
	      return this.token;
	   }
}
