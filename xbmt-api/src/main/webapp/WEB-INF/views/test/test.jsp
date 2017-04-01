<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试Spring MVC 框架搭建</title>
</head>
<body>
	<h5>1、测试 Spring MVC请求响应 </h5>
	<ul>
		<c:forEach items="${strList}" var="data">
			<li>${data}</li>
		</c:forEach>
	</ul>
	<h5>2、测试 数据库查询数据 </h5>
	<ul>
		<c:forEach items="${dataList}" var="data">
			<li>${data.number} === ${data.name}</li>
		</c:forEach>
	</ul>
	<h5>3、测试 Spring MVC  链接请求响应 </h5>
	<ul>
		<li><a href="${pageContext.request.contextPath}/test/test/111">链接请求--测试1</a></li>
		<li><a href="${pageContext.request.contextPath}/test/test/222/and/myname">链接请求--测试222</a></li>
		<li><a href="${pageContext.request.contextPath}/test/test/data/111.json">链接请求--测试【请求json数据】</a></li>
		<li><a href="${pageContext.request.contextPath}/test/test/data2/111.json">链接请求--测试【请求json数据】</a></li>
		<li><a href="${pageContext.request.contextPath}/test/test/data2/111.xml">链接请求--测试【请求xml数据】</a></li>
		<%-- <li><a href="${pageContext.request.contextPath}/test/test/gotoUpload">测试文件上传</a></li> --%>
		
	</ul>
	<hr/>
	<div>
		${content}
	</div>
</body>
</html>