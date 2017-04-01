package com.xbmt.shiro;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.xbmt.framework.entity.sys.SysUser;
import com.xbmt.service.IUserService;

/**
 * @description：shiro权限认证
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
public class ShiroDbRealm extends AuthorizingRealm {

    private static Logger LOGGER = LoggerFactory.getLogger(ShiroDbRealm.class);

    @Autowired
    private IUserService userService;
   // @Autowired
   // private RoleService roleService;

    /**
     * Shiro登录认证(原理：用户提交 用户名和密码  --- shiro 封装令牌 ---- realm 通过用户名将密码查询返回 ---- shiro 自动去比较查询出密码和用户输入密码是否一致---- 进行登陆控制 )
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        LOGGER.info("Shiro开始登录认证");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        SysUser user = userService.findUserByLoginName(token.getUsername());
        // 账号不存在
        if (user == null) {
            return null;
        }
        // 账号未启用
        if (!user.getIsEnable()) {
            return null;
        }
        //List<Long> roleList = roleService.findRoleIdListByUserId(user.getId());
       // List<Long> roleList = new ArrayList<Long>();
       // roleList.add(1l);
        
        List<String> roleList = this.getRoleList(user.getNumbers());
        ShiroUser shiroUser = new ShiroUser(user.getId(), user.getNumbers(), user.getName(), roleList);
        // 认证缓存信息
        return new SimpleAuthenticationInfo(shiroUser, user.getPassword().toCharArray(), getName());

    }

    /**
     * Shiro权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {

        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        List<String> roleList = shiroUser.roleList;
//        Set<String> urlSet = new HashSet<String>();
//        Set<String> urlSet = Sets.newHashSet();
//        for (Long roleId : roleList) {
//            List<Map<Long, String>> roleResourceList = roleService.findRoleResourceListByRoleId(roleId);
//            if (roleResourceList != null) {
//                for (Map<Long, String> map : roleResourceList) {
//                    if (StringUtils.isNoneBlank(map.get("url"))) {
//                        urlSet.add(map.get("url"));
//                    }
//                }
//            }
//        }
        
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roleList);//添加角色 集合
        info.addStringPermissions(this.getPermSet(shiroUser.toString()));//添加权限集合
        return info;
    }
    
    /***
     * 测试角色 ，根据用户名 获取角色
     * @param username
     * @return
     */
    private List<String> getRoleList(String username){
    	
    	List<String> roleList = new ArrayList<String>();
    	if("user".equals(username)){
    		roleList.add("admin");
    		roleList.add("test");
    		roleList.add("aaa");
    	}else if("test".equals(username)){
    		roleList.add("test");
    	}else if("aaa".equals(username)){
    		roleList.add("aaa");
    	}
    	return roleList;
    }
    
    
    /***
     * 测试权限 ，根据用户名 获取不同的权限
     * @param username
     * @return
     */
    private Set<String> getPermSet(String username){
    	
    	Set<String> urlSet = new HashSet<String>();
    	if("user".equals(username)){
    		 urlSet.add("/test/list");
    		 urlSet.add("/test/add");
	         urlSet.add("/test/edit");
	         urlSet.add("/test/delete");
    	}else if("test".equals(username)){
    		urlSet.add("/test/list");
   		    urlSet.add("/test/add");
    	}else if("aaa".equals(username)){
    		 urlSet.add("/test/edit");
	         urlSet.add("/test/delete");
    	}
    	return urlSet;
    }
    
}
