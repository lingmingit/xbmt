package com.xbmt.framework.web.jackson.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.xbmt.framework.web.jackson.FilterPropertyHandler;
import com.xbmt.framework.web.jackson.impl.Jackson1JavassistFilterPropertyHandler;


/*****
 * 用于解决 动态设置 json字段 aop Controller方法拦截器<p>
 * @author LingMin 
 * @date 2015-8-18<br>
 * @version 1.0<br>
 */
@Component
@Aspect
public class IgnorePropertyAspect {
	/** 日志书写对象**/
	protected org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());

	/***
	 * 方法切入点 匹配<p>
	 * com.hgmk.ngwx.wx.controller
	 *  <p>
	 * void
	 */
    // @Pointcut("execution(* com.hgmk.ngwx.wx.*.*(..))")//测试不起作用，网上解释说Spring mvc 的Controller被代理了
	//@Pointcut("org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter.handle(..)")//抛异常：java.lang.IllegalArgumentException: error at ::0 can't find referenced pointcut handle
	@Pointcut("execution(* com.hgmk.ngwx.wx.controller.*.list(..))")// 测试可以
    private void anyMethod() {
    	logger.info(" --------  @Pointcut('execution(* com.hgmk.ngwx.wx.*.*(..))') -------- ");
    }

    /***
     * <p>
     * @param pjp
     * @return
     * @throws Throwable <p>
     * Object
     */
    @Around("anyMethod()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object returnVal = pjp.proceed(); // 返回源结果
        try {
        	//该 JavassistFilterPropertyHandler 用于 Jackson 2.x版本
            //FilterPropertyHandler filterPropertyHandler = new JavassistFilterPropertyHandler(true);
        	FilterPropertyHandler filterPropertyHandler = new Jackson1JavassistFilterPropertyHandler(true);
            Method method = ((MethodSignature) pjp.getSignature()).getMethod();
            returnVal = filterPropertyHandler.filterProperties(method, returnVal);
        } catch (Exception e) {
        	logger.error(e);
            e.printStackTrace();
        }
        return returnVal;
    }

    
    @AfterThrowing(pointcut = "anyMethod()", throwing = "e")
    public void doAfterThrowing(Exception e) {
    	logger.info(" -------- AfterThrowing -------- ");
    }
}