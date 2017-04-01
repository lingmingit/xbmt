<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>系统模块管理</title>
	 	<!-- 导入功能js -->
		<jsp:include page="../common/common_list.jsp"/>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/easyui/CoreListUI.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/easyui/BaseListUI.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/easyui/DataBaseListUI.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/easyui/TreeGridBaseListUI.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/sys/menu.js"> </script>
</head>
<body>
	<!-- 列表grid -->
	    <table id="listDataGrid" class="easyui-treegrid" title="系统菜单" 
            data-options="fit:true,idField:'id',treeField: 'name',rownumbers:true,singleSelect:true,pagination:false,url:'${pageContext.request.contextPath}${AdminRequestURI}/sys/menu/list',method:'post',toolbar:'#tb'">
        <thead>
            <tr>
            	<!-- <th data-options="field:'ck',checkbox:true"></th> -->
                <!-- <th data-options="field:'id',width:30">ID</th> -->
                <th data-options="field:'name',width:200,align:'left'">名称</th>
                <th data-options="field:'module.name',width:100">所属模块</th><!-- ,formatter:listUI.moduleNameFormatter -->
                <th data-options="field:'numbers',width:80">编码</th>
                <th data-options="field:'longNumber',width:80">长编码</th>
                <th data-options="field:'isEnable',width:80,align:'center',formatter:easyuiCommon.enableFormatter">启用</th>
                 <th data-options="field:'isLeaf',width:80,align:'center',formatter:easyuiCommon.enableFormatter">叶子</th>
                <th data-options="field:'seqNo',width:30,align:'center'">序号</th>
                <th data-options="field:'moduleIcon',width:50,align:'center',formatter:easyuiCommon.iconFormatter">图标</th>
                <th data-options="field:'url',width:150,align:'left'">URL</th>
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
                      编码: <input class="easyui-textbox" id="s_numbers" name="s_numbers" style="width:110px">
                      名称: <input class="easyui-textbox" id="s_name" name="s_name" style="width:110px">
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="listUI.searchAction();">Search</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-redo" onclick="listUI.resetAction();">Reset</a>
        <div id="p" class="easyui-panel" title="" style="width:100%;height:30px;">
	        <a href="#" id="btnAdd" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="listUI.addRecord();">新增</a>
	        <a href="#" id="btnAddChildren" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="listUI.addChildrenRecord();">新增下级</a>
	        <a href="#" id="btnEdit" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="listUI.editRecord();">编辑</a>
	        <a href="#" id="btnView" class="easyui-linkbutton"  plain="true"  onclick="listUI.viewRecord();">查看</a>
	        <a href="#" id="btnDelete" class="easyui-linkbutton" iconCls="icon-remove" plain="true"  onclick="listUI.deleteRecord();">删除</a>
	        <a href="#" id="btnEnable" class="easyui-linkbutton"  plain="true" onclick="listUI.enableRecord();">启用</a>
	        <a href="#" id="btnUnEnable" class="easyui-linkbutton"  plain="true" onclick="listUI.unEnableRecord();">禁用</a>
	        <a href="#" id="btnDeletes" class="easyui-linkbutton"  plain="true" onclick="listUI.deletesRecord();">批量删除</a>
	    </div>
    </div>
    
</body>
</html>