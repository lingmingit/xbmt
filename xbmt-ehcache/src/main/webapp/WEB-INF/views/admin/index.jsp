<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>后台管理主页</title>
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.3/themes/icon.css">
    <link href="${pageContext.request.contextPath}/css/index.css" rel="stylesheet" type="text/css">
    
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.3/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.3/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.3/locale/easyui-lang-zh_CN.js"></script>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/system.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/index.js"></script>
</head>
<body>

	<div class="easyui-layout" data-options="fit:true,border:false,plain:true" >
   		<!-- 顶部区域 -->
        <div data-options="region:'north'" style="height:80px">
        	<jsp:include page="header.jsp"/>
        </div>
        <!-- 左边区域 -->
        <div data-options="region:'west',split:true" title="West" style="width:200px;">
	   	    <%--  <ul>
	   	     	<li><a href="${pageContext.request.contextPath}/admin/test">测试页面</a></li>
	   	     	<li><a href="${pageContext.request.contextPath}/admin/sys/user/index">用户管理</a></li>
	   	     	<li><a href="${pageContext.request.contextPath}/admin/sys/user2/index">用户管理2</a></li>
	   	     	<li><a href="${pageContext.request.contextPath}/admin/sys/modules/index">系统模块</a></li>
	   	     	<li><a href="${pageContext.request.contextPath}/admin/sys/functionAction/index">系统功能</a></li>
	   	     	<li><a href="${pageContext.request.contextPath}/admin/sys/menu/index">系统菜单</a></li>
	   	     	<li></li>
	   	     	<li></li>
	   	     	<li></li>
	   	     	<li></li>
	   	     </ul> --%>
	   	     
	   	     	<div class="easyui-accordion" data-options="fit:true,border:false" >
				<div title="About" data-options="iconCls:'icon-ok'" style="overflow:auto;padding:10px;">
					<h3 style="color:#0099FF;">Accordion for jQuery</h3>
					<span><input type="button"  value="添加tab" onclick="addPanel();">
						<input type="button"  value="用户" onclick="addUserTab();">
						<input type="button"  value="模块" onclick="addModuleTab();">
						<input type="button"  value="菜单" onclick="addMenuTab();">
						<input type="button"  value="系统功能" onclick="addFunctionActionTab();">
					</span>
					<p>Accordion is a part of easyui framework for jQuery. It lets you define your accordion component on web page more easily.</p>
				</div>
				<div title="Help" data-options="iconCls:'icon-help'" style="padding:10px;">
					<p>The accordion allows you to provide multiple panels and display one or more at a time. Each panel has built-in support for expanding and collapsing. Clicking on a panel header to expand or collapse that panel body. The panel content can be loaded via ajax by specifying a 'href' property. Users can define a panel to be selected. If it is not specified, then the first panel is taken by default.</p> 		
				</div>
				<div title="TreeMenu" data-options="iconCls:'icon-search'" style="padding:10px;">
					<ul class="easyui-tree">
						<li>
							<span>Foods</span>
							<ul>
								<li>
									<span>Fruits</span>
									<ul>
										<li>apple</li>
										<li>orange</li>
									</ul>
								</li>
								<li>
									<span>Vegetables</span>
									<ul>
										<li>tomato</li>
										<li>carrot</li>
										<li>cabbage</li>
										<li>potato</li>
										<li>lettuce</li>
									</ul>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
        </div>
        
        <!-- 中心区域  ,title:'Main Title',iconCls:'icon-ok'-->
        <div  data-options="region:'center'">
        	<div id="mainTabs" class="easyui-tabs" data-options="fit:true,border:false">
        		<!-- 主页 -->
        		<div title="<span class='tt-inner'><img widht='16' height='16' src='${pageContext.request.contextPath}/images/icon/home.ico'/>主页</span>" style="padding:10px;">
        			<jsp:include page="main.jsp"/>
        		</div>
           		<div title="About2" style="padding:10px;">222</div>
           		<div title="About3" style="padding:10px;" data-options="iconCls:'icon-help',closable:true">333</div>
        	</div>
        </div>
         <!-- 右边区域 
        <div data-options="region:'east',split:true" title="East" style="width:100px;"></div>-->
        <!-- 底部区域 -->
         <div data-options="region:'south',split:false" style="height:50px;">
        	<jsp:include page="bottom.jsp"/>
        </div>
    </div>
    <!-- **************主页公共部分，被嵌入的页面进行使用*************************************************  -->
		<!-- 后台管理 请求URI 前缀 隐藏表单 -->
      <input type="hidden" id="AdminRequestURI" name="AdminRequestURI" value="${AdminRequestURI}">
      <!-- 弹出form表单窗口功能 -->
      <div id="form-dialog-modal" class="easyui-window" title="" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:70%;height:420px;padding:6px;">
        <div id="form-panel" class="easyui-panel" title="" style="margin: 0px;" data-options="fit:true,border:false" ></div>
    </div>
</body>
</html>