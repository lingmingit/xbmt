package com.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/***
 * 
 * 1、for降序删除：测试结果=成功删除完所有元素
 * 2、for升序删除:测试结果=只能删除部分元素
 * 3、foreach删除 异常 Exception in thread "main" java.util.ConcurrentModificationException
 * 4、itr.remove(); 使用迭代器中的 删除方法进行删除 ，不会出现异常，并且成功删除
 * 测试 list 删除元素<p>
 * @author LingMin 
 * @date 2016-7-19<br>
 * @version 1.0<br>
 */
public class ListRemove {

	/**            
	 *  <p>
	 * @param args <p>
	 * void
	 */
	public static void main(String[] args) {
		//ListRemove.removeForAscList();//升序删除
		//ListRemove.removeForeach();//foreach删除 异常 Exception in thread "main" java.util.ConcurrentModificationException
		//ListRemove.removeForDescList2();//降序删除
		ListRemove.iteratorRemove();//迭代器删除 元素
	}
	
	/***
	 * 
	 * for降序删除：测试结果=成功删除完所有元素
	 * 删除 list 元素（从最后一个索引开始）<p>
	 *  <p>
	 * void
	 */
	public static void removeForDescList2(){
		List<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		for(int i = list.size() -1 ; i >= 0 ; i--){
			System.out.println("i="+i);
			list.remove(i);
		}
		System.out.println(list);
	}

	
	/***
	 * for升序删除:测试结果=只能删除部分元素
	 * 删除 list 元素（从第一个索引开始）<p>
	 *  <p>
	 * void
	 */
	public static void removeForAscList(){
		List<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		list.add("ddd");
		for(int i = 0 ; i < list.size() ; i++){
			System.out.println("i="+i);
			list.remove(list.get(i));
		}
		System.out.println(list);
	}

	/***
	 * foreach删除 异常 Exception in thread "main" java.util.ConcurrentModificationException
	 * 删除 list 元素（foreach）<p>
	 *  <p>
	 * void
	 */
	public static void removeForeach(){
		List<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		for(String temp : list){
			list.remove(temp);
		}
		System.out.println(list);
	}
	
	/***
	 * 通过迭代器删除 list中的元素 不会出现异常<p>
	 *  <p>
	 * void
	 */
	public static void iteratorRemove(){
		List<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");
		Iterator<String> itr = list.iterator();
        while(itr.hasNext()){
            String lang = itr.next();
           // list.remove(lang);//list 中的删除方法  Exception in thread "main" java.util.ConcurrentModificationException
            itr.remove();//使用迭代器中的 删除方法进行删除 ，不会出现异常，并且成功删除
        }
        System.out.println(list);
	}

}
