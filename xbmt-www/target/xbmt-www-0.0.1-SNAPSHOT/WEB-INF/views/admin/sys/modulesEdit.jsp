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
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/easyui/DataBaseEditUI.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/sys/modulesEdit.js"></script>
	
	<div class="right clear" >
    	<div id="nav_cont">
                <!--  <h1 id="tag_1" class="cont_tag_show"></h1> -->
                 <form id="editform" method="post" action="${pageContext.request.contextPath}${AdminRequestURI}/sys/modules/save">
                 	 <input type="hidden" id="pageStatus" name="pageStatus" value="${pageStatus}">
                 	 <input type="hidden" id="id" name="id" value="${data.id}">
					 <input type="hidden" id="token" name="token" value="${token}" />
                	 <input type="hidden" id="method" name="method" value="formValidateSubmit">
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
	                    	   <input type="text" id="moduleIcon" name="moduleIcon" class="easyui-textbox" data-options="" style="width: 60%;" value="${data.moduleIcon}"/>
	                    </td>
	                  </tr>
	                    <tr>
		                    <td align="right"><span class="color_red"></span>模块URL：</td>
		                    <td colspan="7">
		                        <input type="text" maxlength="5" name="moduleURL" id="moduleURL" class="easyui-textbox" data-options="validType:'maxLength[100]'"  style="width: 70%;" value="${data.moduleURL}"/>
		                    </td>
		               </tr>
                    <tr>
                    <td align="right">备注：</td>
                    <td colspan="7">
                      <input type="text" name="description" id="description" maxlength="5" class="easyui-textbox" data-options="multiline:true,validType:'maxLength[100]'"  style="width:95.8%;height: 50px;" value="${data.description}"  />
                      <div style="width: 100%" id="count"></div>
                    </td>
                    </tr>
	            </table>

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