package com.xbmt.framework.web.omui.model;

import java.io.Serializable;


/****
 * OperaMasks UI 前台 Grid列模型实体<p>
 * @author LingMin 
 * @date 2015-9-7<br>
 * @version 1.0<br>
 */
public class Column implements Serializable{
	
 /***列中文名称***/
  private String header;
  /**列宽度**/
  private int width;
  /**列名称***/
  private String name;
  /***列对齐方法**/
  private String align;
  /*****/
  private String render;

  /****
   * 构造方法
   * @param header 列中文名称
   * @param width 列宽度
   * @param name 列名称
   * @param align 列对齐方法
   * @param render
   */
  public Column(String header, int width, String name, String align, String render){
    this.header = header;
    this.width = width;
    this.name = align;
    this.align = align;
    this.render = render;
  }

  /****
   * 默认构造方法
   */
  public Column(){
  }
	
	/**
	 * 获取列中文名称<p>
	 * @return  header  列中文名称<br>
	 */
	public String getHeader() {
		return header;
	}
	
	/**
	 * 设置列中文名称<p>
	 * @param  header  列中文名称<br>
	 */
	public void setHeader(String header) {
		this.header = header;
	}
	
	/**
	 * 获取列宽度<p>
	 * @return  width  列宽度<br>
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * 设置列宽度<p>
	 * @param  width  列宽度<br>
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * 获取列名称<p>
	 * @return  name  列名称<br>
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 设置列名称<p>
	 * @param  name  列名称<br>
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取列对齐方法<p>
	 * @return  align  列对齐方法<br>
	 */
	public String getAlign() {
		return align;
	}
	
	/**
	 * 设置列对齐方法<p>
	 * @param  align  列对齐方法<br>
	 */
	public void setAlign(String align) {
		this.align = align;
	}
	
	/**
	 * 获取<p>
	 * @return  render  <br>
	 */
	public String getRender() {
		return render;
	}
	
	/**
	 * 设置<p>
	 * @param  render  <br>
	 */
	public void setRender(String render) {
		this.render = render;
	}
	  
	  
  

}