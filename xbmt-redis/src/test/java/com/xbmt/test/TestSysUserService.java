package com.xbmt.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xbmt.framework.entity.sys.SysUser;
import com.xbmt.service.IUserService;
import com.xbmt.test.base.SpringTestCase;



/**
 * 测试 用户业务service
 * @author LingMin 
 * @date 2016-08-25<br>
 * @version 1.0<br>
 */
public class TestSysUserService extends SpringTestCase{

	//用户业务操作service
	@Autowired  
    private IUserService userService;
	/***
	 * 测试  单个查询
	 * 测试成功
	 * @throws InterruptedException
	 */
	@Test  
    public void delete() throws InterruptedException{  
        String userId = "00001";//402896e4511e66bc01511e83508d0000
        userService.delete(userId);
    } 
	

	/***
	 * 测试  单个查询
	 * 测试成功
	 * @throws InterruptedException
	 */
	/*@Test  
    public void update() throws InterruptedException{  
        String userId = "00001";//402896e4511e66bc01511e83508d0000
        SysUser user = new SysUser();
        user.setId(userId);
        user.setNumbers("zhangsan_1");
        user.setName("张三1");
        SysUser updator = new SysUser();
        updator.setId("1");
        user.setModifier(updator);
        user.setModifyTime(new Date());
        
        userService.update(user);
        if(user != null){
        	logger.info("id="+user.getId()+" \t numbers="+user.getNumbers()+" \t name="+user.getName());
        }
    } */
	
	
	/***
	 * 测试  单个查询
	 * 测试成功
	 * @throws InterruptedException
	 */
	/*@Test  
    public void getList() throws InterruptedException{  
        String userId = "1";//402896e4511e66bc01511e83508d0000
        SysUser user = userService.get(userId);
        if(user != null){
        	logger.info("id="+user.getId()+" \t numbers="+user.getNumbers()+" \t name="+user.getName());
        }
    } */
	
	
	/***
	 * 测试  查询方法
	 * 测试成功
	 * @throws InterruptedException
	 */
	/*@Test  
    public void getList() throws InterruptedException{  
        String numbers = "u";
        Map map = new HashMap();
        map.put("numbers", numbers);
        List<SysUser> userList = userService.getList(map);
        if(userList != null){
        	 logger.info("共有条数据：="+userList.size());
        	for(SysUser user : userList){
        		 logger.info("id="+user.getId()+" \t numbers="+user.getNumbers()+" \t name="+user.getName());
        	}
        }
    } */
	
	/***
	 * 测试  查询方法
	 * 测试成功
	 * @throws InterruptedException
	 */
	@Test  
    public void getList2() throws InterruptedException{  
        String numbers = "%user%";
        List<SysUser> userList = userService.getList2(numbers);
        if(userList != null){
        	 logger.info("共有条数据：="+userList.size());
        	for(SysUser user : userList){
        		 logger.info("id="+user.getId()+" \t numbers="+user.getNumbers()+" \t name="+user.getName());
        	}
        }
    } 
	
	
	/***
	 * 测试  新增方法
	 * 测试成功
	 * @throws InterruptedException
	 */
	/*@Test  
    public void add() throws InterruptedException{  
		//org.apache.ibatis.type.JdbcType.TIMESTAMP
		//org.apache.ibatis.type.JdbcType.INTEGER
		*//***
		 * insert into t_sys_user (id, numbers, name, simpleName, isEnable, isSystem ,
    	creator_id , createTime , description ,
    	sex , birthday , age ,email ,password
		 *//*
        SysUser user = new SysUser();
        user.setId("00001");
        user.setNumbers("zhangsan");
        user.setName("张三");
        user.setSimpleName("zs");
        user.setIsEnable(true);
        user.setIsSystem(false);
        SysUser ceator = new SysUser();
        ceator.setId("1");
        user.setCreator(ceator);
        user.setCreateTime(new Date());
        user.setDescription("备注信息");
        user.setSex("1");
        user.setBirthday(new Date());
        user.setAge(12);
        user.setEmail("zhangsan@xbmt.net");
        user.setPassword("123456");
        
        userService.add(user);
        logger.info("user="+user);
    } */
	
	
}
