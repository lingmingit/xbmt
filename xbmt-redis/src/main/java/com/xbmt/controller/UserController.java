/**
 * 
 */
package com.xbmt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xbmt.common.CommonUtils;
import com.xbmt.common.ReflectionUtils;
import com.xbmt.common.StringUtils;
import com.xbmt.common.security.EncryptUtils;
import com.xbmt.framework.controller.BaseController;
import com.xbmt.framework.entity.sys.SysUser;
import com.xbmt.service.IUserService;

/**
 * 用户Controller<p>
 * @author LingMin 
 * @date 2016-08-25<br>
 * @version 1.0<br>
 */
@Controller
@RequestMapping(value="/admin/user")
public class UserController extends BaseController{

	 @Autowired
	 private IUserService userService;
	
	 /**
     * 用户list
     * @param model 
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(Model model, HttpServletRequest request, HttpServletResponse response) {
    	Map map = new HashMap();
    	//map.put("test", "test1");
    	List<SysUser> userList = userService.getList(map);
    	model.addAttribute("userList", userList);
    	
        return "admin/user/userList";
    }

    
    /**
     * 用户list
     * @param model 
     * @return
     */
    @RequestMapping(value = "/list2")
    public String list2(Model model, HttpServletRequest request, HttpServletResponse response) {
    	String numbers = request.getParameter("numbers");
    	List<SysUser> userList = userService.getList2(numbers);
    	model.addAttribute("userList", userList);
        return "admin/user/userList";
    }
    
    /**
     * 用户查看
     * @param model 
     * @return
     */
    @RequestMapping(value = "/view/{id}")
    public String view(@PathVariable String id , Model model, HttpServletRequest request, HttpServletResponse response ) {
    	SysUser user = userService.get(id);
    	model.addAttribute("data", user);
        return "admin/user/userView";
    }
    
    /**
     * 用户编辑
     * @param model 
     * @return
     */
    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable String id , Model model, HttpServletRequest request, HttpServletResponse response ) {
    	SysUser user = userService.get(id);
    	
    	model.addAttribute("pageStatus", "EDIT");
    	model.addAttribute("data", user);
        return "admin/user/userEdit";
    }
    
    /**
     * 用户add
     * @param model 
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(Model model, HttpServletRequest request, HttpServletResponse response ) {
    	model.addAttribute("pageStatus", "ADDNEW");
    	
        return "admin/user/userEdit";
    }
    
    
    /**
     * 用户保存
     * @param model 
     * @return
     */
    @RequestMapping(value = "/save")
    public String save(@ModelAttribute SysUser data , Model model, HttpServletRequest request, HttpServletResponse response ) {
    	if(StringUtils.isEmpty(data.getId())){//新增
			SysUser temp = this.userService.getUserByAccount(data.getNumbers() , data.getId());
			if(CommonUtils.isEmptyObject(temp) || StringUtils.isEmpty(temp.getId())){
				//用户新增时 对密码进行加密处理
				data.setPassword(EncryptUtils.md5Encode(data.getPassword()));
				//设置用户实体字段默认值
				this.userService.add(data);
				request.getSession(false).setAttribute("message", "【"+data.getNumbers()+"】保存成功!!");
			}else{
				model.addAttribute("data", data);
				request.getSession(false).setAttribute("message", "【"+data.getNumbers()+"】已经存在，不能保存!!");
				
				return "admin/user/userEdit";
			}
		}else{
			SysUser oldUser = this.userService.get(data.getId());
			//更新数据实体对象所有属性 值
			ReflectionUtils.updateEntityObjectFields(oldUser , data , null , new String[]{"id"} );
			this.userService.update(oldUser);
		}
        return "redirect:/admin/user/list";
    }
}
