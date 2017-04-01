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
<title>测试[test]角色首页</title>
</head>
<body>
	<h1>测试[test]角色首页</h1>
	<a href="${pageContext.request.contextPath}/admin/index">首页</a>
	<a onclick="return window.confirm('您确认退出吗');" href="${pageContext.request.contextPath}/admin/logout">退出</a>
</body>
</html>