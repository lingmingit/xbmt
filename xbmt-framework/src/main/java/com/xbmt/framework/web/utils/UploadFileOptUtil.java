/**
 * &lt;p&gt;
 * copyright &amp;copy;  2015, all rights reserved.
 * @author admin
 * @version $Id$
 * @since 1.0
 * 
 */
package com.xbmt.framework.web.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.xbmt.common.CommonUtils;
import com.xbmt.common.StringUtils;
import com.xbmt.common.date.DateUtils;

/** 
 * 文件上传操作辅助类<p>
 * @author LingMin 
 * @date 2015-7-31<br>
 * @version 1.0<br>
 */
public class UploadFileOptUtil {
	
	/** 日志书写对象**/
	protected static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(UploadFileOptUtil.class);

	/***上传文件 原始名称**/
	public static final String ORIGINALNAME = "originalName";
	/***上传文件重命名名称***/
	public static final String REALNAME = "realName";
	/***上传文件所对应提交参数名称***/
	public static final String PARAMNAME = "paramName";
	/***上传文件大小***/
	public static final String SIZE = "size";
	/***上传文件后缀名***/
	public static final String SUFFIX = "suffix";
	/**文件类型***/
	public static final String CONTENTTYPE = "contentType";
	/****文件创建时间***/
	public static final String CREATETIME = "createTime";
	/***上传文件URI  路径 用于显示或下载****/
	public static final String FILEURI = "fileURI";
	
	/**文件上传目录**/
	public static final String UPLOADDIR = "uploadDir/";
	/**文件下载目录***/
	public static final String UPLOADDIR2 = "uploadFilesDir/";
	/****日期格式化字符串****/
	private static final String dateformater = "yyyyMMddHHmmss";
	
	
	
	
	/**
	 * 将上传的文件进行重命名,重命名为 当前时间 (yyyyMMddHHmmss)+ 随机数
	 * @param name 文件
	 * @return
	 */
	private static String rename(String name) {
		String dateStr = DateUtils.getCurrentDateBySpecifiedFormatter(dateformater);
		Long now = Long.parseLong(dateStr);
		Long random = (long) (Math.random() * now);
		String fileName = now + "" + random;
		if (name.indexOf(".") != -1) {//截取上传文件的后缀名
			fileName += name.substring(name.lastIndexOf("."));
		}
		return fileName;
	}
	
	/**
	 * 获取文件名称后缀
	 * @param name 文件
	 * @return 返回后缀名称
	 */
	private static String getFileNameSuffix(String name) {
		String suffix = null;
		if (CommonUtils.isNotEmptyObject(name) && name.indexOf(".") != -1) {//截取上传文件的后缀名
			suffix = name.substring(name.lastIndexOf("."));
		}
		return suffix;
	}
	
	
	/***
	 * 获取服务器 上下文根目录<p>
	 * @param request
	 * @return String 返回路径<p>
	 * 
	 */
	public static String getServerContextRootDir(HttpServletRequest request){
		 return request.getSession(false).getServletContext().getRealPath("/");
	}
	
