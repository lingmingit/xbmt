package com.xbmt.auth3;

import java.util.Map;


/***
 * 定义 user vo 实体对象
 * @author admin
 *
 */
public class UserTransfer {
	 private final String username;

	   private final Map<String, Boolean> roles;


	   public UserTransfer(String userName, Map<String, Boolean> roles)
	   {
	      this.username = userName;
	      this.roles = roles;
	   }


	   public String getUsername()
	   {
	      return this.username;
	   }


	   public Map<String, Boolean> getRoles()
	   {
	      return this.roles;
	   }

}

