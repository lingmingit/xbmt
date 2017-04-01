<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>用户管理</title>
	<!-- 导入功能js -->
	<jsp:include page="../common/common_list.jsp"/>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/easyui/CoreListUI.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/easyui/BaseListUI.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/easyui/DataBaseListUI.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/sys/user.js"> </script>
</head>
<body>
	
	<!-- 列表grid -->
	    <table id="listDataGrid" class="easyui-datagrid" title="用户管理" 
            data-options="fit:true,rownumbers:true,pagination:true,singleSelect:true,url:'${pageContext.request.contextPath}${AdminRequestURI}/sys/user/list',method:'post',toolbar:'#tb'">
        <thead>
            <tr>
                <th data-options="field:'id',width:30">ID</th>
                <th data-options="field:'numbers',width:80">账户</th>
                <th data-options="field:'name',width:80,align:'left'">姓名</th>
                <th data-options="field:'isEnable',width:80,align:'center',formatter:easyuiCommon.enableFormatter">启用</th>
                <th data-options="field:'birthday',width:100,align:'center'">生日</th>
                <th data-options="field:'age',width:40,align:'center'">年龄</th>
                <th data-options="field:'sex',width:40,align:'center',formatter:easyuiCommon.sexFormatter">性别</th>
                <th data-options="field:'email',width:120,align:'left'">电子邮件</th>
                <th data-options="field:'telephone',width:100,align:'center'">电话</th>
                <th data-options="field:'mobilePhone',width:100,align:'center'">手机</th>
                <th data-options="field:'qq',width:80,align:'left'">QQ</th>
                <th data-options="field:'homeAddress',width:150,align:'left'">现住址</th>
                <th data-options="field:'description',width:240,align:'left'">备注</th>
                <th data-options="field:'creatorName',width:80,align:'center'">创建人</th>
                <th data-options="field:'createTime',width:130,align:'center',formatter:easyuiCommon.dateTimeFormatter">创建时间</th>
                <th data-options="field:'modifierName',width:60,align:'center'">修改人</th>
                <th data-options="field:'modifyTime',width:130,align:'center',formatter:easyuiCommon.dateTimeFormatter">修改时间</th>
            </tr>
        </thead>
    </table> 
    
    <!-- 列表 查询过滤 -->
      <div id="tb" style="padding:2px 5px;">
                       账户: <input class="easyui-textbox" id="s_numbers" name="s_numbers" style="width:110px">
                      姓名: <input class="easyui-textbox" id="s_name" name="s_name" style="width:110px">
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="listUI.searchAction();">Search</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-redo" onclick="listUI.resetAction();">Reset</a>
        <div id="p" class="easyui-panel" title="" style="width:100%;height:30px;">
	        <a href="#" id="btnAdd" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="listUI.addRecord();">新增</a>
	        <a href="#" id="btnEdit" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="listUI.editRecord();">编辑</a>
	        <a href="#" id="btnView" class="easyui-linkbutton"  plain="true"  onclick="listUI.viewRecord();">查看</a>
	        <a href="#" id="btnDelete" class="easyui-linkbutton" iconCls="icon-remove" plain="true"  onclick="listUI.deleteRecord();">删除</a>
	        <a href="#" id="btnEnable" class="easyui-linkbutton"  plain="true" onclick="listUI.enableRecord();">启用</a>
	        <a href="#" id="btnUnEnable" class="easyui-linkbutton"  plain="true" onclick="listUI.unEnableRecord();">禁用</a>
	        <a href="#" id="btnDeletes" class="easyui-linkbutton"  plain="true" onclick="listUI.deletesRecord();">批量删除</a>
	        <a href="#" id="btnEnables" class="easyui-linkbutton"  plain="true" onclick="listUI.enablesRecord();">批量启用</a>
	        <a href="#" id="btnUnEnables" class="easyui-linkbutton"  plain="true" onclick="listUI.unEnablesRecord();">批量禁用</a>
	    </div>
    </div>
    
    <!-- 编辑界面弹出窗口 
    <div id="form-dialog-modal" class="easyui-window" title="用户管理" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:70%;height:420px;padding:6px;">
       <div id="form-panel" class="easyui-panel" title="" style="margin: 0px;" data-options="fit:true,border:false" ></div>
    </div>-->
</body>
</html>