package com.classinit;



/** 
 * 测试类初始化顺序 <p>
 * 来说说Java中的实例初始化器  链接：http://www.importnew.com/12893.html
 * 
 * @author LingMin 
 * @date 2016-10-20<br>
 * @version 1.0<br>
 */
public class Foo {

	//instance variable initializer 实例变量初始化器
    String s = "abc";
  
    //constructor 构造函数
    public Foo() {
        System.out.println("constructor called");
    }
  
    //static initializer   静态初始化器
    static {
        System.out.println("static initializer called");
    }
  
    //instance initializer 实例变量初始化器
    {
        System.out.println("instance initializer called");
    }
  
    public static void main(String[] args) {
        new Foo();
        new Foo();
    }
}
