/**
 * 
 */
package com.xbmt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试角色Controller<p>
 * @author LingMin 
 * @date 2016-08-25<br>
 * @version 1.0<br>
 */

@Controller
@RequestMapping(value = "/test")
public class TestController {

	/**
     *
     * @return
     */
    @RequestMapping(value = "/")
    public String contentIndex() {
        return "test/test_list";
    }
    
    /**
    *
    * @return
    */
   @RequestMapping(value = "/list")
   public String list() {
       return "test/test_list";
   }
}
