package com.xbmt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xbmt.common.StringUtils;
import com.xbmt.framework.controller.BaseController;
import com.xbmt.framework.utils.Result;

/**
 * 登录Controller<p>
 * @author LingMin 
 * @date 2016-08-25<br>
 * @version 1.0<br>
 */
@Controller
public class LoginController extends BaseController{

    

    /**
     * 内容首页
     *
     * @return
     */
    @RequestMapping(value = "/")
    public String contentIndex() {
        return "redirect:/index";
    }
    
    /**
     * website主页跳转
     * @param model
     * @return
     */
    @RequestMapping(value = "/index")
    public String contentIndex(Model model, HttpServletRequest request, HttpServletResponse response) {
    	//TODO:可控制根据不同终端访问不同的界面
//    	try {
//    		//移动端访问
//			if(check(request, response)) {
//				return "/website/indexm";
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
        return "website/index";
    }

    /**
     * 管理首页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin")
    public String adminIndex() {
        return "admin/index";
    }
    
    /**
     * 管理首页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin/index")
    public String adminIndex(Model model) {
        return "admin/index";
    }
    
//    /**
//     * GET 登录
//     *
//     * @param model
//     * @param request
//     * @return
//     */
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String logins(Model model, HttpServletRequest request) {
//        LOGGER.info("GET请求登录");
//        if (SecurityUtils.getSubject().isAuthenticated()) {
//            return "redirect:/admin/index";
//        }
//        return "/admin/login";
//    }

    /**
     * GET 登录
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/login", method = RequestMethod.GET)
    public String login(Model model, HttpServletRequest request) {
    	logger.info("GET请求登录");
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/admin/index";
        }
        return "/admin/login";
    }

    /**
     * POST 登录 shiro 写法
     *
     * @param username 用户名
     * @param password 密码
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    @ResponseBody
    public Result loginPost(String username, String password, HttpServletRequest request, Model model) {
    	logger.info("POST请求登录");
        Result result = new Result();
        if (StringUtils.isBlank(username)) {
            result.setMsg("用户名不能为空");
            return result;
        }
        if (StringUtils.isBlank(password)) {
            result.setMsg("密码不能为空");
            return result;
        }
        Subject user = SecurityUtils.getSubject();
        logger.info("SecurityUtils.getSubject().isAuthenticated()="+SecurityUtils.getSubject().isAuthenticated());
        //DigestUtils.
        password = DigestUtils.md5Hex(password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password.toCharArray());
        token.setRememberMe(true);
        try {
            user.login(token);
        } catch (UnknownAccountException e) {
        	logger.error("账号不存在：{}", e);
            result.setMsg("账号不存在");
            return result;
        } catch (DisabledAccountException e) {
        	logger.error("账号未启用：{}", e);
            result.setMsg("账号未启用");
            return result;
        } catch (IncorrectCredentialsException e) {
        	logger.error("密码错误：{}", e);
            result.setMsg("密码错误");
            return result;
        } catch (RuntimeException e) {
        	logger.error("未知错误,请联系管理员：{}", e);
            result.setMsg("未知错误,请联系管理员");
            return result;
        }
        result.setSuccess(true);
        return result;
    }

    /**
     * 未授权
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/unauth")
    public String unauth(Model model) {
        if (SecurityUtils.getSubject().isAuthenticated() == false) {
            return "redirect:admin/login";
        }
        return "unauth";
    }
    
    /**
     * 测试 shiro 权限的用法
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin/shiro", method = RequestMethod.GET)
    public String testShiro(Model model) {
        return "shiro";
    }

    /**
     * 退出
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/admin/logout")
    //@ResponseBody
    public String logout(HttpServletRequest request) {
    	logger.info("登出");
        Subject subject = SecurityUtils.getSubject();
        Result result = new Result();
        subject.logout();
//        result.setSuccess(true);
//        return result;
        return "redirect:admin/login";
    }
}
