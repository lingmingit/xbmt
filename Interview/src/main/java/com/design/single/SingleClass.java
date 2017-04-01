package com.design.single;


/***
 * 测试单例模式 ：  内部类<p>
 * @author LingMin 
 * @date 2016-12-7<br>
 * @version 1.0<br>
 */
public class SingleClass {

    private static boolean flag = false; 
	/***
	 * 私有构造方法
	 */
	private SingleClass(){
		//增加代码 防止  通过JAVA的反射机制来“攻击”单例模式
		 synchronized(SingleClass.class)  {  
            if(flag == false)  {  
                flag = !flag;  
            }  
            else{  
                throw new RuntimeException("单例模式被侵犯！");  
            } 
        }
	}
	/***
	 * 返回当前实例对象<p>
	 * @return <p>
	 * SingleClass
	 */
	public static SingleClass getInstance(){
		return SingleInnerClass.INSTANCE ;
	}
	
	/***
	 * 内部类 生成 单例对象，由于类加载机制保障 多个线程调用时，会加锁保障一个实例对象<p>
	 * @author LingMin 
	 * @date 2016-12-7<br>
	 * @version 1.0<br>
	 */
	private static class SingleInnerClass{
		private static final SingleClass INSTANCE  = new SingleClass();
	}
	
	
    public void doSomethingElse()  {  
    }  
}
