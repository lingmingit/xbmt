/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.javadeathmatch;

import java.sql.SQLException;

/** 
 * 这段代码错在哪儿？
a.编译错误，因为没有SQLException被抛出
b.抛出ClassCastException，因为SQLException并不是RuntimeException的一个实例
c.没有错误，程序打印出抛出的SQLException堆栈跟踪信息
d.编译错误，因为我们不能将SQLException类型转换成RuntimeException
好，我们能从题目中得到什么信息？题目中的泛型涉及到了类型擦除，以及一些异常。这里需要回忆一些知识：

Java的泛型并不是具体化的。这意味着在编译时，泛型的类型信息会“丢失”，并且泛型参数像是被它的限定类型替换了一样，或者当限定类型不存在时，泛型参数被替换成了Object。这就是大家所说的类型“擦除”。

所以，正确答案是：编译失败，因为编译器认为SQLException不会从try代码块中抛出－但是实际上它确实能抛出！
<p>
 * @author LingMin 
 * @date 2017-3-16<br>
 * @version 1.0<br>
 */
public class Mocker<T extends Exception> {
	
	
	private void pleaseThrow(final Exception t) throws T {
		throw(T)t;
	}

	/**             <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {

//		try{
			new Mocker<RuntimeException>().pleaseThrow(new SQLException());
//		}catch(final SQLException ex){
//			ex.printStackTrace();
//		}
	}

}
