/**
 * 
 */
package com.xbmt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试2[aaa]角色Controller<p>
 * @author LingMin 
 * @date 2016-08-25<br>
 * @version 1.0<br>
 */
@RequestMapping(value = "/test2")
@Controller
public class Test2Controller {

	/**
     *
     * @return
     */
    @RequestMapping()
    public String contentIndex() {
        return "test2/test2_list";
    }
    
    /**
    *
    * @return
    */
   @RequestMapping(value = "/list")
   public String list() {
       return "test2/test2_list";
   }
}
