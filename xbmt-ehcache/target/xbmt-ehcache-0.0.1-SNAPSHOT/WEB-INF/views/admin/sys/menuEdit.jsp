<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>系统模块编辑</title>
</head>
<body style="margin-top:0px;margin-left: 0px;">
	<!-- 导入功能js -->
	<jsp:include page="../common/common_edit.jsp"/>
	 <!-- 前台js UI抽象类 导入 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/easyui/CoreUI.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/easyui/CoreEditUI.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/easyui/BaseEditUI.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/easyui/BaseEntryEditUI.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/easyui/DataBaseEntryEditUI.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/sys/menuEdit.js"> </script>
	<div class="right clear" >
    	<div id="nav_cont">
                <!--  <h1 id="tag_1" class="cont_tag_show"></h1> -->
                 <form id="editform" method="post" action="${pageContext.request.contextPath}${AdminRequestURI}/sys/menu/save">
                 	 <input type="hidden" id="pageStatus" name="pageStatus" value="${pageStatus}">
                 	 <input type="hidden" id="id" name="id" value="${data.id}">
					 <input type="hidden" id="token" name="token" value="${token}" />
					 <input type="hidden" id="levels" name="levels" value="${data.levels}">
                	 <input type="hidden" id="method" name="method" value="formValidateSubmit">
                	 
                	  <input type="hidden" id="moduleId" name="moduleId" value="${moduleId}" />
                	 <input type="hidden" id="pId" name="pId" value="${pId}" />
					 <input type="hidden" id="pNumbers" name="pNumbers" value="${pNumbers}">
                	 <input type="hidden" id="pLevels" name="pLevels" value="${pLevels}">
                	 <!-- 分录数据隐藏表单 -->
                	  <input type="hidden" id="addedEntryRows" name="addedEntryRows"  />
					 <input type="hidden" id="deletedEntryRows" name="deletedEntryRows" >
                	 <input type="hidden" id="updatedEntryRows" name="updatedEntryRows">
                	 
                   <table width="100%" border="0" class="grid_layout" cellspacing="0">
	                  <tr class="style1">
	                    <td width="10%" align="right"><span class="color_red">*</span>编码：</td>
	                    <td width="35%" colspan="3">
	                      <input type="text" name="numbers" id="numbers" class="easyui-textbox" maxlength="10" data-options="required:true" style="width: 60%;"  value="${data.numbers}" />
	                    </td>
	                    <td width="10%" align="right"><span class="color_red">*</span>名称：</td>
	                    <td width="35%" colspan="3">
	                       <input type="text" id="name" name="name" class="easyui-textbox" data-options="required:true" style="width: 60%;" value="${data.name}"/>
	                    </td>
	                  </tr>
	                  
	                  <tr>
	                    <td align="right"><span class="color_red">*</span>序号：</td>
	                    <td colspan="3"><input type="text" name="seqNo" id="seqNo" class="easyui-numberbox" data-options="required:true" style="width: 60%;" value="${data.seqNo}" />
	                    </td>
	                    <td align="right"><span class="color_red"></span>图标：</td>
	                    <td colspan="3">
	                    	   <input type="text" id="menuIcon" name="menuIcon" class="easyui-textbox" data-options="" style="width: 60%;" value="${data.menuIcon}"/>
	                    </td>
	                  </tr>
	                  <tr>
	                  		<td align="right"><span class="color_red">*</span>所属模块：</td>
		                    <td colspan="3">
		                        <input class="easyui-combobox" id="module" name="module.id" data-options="required:true,valueField:'value',textField:'label',url:'${pageContext.request.contextPath}${AdminRequestURI}/dataItem/modules',method:'get',onSelect:editUI.mouduleOnselect"  value="${data.module.id}">
		                    </td>
		                    <td align="right"><span class="color_red"></span>上级菜单：</td>
		                    <td colspan="3">
		                        <input class="easyui-combobox" id="parent" name="parent.id" data-options="valueField:'value',textField:'label',url:'${pageContext.request.contextPath}${AdminRequestURI}/dataItem/menu',method:'get'" value="${data.parent.id}">
		                    </td>
		               </tr>
	                    <tr>
	                    	<td align="right"><span class="color_red"></span>叶子：</td>
		                    <td colspan="3">
		                        <input type="checkbox" id="isLeaf" name="isLeaf" onchange="" value="true">
		                    </td>
		                    <td align="right"><span class="color_red"></span>长编码：</td>
		                    <td colspan="3">
		                        <input id="longNumber" name="longNumber" class="easyui-textbox" data-options="readonly:true" value="${data.longNumber}">
		                    </td>
		               </tr>
		               <tr>
		               		<td align="right"><span class="color_red">*</span>菜单URL：</td>
		                    <td colspan="7">
		                        <input type="text" maxlength="5" name="url" id="url" class="easyui-textbox" data-options="disabled:true,validType:'maxLength[100]'"  style="width: 70%;" value="${data.url}"/>
		                    </td>
		               </tr>
		                <tr>
		                    <td align="right"><span class="color_red"></span>备用URL：</td>
		                    <td colspan="7">
		                        <input type="text" maxlength="5" name="backUrl" id="backUrl" class="easyui-textbox" data-options="validType:'maxLength[100]'"  style="width: 70%;" value="${data.backUrl}"/>
		                    </td>
		               </tr>
                   <%--  <tr>
                    <td align="right">备注：</td>
                    <td colspan="7">
                      <input type="text" name="description" id="description" maxlength="5" class="easyui-textbox" data-options="multiline:true,validType:'maxLength[100]'"  style="width:95.8%;height: 50px;" value="${data.description}"  />
                      <div style="width: 100%" id="count"></div>
                    </td>
                    </tr> --%>
                    <tr>
                    	<td align="right"></td>
                    	<td colspan="7">
                    		<!-- 分录 -->
								<table id="functionActionEntry" class="easyui-datagrid" title="" style="width:700px;height:200px"
									data-options="rownumbers:true,
										iconCls: 'icon-edit',
										singleSelect: true,
										toolbar: '#entrytb',
										onClickRow: editUI.entryGrid_onClickCell">
								<thead>
									<tr>
										<th data-options="field:'id',width:80">ID</th>
										<th data-options="field:'action',width:100,
												formatter:function(value,row,index){
													if(row.aName){
														return row.aName;
													}
													if(typeof(row.action) == 'object'){
														return row.action['name'];
													}
													if(typeof(row.action) == 'string'){
														return row.action;
													}
													return null;
												},
												editor:{
													type:'combobox',
													options:{
														valueField:'value',
														textField:'label',
														method:'get',
														url:'${pageContext.request.contextPath}${AdminRequestURI}/dataItem/functionAction',
														required:true
													}
												}">功能名称</th>
										<th data-options="field:'buttonIdName',width:80,align:'right',editor:'textbox'">buttonId</th>
										<th data-options="field:'actionName',width:80,align:'right',editor:'textbox'">action名称</th>
									</tr>
								</thead>
							</table>
							
                    	</td>
                    </tr>
	            </table>
	            
				<div id="entrytb" style="height:auto">
					<a href="javascript:void(0)" class="easyui-linkbutton" id="btnAddLine" data-options="iconCls:'icon-add',plain:true" onclick="editUI.appendEntryRow();">Append</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" id="btnRemoveLine" data-options="iconCls:'icon-remove',plain:true" onclick="editUI.removeEntryRow();">Remove</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" id="btnAccept" data-options="iconCls:'icon-save',plain:true" onclick="editUI.entryAccept();">Accept</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" id="btnReject" data-options="iconCls:'icon-undo',plain:true" onclick="editUI.entryReject();">Reject</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" id="btnGetChanges" data-options="iconCls:'icon-search',plain:true" onclick="editUI.getEntryChange();">GetChanges</a>
				</div>
	            <div class="separator"></div>
	            <div class="text_align_c pad ">
		    	     <a href="javascript:void(0)" id="btnSave" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="editUI.saveForm()" style="width:90px">保存</a>
       				 <a href="javascript:void(0)" id="btnCancel" class="easyui-linkbutton" iconCls="icon-cancel" onclick="editUI.closeFormWindow();" style="width:90px">取消</a>
		        </div>
	        </form>
    	</div>
	</div>
</body>
</html>