package com.xbmt.framework.web.utils;


/** 
 * 分页计算 帮助类<p>
 * @author LingMin 
 * @date 2015-5-21<br>
 * @version 1.0<br>
 */
public class PageUtils {

	
	/***
	 *  计算分页分页数据起始行索引<p>
	 * @param currentPage 当前页码
	 * @param pageSize 每页行数
	 * @return 返回 总页数
	 */
	public static int getRowIndex(int currentPage , int pageSize){
		//分页数据起始行索引  =  (当前页 -1) *每页 大小
		return (currentPage - 1) * pageSize;
	}
	
	/***
	 *  计算分页 总页数<p>
	 * @param totalRowCount总记录数
	 * @param pageSize 每页行数
	 * @return 返回 总页数
	 */
	public static int getPageCount(int totalRowCount , int pageSize){
		return(int)Math.ceil((double)totalRowCount / pageSize);//计算总页数=总记录行数量 /每页行数   取整数(大)
	}
}
