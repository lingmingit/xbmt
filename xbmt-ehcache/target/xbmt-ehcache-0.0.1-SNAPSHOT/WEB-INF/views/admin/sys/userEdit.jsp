<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>用户编辑</title>
	
</head>
<body style="margin-top:0px;margin-left: 0px;">

	<!-- 导入功能js -->
	<jsp:include page="../common/common_edit.jsp"/>
	 <!-- 前台js UI抽象类 导入 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/base/easyui/CoreUI.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/easyui/CoreEditUI.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/easyui/BaseEditUI.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/base/easyui/DataBaseEditUI.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/admin/sys/userEdit.js"></script>
    
	<div class="right clear" >
    	<div id="nav_cont">
                <!--  <h1 id="tag_1" class="cont_tag_show"></h1> -->
                 <form id="editform" method="post" action="${pageContext.request.contextPath}${AdminRequestURI}/sys/user/save">
                 	 <input type="hidden" id="pageStatus" name="pageStatus" value="${pageStatus}">
                 	 <input type="hidden" id="id" name="id" value="${data.id}">
					 <input type="hidden" id="token" name="token" value="${token}" />
                	 <input type="hidden" id="method" name="method" value="formValidateSubmit">
                   <table width="100%" border="0" class="grid_layout" cellspacing="0">
	                  <tr class="style1">
	                    <td width="10%" align="right"><span class="color_red">*</span>用户名：</td>
	                    <td width="35%" colspan="3">
	                      <input type="text" name="numbers" id="numbers" class="easyui-textbox" maxlength="10" data-options="required:true" style="width: 50%;"  value="${data.numbers}" />
	                    </td>
	                    <td width="10%" align="right"><span class="color_red">*</span>姓名：</td>
	                    <td width="35%" colspan="3">
	                       <input type="text" id="name" name="name" class="easyui-textbox" data-options="required:true" style="width: 50%;" value="${data.name}"/>
	                    </td>
	                  </tr>
	                  <tr>
	                    <td align="right"><span class="color_red">*</span>初始密码：</td>
	                    <td colspan="3"><input type="password" name="password" id="password" class="easyui-textbox" data-options="required:true" style="width: 50%;" value="${data.password}" />
	                    </td>
	                    <td align="right"><span class="color_red">*</span>性别：</td>
	                    <td colspan="3">
	                    	   <select class="easyui-combobox" name="sex" id="sex"  style="width: 50%;" editable="false" data-options="required:true" >
							        <option value="1">男</option>
							        <option value="0">女</option>
							   </select>
							   <input type="hidden" id="sexHide" value="${data.sex}">
	                    </td>
	                  </tr>
	                  <tr>
	                  	<td align="right"><span class="color_red">*</span>出生日期：</td>
	                    <td colspan="3">
	                    	<input type="text" name="birthday" id="birthday"  class="easyui-datebox" style="width: 50%;" value="${data.birthday}" data-options="required:true,formatter:easyuiCommon.myformatter,parser:easyuiCommon.myparser,validType:'date'" />
	                    </td>
	                    <td align="right"><span class="color_red">*</span>年龄：</td>
		                    <td colspan="3">
		                      <input type="text" name="age" id="age" class="easyui-numberbox" class="easyui-numberbox" data-options="min:0,max:100,required:true" style="width: 50%;" value="${data.age}"/>
		                </td>
	                    </tr>
	                      <tr>
	                    <td colspan="8" align="right">
	                    	<div class="dashed"></div>
	                    </td>
	                    </tr>
	                    
	                    <tr>
	                    	 <td align="right"><span class="color_red"></span>电话：</td>
		                    <td colspan="3">
		                        <input name="telephone" id="telephone"  class="easyui-validatebox textbox" style="width: 50%;" value="${data.telephone}" data-options="validType:'phone'"/>
		                    </td>
		                    
		                    <td align="right"><span class="color_red">*</span>手机：</td>
		                    <td  colspan="3">
		                        <input type="text"   id="mobilePhone" name="mobilePhone" class="easyui-validatebox textbox" style="width: 50%;" value="${data.mobilePhone}" data-options="required:true,validType:'mobile'"/>
		                    </td>
		               </tr>
		               <tr>
		               		<td align="right">QQ：</td>
		                    <td colspan="3">
		                        <input name="qq" id="qq"   class="easyui-validatebox textbox" style="width: 50%;" value="${data.qq}" data-options="validType:'qq'"/>
		                    </td>
		                    <td align="right">电子邮件：</td>
		                    <td  colspan="3">
		                        <input type="text" id="email" name="email" class="easyui-validatebox textbox" style="width: 50%;" value="${data.email}"  data-options="prompt:'Enter a valid email.',validType:'email'"/>
		                    </td>
	                  </tr>
	                  
	                    <tr>
		                    <td align="right"><span class="color_red">*</span>现住址：</td>
		                    <td colspan="7">
		                        <input type="text" name="homeAddress" id="homeAddress" class="easyui-textbox" required="true" style="width: 50%;" value="${data.homeAddress}"/>
		                    </td>
		               </tr>
                    <tr>
                    <td align="right">备注：</td>
                    <td colspan="7">
                      <textarea name="description" id="description" maxlength="100" class="easyui-validatebox textbox" data-options="multiline:true,"  style="width:95.8%;height: 50px;" >${data.description}</textarea>
                      <span class="errorMsg"></span>
                      <div style="width: 100%" id="count"></div>
                    </td>
                    </tr>
	            </table>

	            <div class="separator"></div>
	              <div class="text_align_c pad ">
		    	     <a href="javascript:void(0)" id="btnSave" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="editUI.saveForm();" style="width:90px">保存</a>
       				 <a href="javascript:void(0)" id="btnCancel" class="easyui-linkbutton" iconCls="icon-cancel" onclick="editUI.closeFormWindow();" style="width:90px">取消</a><!-- listUI.closeFormWindow(); -->
		        </div>
	        </form>
    	</div>
	</div>
</body>
</html>