/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.javadeathmatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * 对于运行时Map容器的离奇事件
 * 看回源码，compute实际上是“通过key在map中查找一个value。如果这个value是null，则插入(key, value)，并返回value”。

    因为开始时，这个list是空的，“foo”值并不存在，v是null。然后，我们向map中插入一个“foo”并且“foo”指向new ArrayList()，此时的ArrayList对象是空的，所以它打印出[]。
    下一行，“foo”键值存在于map容器中，所以我们计算右边的表达式。ArrayList对象成功转换为List类型，然后“ber”字符串被插入到List中。add方法返回true，因此true就是第二行打印的内容。

 * 
 *  <p>
 * @author LingMin 
 * @date 2017-3-16<br>
 * @version 1.0<br>
 */
public class TestMap {

	/**             <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Map<String,Object> collection =  new TreeMap<>();
		//jdk 1.8
		System.out.println( collection.compute("foo", (k, v) -> ( null == v )? new ArrayList<Object>(): ((List)v).add("bar") ));
	    System.out.println( collection.compute("foo", (k, v) -> ( null == v )? new ArrayList<Object>(): ((List)v).add("ber") ));
	    
	    System.out.println(collection.get("foo"));
	}

}
