<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试Session共享页面B</title>
</head>
<body>
	<h1>测试Session共享页面B</h1>
	 <%
	HttpSession mysession = request.getSession(false);
	if(mysession==null){
		mysession = request.getSession(true);
		mysession.setAttribute("appname","value-A");
		out.println("new session:"+mysession.getId());
	}else{
		out.println("old session:"+mysession.getId());
	}
	out.println("appname="+mysession.getAttribute("appname"));

    %>
</body>
</html>