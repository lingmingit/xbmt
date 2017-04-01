<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>后台管理登录</title>
		<link rel="stylesheet" type="text/css" href=""/>
		<script type="text/javascript" src="<c:url value='/js/base/jquery-1.8.2.js'/>"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/system.js"></script>
		  <script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/login.js"></script>
		<style type="text/css">
			<!--
			body {
				margin-left: 0px;
				margin-top: 0px;
				margin-right: 0px;
				margin-bottom: 0px;
				overflow:hidden;
			}
			.STYLE3 {color: #528311; font-size: 12px; }
			.STYLE4 {
				color: #42870a;
				font-size: 12px;
			}
			-->
			</style>
</head>
<body onkeydown="enterlogin();">
	<form id="loginform" onsubmit="return formOnSubmit();" action="${pageContext.request.contextPath}/admin/login" method="post">
		<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
			  <tr>
			    <td bgcolor="#e5f6cf">&nbsp;</td>
			  </tr>
			  <tr>
			    <td height="608" background="${pageContext.request.contextPath}/images/login2/login_03.gif"><table width="862" border="0" align="center" cellpadding="0" cellspacing="0">
			      <tr>
			        <td height="266" background="${pageContext.request.contextPath}/images/login2/login_04.gif">&nbsp;</td>
			      </tr>
			      <tr>
			        <td height="95">
			          <table width="100%" border="0" cellspacing="0" cellpadding="0">
				          <tr>
				            <td width="415" height="150" background="${pageContext.request.contextPath}/images/login2/login_06.gif">&nbsp;</td>
				            <td width="192" background="${pageContext.request.contextPath}/images/login2/login_07.gif">
					            <table width="100%" border="0" cellspacing="0" cellpadding="0">
					              <tr>
					                <td width="28%" height="30"><div align="center"><span class="STYLE3">用户名：</span></div></td>
					                <td width="72%" height="30">
					                	<input type="text" id="username" name="username"  value="user" style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;" required="required">
					                </td>
					              </tr>
					              <tr>
					                <td height="30"><div align="center"><span class="STYLE3">密码：</span></div></td>
					                <td height="30">
					                	<input type="password" id="password" name="password"  value="123456" style="height:18px; width:130px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;" required="required">
					                </td>
					              </tr>
					              <c:if test="${param.type == 'loginError' }">
						              <tr>
						                <td height="30"><div align="center"><span class="STYLE3">验证码：</span></div></td>
						                <td height="30">
						                	<input type="text" id="validateCode" name="validateCode" maxlength="5"  style="float:left;height:24px; width:50px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;" required="required">
						                	<img style="float:right;display:block;margin-right: 5px;" id="authCode" src="${pageContext.request.contextPath}/captcha.svl? + Math.random()" onclick="refreshValidateCode();" alt="验证码" height="30"/>
						                </td>
						              </tr>
						          </c:if>
					             <!-- 此处增加验证码，登陆页面会变形，由于背景图片高度固定造成  required="false"-->
					              <tr>
					                <td height="30">&nbsp;</td>
					                <td height="30"><img src="${pageContext.request.contextPath}/images/login2/dl.gif" width="81" height="22" border="0" usemap="#Map"/></td>
					              </tr>
					               <tr><!--  错误消息-->
						              <td height="5" colspan="2" style="color:red;font-size: 12px;">&nbsp; <input type="submit" id="submit"  value="Submit" style="display: none;" /> 
						              	<c:if test="${param.type == 'loginError' }">
						              		用户名和密码错误!!
						              	</c:if>
						              </td>
						          </tr>
					            </table>
					         </td>
				            <td width="255" background="${pageContext.request.contextPath}/images/login2/login_08.gif">&nbsp;</td>
				          </tr>
				        </table>
			        </td>
			      </tr>
			      <tr>
			        <td height="247" valign="top" background="${pageContext.request.contextPath}/images/login2/login_09.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			          <tr>
			            <td width="22%" height="30">&nbsp;</td>
			            <td width="56%">&nbsp;</td>
			            <td width="22%">&nbsp;</td>
			          </tr>
			          <tr>
			            <td>&nbsp;</td>
			            <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
			              <tr>
			                <td width="44%" height="20">&nbsp;</td>
			                <td width="56%" class="STYLE4">版本 2015V1.0</td>
			              </tr>
			            </table></td>
			            <td>&nbsp;</td>
			          </tr>
			        </table></td>
			      </tr>
			    </table></td>
			  </tr>
			  <tr>
			    <td bgcolor="#a2d962">&nbsp;</td>
			  </tr>
			</table>
			<map name="Map"><!-- document.getElementById('loginForm').submit();return false; -->
				<area shape="rect" coords="3,3,36,19"  onclick="submitForm();" href="#"/>
				<area shape="rect" coords="40,3,78,18" onclick="return clearForm();" href="#"/>
			</map>
		</form>
</body>
</html>