	/**
	 * 上传文件
	 * 使用  apache  ant.jar 第三方包
	 * @date 2012-5-5 下午12:25:47
	 * @param request http请求对象
	 * @param uploadRootDir文件上传目录参数，如果为空，则使用服务器根目录+UploadFileOptUtil.UPLOADDIR
	 * @param uploadSubDir 上传文件子目录
	 * @param paramsValue 上传文件所对应参数
	 * @return 返回文件对象
	 * @throws Exception
	 */
	public static List<Map<String, Object>> uploadApache(HttpServletRequest request, String uploadRootDir , String uploadSubDir , String[] paramsValue) throws Exception {

		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = mRequest.getFileMap();
		String uploadFileURI = "";
		if(StringUtils.isEmpty(uploadRootDir)){
			uploadRootDir = UploadFileOptUtil.getServerContextRootDir(request)+ UploadFileOptUtil.UPLOADDIR;
			uploadFileURI = UploadFileOptUtil.UPLOADDIR;
		}
		if(StringUtils.isNotEmpty(uploadSubDir)){
			uploadFileURI = uploadFileURI.concat(uploadSubDir);
			uploadRootDir = uploadRootDir.concat(uploadSubDir);
		}
		
		File file = new File(uploadRootDir);
		if (!file.exists()) {//如果目录不存在 则创建
			//file.mkdir();//该方法只能创建一个目录
			file.mkdirs();//该方法 能创建当前目录和 子目录
		}
		String fileName = null;
		int i = 0;
		for (Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator(); it.hasNext(); i++) {

			Map.Entry<String, MultipartFile> entry = it.next();
			MultipartFile mFile = entry.getValue();
			//上传文件的原始名称
			fileName = mFile.getOriginalFilename();
			if(StringUtils.isEmpty(fileName) || mFile.getSize() == 0){
				continue;
			}
			//将上传的文件进行重命名,重命名为 当前时间 (yyyyMMddHHmmss)+ 随机数，如：201303271449391048661750630.txt
			String storeName = rename(fileName);
			String filePath = uploadRootDir + storeName;
			uploadFileURI = uploadFileURI.concat(storeName);
			// 上传成为压缩文件
			OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(filePath));
			//copy文件到制定目录
			FileCopyUtils.copy(mFile.getInputStream(), outputStream);
			
			Map<String, Object> map = new HashMap<String, Object>();
			// 固定参数值对
			map.put(UploadFileOptUtil.ORIGINALNAME, fileName);//上传文件名：原始名称
			map.put(UploadFileOptUtil.REALNAME, storeName);//上传文件名：重命名名称
			if(CommonUtils.isNotEmptyObjectArray(paramsValue)){
				map.put(UploadFileOptUtil.PARAMNAME, paramsValue[i]);//上传文件 所对应提交参数名称
			}
			map.put(UploadFileOptUtil.SIZE, new File(filePath).length());//文件大小
			map.put(UploadFileOptUtil.SUFFIX, UploadFileOptUtil.getFileNameSuffix(fileName));//文件后缀
			map.put(UploadFileOptUtil.CONTENTTYPE, "application/octet-stream");
			map.put(UploadFileOptUtil.CREATETIME, new Date());
			map.put(UploadFileOptUtil.FILEURI, uploadFileURI);
			result.add(map);
		}
		return result;
	}
	
	
	/**
	 * 下载 文件方法
	 * @param request http 请求对象
	 * @param response http响应对象
	 * @param downloadDir 文件下载目录
	 * @param storeName 下载文件名称(上传文件 系统自动生成的zip文件名称) 如：2013030809215114586099822095.zip
	 * @param contentType 下载文件类型学
	 * @param realName 上传原文件名称 ，如：xx.txt
	 * @throws Exception
	 */
	public static void download(HttpServletRequest request, HttpServletResponse response , String downloadDir , String storeName, String contentType,String realName) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		if(StringUtils.isEmpty(downloadDir)){
			//获取服务器 文件目录
			downloadDir = request.getSession(false).getServletContext().getRealPath("/").concat(UploadFileOptUtil.UPLOADDIR);
		}
		String downLoadPath = downloadDir.concat(storeName);
		long fileLength = new File(downLoadPath).length();
		response.setContentType(contentType);
//		String fileName = new String(realName.getBytes("utf-8"), "ISO8859-1");//下载 默认的  乱码
		//String fileName = new String(realName.getBytes("GB2312"), "ISO8859-1");// 下载为 中文件名  成功转换
		//String fileName = java.net.URLEncoder.encode(realName,"UTF-8"); //// 下载为 中文件名   成功转换
		//String fileName =  java.net.URLDecoder.decode(realName,"UTF-8"); //这个是解码， 所以没有用 
		String fileName = new String(realName.getBytes("GBK"), "ISO8859-1");// 下载为 中文件名  成功转换
		
		response.setHeader("Content-disposition", "attachment; filename=".concat(fileName));
		response.setHeader("Content-Length", String.valueOf(fileLength));

		InputStream inputStream = new FileInputStream(downLoadPath);
		bis = new BufferedInputStream(inputStream);
		bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buff = new byte[2048];
		int bytesRead;
		while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
			bos.write(buff, 0, bytesRead);
		}
		bis.close();
		inputStream.close();
		bos.close();
	}
	
	
	/***
	 * 删除上传文件【当编辑图片时，重新上传新地图，将之前的图片删除，保持服务器没有多余的文件】<p>
	 * @param uploadRootDir
	 * @param uploadSubDir <p>
	 * void
	 */
	public static void deleteUploadFile(String uploadRootDir , String uploadSubDir , String fileName){
		if(StringUtils.isEmpty(uploadRootDir)){
			return;
		}
		
		if(StringUtils.isNotEmpty(uploadSubDir)){
			uploadRootDir = uploadRootDir.concat(uploadSubDir);
		}
		
		if(StringUtils.isNotEmpty(fileName)){
			uploadRootDir = uploadRootDir.concat(fileName);
		}
		File file = new File(uploadRootDir);
		if(file.exists()){
			logger.info("成功删除上传文件："+uploadRootDir);
			file.delete();
		}
		
		
	}
}
