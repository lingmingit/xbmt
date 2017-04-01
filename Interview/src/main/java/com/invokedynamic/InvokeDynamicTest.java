/**
 * 
 */
package com.invokedynamic;

import java.lang.invoke.CallSite;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/***
 * 测试java1.7 动态类型语言  invokedynamic指令
 * 
 * 解析JDK 7的动态类型语言支持  http://www.infoq.com/cn/articles/jdk-dynamically-typed-language/
 * @author LingMin 
 * @date 2016-9-19<br>
 * @version 1.0<br>
 */
public class InvokeDynamicTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Throwable {
        INDY_BootstrapMethod().invokeExact("icyfenix");
    }
    public static void testMethod(String s) {
        System.out.println("hello String:" + s);
    }
    /**
     * 
     * @param lookup
     * @param name
     * @param mt
     * @return
     * @throws Throwable
     */
    public static CallSite BootstrapMethod(MethodHandles.Lookup lookup, String name, MethodType mt) throws Throwable {
        return new ConstantCallSite(lookup.findStatic(InvokeDynamicTest.class, name, mt));
    }
    /***
     * 通过编译后的字节码代码 进行访问定位 
     * 字节码：（参数及类型）方法返回参数类型
     * @return
     */
    private static MethodType MT_BootstrapMethod() {
        return MethodType.fromMethodDescriptorString("(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;", null);
    }
    /***
     * 动态执行 BootstrapMethod() 这个方法
     * @return
     * @throws Throwable
     */
    private static MethodHandle MH_BootstrapMethod() throws Throwable {
        return MethodHandles.lookup().findStatic(InvokeDynamicTest.class, "BootstrapMethod", MT_BootstrapMethod());
    }
    private static MethodHandle INDY_BootstrapMethod() throws Throwable {
        CallSite cs = (CallSite) MH_BootstrapMethod().invokeWithArguments(MethodHandles.lookup(), "testMethod", MethodType.fromMethodDescriptorString("(Ljava/lang/String;)V", null));
        return cs.dynamicInvoker();
    }

}
