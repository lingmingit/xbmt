<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">


    </script>

<div style="position: relative;height:77px">
	<div>
		userId = ${userId}
		<input type="button" value="添加按钮" onclick="addButton();" />
		<a onclick="return window.confirm('您确认退出吗？');" href="${pageContext.request.contextPath}/j_spring_security_logout">退出</a>
	</div>
	
	 <div id="buttonbar"></div>
</div>




