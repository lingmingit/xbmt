<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户list页面</title>
</head>
<body>
	<h1>用户list</h1>
	<a href="${pageContext.request.contextPath}/admin/index">首页</a>
	<a href="${pageContext.request.contextPath}/admin/user/add">新增</a>
	<a href="${pageContext.request.contextPath}/admin/user/list2?numbers=user">list2</a>
	
	<div id="promptMsg" class="tishier">${message}
		<!-- 删除 作用域中的消息提示 -->
		<c:remove var="message" scope="request"/>
		<c:remove var="message" scope="session"/>
	</div>
	
	<c:forEach var="item" items="${userList}" varStatus="vs">
		<div style="padding-left: 20px;">
			<span>${vs.count}</span>
			<span>${item.id}</span>
			<span>${item.numbers}</span>
			<span>${item.name}</span>
			<span>${item.sex}</span>
			<span>${item.age}</span>
			<span>${item.email}</span>
			<span><a href="${pageContext.request.contextPath}/admin/user/view/${item.id}">查看</a></span>
			<span><a href="${pageContext.request.contextPath}/admin/user/edit/${item.id}">修改</a></span>
			<span>删除</span>
		</div>
	</c:forEach>
	
</body>
</html>