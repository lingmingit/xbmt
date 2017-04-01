/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.framework.web.omui.model;

import java.util.ArrayList;
import java.util.List;

/** 
 * OperaMasks UI 前台 Grid数据模型实体<p>
 * @author LingMin 
 * @date 2015-9-7<br>
 * @version 1.0<br>
 */
public class GridDataModel<T> {
	
	  /****分页总行数******/
	  private int total;
	  /**每页数据集合对象****/
	  private List<T> rows = new ArrayList();
	  /**列表 列名称集合对象*****/
	  private List<Column> colmodel = new ArrayList();
	  
	/**
	 * 获取分页总行数<p>
	 * @return  total  分页总行数<br>
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * 设置分页总行数<p>
	 * @param  total  分页总行数<br>
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * 获取每页数据集合对象<p>
	 * @return  rows  每页数据集合对象<br>
	 */
	public List<T> getRows() {
		return rows;
	}
	/**
	 * 设置每页数据集合对象<p>
	 * @param  rows  每页数据集合对象<br>
	 */
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	/**
	 * 获取列表列名称集合对象<p>
	 * @return  colmodel  列表列名称集合对象<br>
	 */
	public List<Column> getColmodel() {
		return colmodel;
	}
	/**
	 * 设置列表列名称集合对象<p>
	 * @param  colmodel  列表列名称集合对象<br>
	 */
	public void setColmodel(List<Column> colmodel) {
		this.colmodel = colmodel;
	}

	  
	  
	  
}
