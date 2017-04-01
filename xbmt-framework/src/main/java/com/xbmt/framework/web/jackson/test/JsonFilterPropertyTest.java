package com.xbmt.framework.web.jackson.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import org.springframework.util.Assert;

import com.xbmt.framework.entity.sys.SysUser;
import com.xbmt.framework.web.jackson.FilterPropertyHandler;
import com.xbmt.framework.web.jackson.annotation.AllowProperty;
import com.xbmt.framework.web.jackson.annotation.IgnoreProperties;
import com.xbmt.framework.web.jackson.annotation.IgnoreProperty;
import com.xbmt.framework.web.jackson.helper.ThreadJacksonMixInHolder;
import com.xbmt.framework.web.jackson.impl.JavassistFilterPropertyHandler;
import com.xbmt.framework.web.jackson.util.EntityHelper;


/***
 * 测试 json 转换 动态过滤字段方法<p>
 * @author LingMin 
 * @date 2015-8-24<br>
 * @version 1.0<br>
 */
public class JsonFilterPropertyTest {

	
	@IgnoreProperties(@IgnoreProperty(pojo = SysUser.class, name = {"creator" , "modifier" ,  "createTime" , "modifyTime" , "description" , "isEnable" , "isSystem" , "isDelete" , "isLocked" , "isArchive" , "simpleName"}))
	@AllowProperty(pojo = SysUser.class, name = {"id" , "numbers" , "name"})
    public Collection<SysUser> listUsers() {

		SysUser user1 = new SysUser();
        user1.setId("1");
        user1.setNumbers("0001");
        user1.setName("用户1");
        
        SysUser user2 = new SysUser();
        user2.setId("2");
        user2.setNumbers("0002");
        user2.setName("用户2");
        
        SysUser user3 = new SysUser();
        user3.setId("3");
        user3.setName("用户3");
        user3.setNumbers("0003");

        
        Collection<SysUser> users = new ArrayList<SysUser>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        return users;
    }
	
	 @Test
    public void jsonTest() throws NoSuchMethodException, JsonProcessingException {
        FilterPropertyHandler filterPropertyHandler = new JavassistFilterPropertyHandler(false);
        Object object = listUsers();

        object = filterPropertyHandler.filterProperties(JsonFilterPropertyTest.class.getMethod("listUsers"), object);


        ObjectMapper mapper = ThreadJacksonMixInHolder.builderCodehausMapper();
        String json;
		try {
			json = mapper.writeValueAsString(object);
			  EntityHelper.print(json);
		        Assert.hasText(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
