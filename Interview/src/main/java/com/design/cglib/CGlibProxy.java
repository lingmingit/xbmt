package com.design.cglib;


/***
 * 测试 cglib 动态代理<p>
 * @author LingMin 
 * @date 2016-12-5<br>
 * @version 1.0<br>
 */
public class CGlibProxy {

	
	/***
	 * 
	 * cglib需要指定父类和回调方法。当然cglib也可以与Java动态代理一样面向接口，因为本质是继承。
	 * <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args){
		//被代理类
        CGlibHosee cGlibHosee = new CGlibHosee();
        //代理逻辑类
        CGlibHoseeProxy cGlibHoseeProxy = new CGlibHoseeProxy();
        //生成代理类对象
        CGlibHosee proxy = (CGlibHosee) cGlibHoseeProxy.bind(cGlibHosee);
        System.out.println(proxy.sayhi());
    }
}
