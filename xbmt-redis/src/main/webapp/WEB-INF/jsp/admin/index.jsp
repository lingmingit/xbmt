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
<title>首页</title>
</head>
<body>
	<h1>首页</h1>
	登录用户：<shiro:principal/><br/>
	
	<a href="${pageContext.request.contextPath}/admin/shiro">测试shiro权限控制</a>
	
	<a href="${pageContext.request.contextPath}/test/list">test角色</a>
	<a href="${pageContext.request.contextPath}/test2/list">test2角色</a>
	<a onclick="return window.confirm('您确认退出吗');" href="${pageContext.request.contextPath}/admin/logout">退出</a>
	<hr>
	<a href="${pageContext.request.contextPath}/admin/user/list">用户列表</a>
	<a href="${pageContext.request.contextPath}/admin/user/list2?numbers=user">用户list2</a>
</body>
</html>