/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.common.exception;

import java.io.PrintStream;

/** 
 * 异常辅助类<p>
 * @author LingMin 
 * @date 2015-10-28<br>
 * @version 1.0<br>
 */
public class ExceptionUtils {

	
	/**
	 * 获取详细的错误 信息
	 * @param exc 异常对象
	 * @param level 异常层次
	 * @return
	 */
	public static String getDetailExceptionMessage(Throwable exc){
		return ExceptionUtils.getDetailExceptionMessage(exc, 3);
	}
	
	/**
	 * 获取详细的错误 信息
	 * @param exc 异常对象
	 * @param level 异常层次
	 * @return
	 */
	public static String getDetailExceptionMessage(Throwable exc , int level){
		StringBuffer detail = new StringBuffer();
	        detail.append("错误信息："); 
	        detail.append(exc.getMessage());
	        detail.append(System.getProperty("line.separator"));
	        detail.append("错误详细堆栈信息：");
	       
	        detail.append(ExceptionUtils.getExcLinkStackTrace(exc , level));
	        //logger.error(detail);
	        return detail.toString();
	}
	/**
	 * 获取 异常对象的堆栈信息 
	 * @param exc 异常信息
	 * @return
	 */
	public static String getStackTraceFromBaseException(Throwable exc){
        String str = "";
        try{
            Exception be = (Exception)exc;
            //java.io.ByteArrayOutputStream
            java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
            PrintStream ps = new PrintStream(baos, false, "UTF8");
            be.printStackTrace(ps);
            str = baos.toString("UTF8");
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return str;
    }

	
	/**
	 * 获取 异常对象的堆栈信息 
	 * @param exc 异常对象
	 * @param level 异常层次
	 * @return 返回异常字符串信息
	 */
	 public static String getExcLinkStackTrace(Throwable exc , int level){
        StringBuffer result = new StringBuffer();
        for(Throwable cause = exc; cause != null; cause = cause.getCause()){
            result.append(cause.getClass().getName());
            result.append(": ");
            result.append(cause.getMessage());
            result.append(System.getProperty("line.separator"));
            StackTraceElement stackTraces[] = cause.getStackTrace();
           // int count = 3;
	        if(stackTraces.length < level){
	        	level = stackTraces.length;
	        }
	        System.out.println("level..................="+level);
            for(int i = 0; i < level; i++){
                result.append("\n    at:");
                result.append(stackTraces[i].toString());
                result.append(System.getProperty("line.separator"));
            }
            result.append(System.getProperty("line.separator"));
        }
        return result.toString();
    }
	
	 /***
	  * 获取异常对象的堆栈信息<p>
	  * @param e
	  * @return <p>
	  * String
	  */
	 public static String getExceptionStack(Exception e) {
	        StackTraceElement[] stackTraceElements = e.getStackTrace();
	        String result = e.toString() + "\n";
	        int count = 3;
	        if(stackTraceElements.length < 3){
	        	count = stackTraceElements.length;
	        }
	        
	        for (int index = count - 1; index >= 0; --index) {
	                result += "at [" + stackTraceElements[index].getClassName() + ",";
	                result += stackTraceElements[index].getFileName() + ",";
	                result += stackTraceElements[index].getMethodName() + ",";
	                result += stackTraceElements[index].getLineNumber() + "]\n";
	        }
	        return result;
	}
	
	 /***
	  * <p>
	  * @param args <p>
	  * void
	  */
	public static void main(String[] args) {
		try {
			String temp = null;
			temp.length();
		} catch (Exception e) {
			//第一种方法 getExceptionStack
			//String msg = ExceptionUtils.getExceptionStack(e);
			//第二种方法 getExcLinkStackTrace
			//String msg = ExceptionUtils.getExcLinkStackTrace(e);
			//第三种方法 getStackTraceFromBaseException
			//String msg = ExceptionUtils.getStackTraceFromBaseException(e);
			//第四种方法 getDetailExceptionMessage
			String msg = ExceptionUtils.getDetailExceptionMessage(e);
			
			System.out.println("msg="+msg);
		}
	}
	 
}
