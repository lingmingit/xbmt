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
<title>用户新增</title>
</head>
<body>
  <h1>用户新增</h1>
  
  <a href="${pageContext.request.contextPath}/admin/index">首页</a>
  <a href="${pageContext.request.contextPath}/admin/user/list">用户首页</a>
  <div id="promptMsg" class="tishier">${message}
		<!-- 删除 作用域中的消息提示 -->
		<c:remove var="message" scope="request"/>
		<c:remove var="message" scope="session"/>
	</div>
  	<form id="editForm" name="editForm" role="form"  action="${pageContext.request.contextPath}/admin/user/save" method="post">
		<input type="hidden" id="pageStatus" name="pageStatus" value="${pageStatus}">
		<input type="hidden" id="id" name="id" value="${data.id}">
		<input type="hidden" id="token" name="token" value="${token}" />
		 <div class="biaodaner">
	        <ul>
	        <li>
	        	<a>用户名：</a>
	        	<span class="spaner">
					<input type="text" id="numbers" name="numbers"    class="shujruk" value="${data.numbers}" required="required" />
				</span>
			</li>
			<li><a>密码：</a>
				<span class="spaner">
					<input type="password"  name="password" maxlength="30"  class="shujruk" value="${data.password}" required="required"/>
				</span>
			</li>
	         <li><a>姓名：</a><span class="spaner"><input type="text" name="name"  class="shujruk" value="${data.name}" required="required"/></span></li>
	         <li><a>性别：</a><span class="spaner">
	         		<c:set var="sexSelected" value="${data.sex}" />
	         		<c:choose>
	         			<c:when test="${data.sex == 0}">
	         				<input type="radio" id="m" name="sex" value="1"/>男
	         				<input type="radio" id="f" name="sex" checked="checked" value="0"/>女
	         			</c:when>
	         			<c:otherwise>
	         				<input type="radio" id="m" name="sex" checked="checked" value="1"/>男
	         				<input type="radio" id="f" name="sex" value="0"/>女
	         			</c:otherwise>
	         		</c:choose>
	         		
	         	</span></li>
	         <li><a>年龄：</a><span class="spaner"><input type="number" name="age" min="1" max="100"  class="shujruk" value="${data.age}" required="required"/></span></li>
	         <li><a>电子邮件：</a><span class="spaner"><input type="email" name="email"  class="shujruk" value="${data.email}" required="required"/></span></li>
	         <li><a>电话：</a><span class="spaner"><input type="tel" name="telephone"  class="shujruk" value="${data.telephone}" required="required"/></span></li>
	         <li><a>手机：</a><span class="spaner"><input type="text" name="mobilePhone"  class="shujruk" value="${data.mobilePhone}" required="required"/></span></li>
	         <li><a>QQ:</a><span class="spaner"><input type="text" name="qq"  class="shujruk" value="${data.qq}"/></span></li>
	         <li style="width:100%;"><a>地址：</a><span class="spaner"><input type="text" name="homeAddress"  class="shujruk" value="${data.homeAddress}" required="required"/></span></li>
	         <li style="width:100%;display: none;"><a>启用:</a><span>
	         	<c:set var="isEnableSelected" value=""/>
	         	<c:if test="${data.isEnable == true }">
	         		<c:set var="isEnableSelected" value="checked='checked'"/>
	         	</c:if>
	         	<input type="checkbox" name="isEnable" ${isEnableSelected} onclick="return false;" value="true"/>
	         	</span></li>
	        </ul>
      </div>
       <div class="caozuoan">
       			<input type="submit" id="submit" value="保存"  />
				<a class="caozuoan_a" href="#" onclick="gotoback();">取消</a>
			</div>
	</form>
</body>
</html>