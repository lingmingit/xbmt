<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试shiro权限标签</title>
</head>
<body>
<hr/>
	<h1>测试shiro权限标签</h1>
	shiro:hasRole角色测试:
	<shiro:hasRole name="admin">
        <a href="javascript:void(0);" >admin</a>
    </shiro:hasRole>
    <shiro:hasRole name="test">
        <a href="javascript:void(0);" >test</a>
    </shiro:hasRole>
    <shiro:hasRole name="aaa">
        <a href="javascript:void(0);" >aaa</a>
    </shiro:hasRole>
	<hr/>
	shiro:hasPermission权限测试:
	<shiro:hasPermission name="/test/list">
        <a href="javascript:void(0);" >列表</a>
    </shiro:hasPermission>
	 <shiro:hasPermission name="/test/add">
        <a href="javascript:void(0);" >新增</a>
    </shiro:hasPermission>
     <shiro:hasPermission name="/test/edit">
        <a href="javascript:void(0);" >编辑</a>
    </shiro:hasPermission>
     <shiro:hasPermission name="/test/delete">
        <a href="javascript:void(0);" >删除</a>
    </shiro:hasPermission>
	<hr/>
	<a onclick="return window.confirm('您确认退出吗');" href="${pageContext.request.contextPath}/admin/logout">退出</a>
	<hr/>
</body>
</html>