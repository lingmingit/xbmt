/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.base;

/** 
 * Java for-each循环解惑
 * 从中了解到的一个有趣的事实是：for-each循环仅应用于实现了Iterable接口的Java array和Collection类，而且既然所有内置Collection类都实现了java.util.Collection接口，已经继承了Iterable，这一细节通常会被忽略,这点可以在Collection接口的类型声明“ public interface Collection extends Iterable”中看到。所以为了解决上述问题，你可以选择简单地让CustomCollection实现Collection接口或者继承AbstractCollection，这是默认的通用实现并展示了如何同时使用抽象类和接口以获取更好的灵活性。
 * <p>
 * @author LingMin 
 * @date 2016-9-11<br>
 * @version 1.0<br>
 */
public class ForEachTest {

	/**             <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		CustomCollection<String> myCollection = new CustomCollection<String>();
        myCollection.add("Java");
        myCollection.add("Scala");
        myCollection.add("Groovy");
 
        //What does this code will do, print language, throw exception or compile time error
        /**这个地方会出现编辑错误，因为 CustomCollection 未实现 迭代器Iterable接口
        for(String language: myCollection){
            System.out.println(language);
        }
		*/
	}

}
