<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户查看</title>
</head>
<body>
	<h1>用户查看</h1>
	 <a href="${pageContext.request.contextPath}/admin/index">首页</a>
  <a href="${pageContext.request.contextPath}/admin/user/list">用户首页</a>
  
	<div style="padding-left: 10px;">
			<span>${data.id}</span>
			<span>${data.numbers}</span>
			<span>${data.name}</span>
			<span>${data.sex}</span>
			<span>${data.age}</span>
			<span>${data.email}</span>
		</div>
</body>
</html>