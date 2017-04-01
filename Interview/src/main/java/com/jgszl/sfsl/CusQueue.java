/**
 * &lt;p&gt;
 * copyright &amp;copy;  2017, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.jgszl.sfsl;

import java.util.HashSet;
import java.util.Set;

/** 
 * 队列结构<p>
 * @author LingMin 
 * @date 2017年3月16日<br>
 * @version 1.0<br>
 */
public class CusQueue {

	
	/**当前元素索引位置**/
	private int index;
	
	//2）环上每一个slot是一个Set<uid>，任务集合
	private Set<String> taskSets;
	
	/***
	 * 默认构造方法
	 */
	public CusQueue(){
	}
	

	/***
	 * 带参数构造方法
	 * @param index 索引位置
	 */
	public CusQueue(int index){
		this.index = index;
	}

	
	/***
	 * 带参数构造方法
	 * @param index 索引位置
	 * @param taskSets 任务集合对象
	 */
	public CusQueue(int index , Set<String> taskSets){
		this.index = index;
		this.taskSets = taskSets;
	}

	/**
	 * 获取index<p>
	 * @return  index  index<br>
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * 设置index<p>
	 * @param  index  index<br>
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * 获取taskSets<p>
	 * @return  taskSets  taskSets<br>
	 */
	public Set<String> getTaskSets() {
		return taskSets;
	}

	/**
	 * 设置taskSets<p>
	 * @param  taskSets  taskSets<br>
	 */
	public void setTaskSets(Set<String> taskSets) {
		this.taskSets = taskSets;
	}
	
	
	
	
	
}
