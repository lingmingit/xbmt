<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>测试 editGrid</title>
	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.3/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.3/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.3/themes/color.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui-1.4.3/demo/demo.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.3/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-1.4.3/jquery.easyui.min.js"></script>
</head>
</head>
<body>
	
	<h2>Row Editing in DataGrid</h2>
    <p>Click the row to start editing.</p>
    <div style="margin:20px 0;"></div>
    
    <table id="dg" class="easyui-datagrid" title="Row Editing in DataGrid" style="width:800px;height:300px;"
            data-options="
                iconCls: 'icon-edit',
                singleSelect: true,
                toolbar: '#tb',
                method: 'get',
                onClickCell: onClickCell
            ">
        <thead>
            <tr>
                <th data-options="field:'itemid',width:80">Item ID</th>
                <th data-options="field:'productid',width:100,
                        formatter:function(value,row){
                            return row.productname;
                        },
                        editor:{
                            type:'combobox',
                            options:{
                                valueField:'value',
                                textField:'label',
                                method:'get',
                                url:'${pageContext.request.contextPath}/test/test/functionAction',
                                required:true
                            }
                        }">Product</th>
                  <th data-options="field:'product2',width:100,
                        formatter:function(value,row){
                            return row.pname;
                        },
                        editor:{
                            type:'combobox',
                            options:{
                                valueField:'value',
                                textField:'label',
                                method:'get',
                                url:'${pageContext.request.contextPath}/test/test/functionAction',
                                required:true
                            }
                        }">Product2</th>
                <th data-options="field:'listprice',width:80,align:'right',editor:{type:'numberbox',options:{precision:1}}">List Price</th>
                <th data-options="field:'unitcost',width:80,align:'right',editor:'numberbox'">Unit Cost</th>
                <th data-options="field:'attr1',width:250,editor:'textbox'">Attribute</th>
                <th data-options="field:'status',width:60,align:'center',editor:{type:'checkbox',options:{on:'P',off:''}}">Status</th>
            </tr>
        </thead>
    </table>
 
    <div id="tb" style="height:auto">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">Append</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">Remove</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" onclick="accept()">Accept</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true" onclick="reject()">Reject</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="getChanges()">GetChanges</a>
    </div>
    
    <script type="text/javascript">
        var editIndex = undefined;
        function endEditing(){
            if (editIndex == undefined){return true}
            if ($('#dg').datagrid('validateRow', editIndex)){
            	console.log("editIndex="+editIndex);
                var ed = $('#dg').datagrid('getEditor', {index:editIndex,field:'productid'});
                var productname = $(ed.target).combobox('getText');
                $('#dg').datagrid('getRows')[editIndex]['productname'] = productname;
                
                var ed = $('#dg').datagrid('getEditor', {index:editIndex,field:'product2'});
                var pname = $(ed.target).combobox('getText');
                $('#dg').datagrid('getRows')[editIndex]['pname'] = pname;
                
                
                $('#dg').datagrid('endEdit', editIndex);
                editIndex = undefined;
                return true;
            } else {
                return false;
            }
        }
        function onClickCell(index, field){
            if (editIndex != index){
                if (endEditing()){
                    $('#dg').datagrid('selectRow', index)
                            .datagrid('beginEdit', index);
                    var ed = $('#dg').datagrid('getEditor', {index:index,field:field});
                    if (ed){
                    	console.log("22");
                        ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
                    }
                    editIndex = index;
                } else {
                    $('#dg').datagrid('selectRow', editIndex);
                }
            }
        }
        function append(){
            if (endEditing()){
                $('#dg').datagrid('appendRow',{status:'P'});
                editIndex = $('#dg').datagrid('getRows').length-1;
                $('#dg').datagrid('selectRow', editIndex)
                        .datagrid('beginEdit', editIndex);
            }
        }
        function removeit(){
            if (editIndex == undefined){return}
            $('#dg').datagrid('cancelEdit', editIndex)
                    .datagrid('deleteRow', editIndex);
            editIndex = undefined;
        }
        function accept(){
            if (endEditing()){
                $('#dg').datagrid('acceptChanges');
            }
        }
        function reject(){
            $('#dg').datagrid('rejectChanges');
            editIndex = undefined;
        }
        function getChanges(){
            var rows = $('#dg').datagrid('getChanges');
            alert(rows.length+' rows are changed!');
        }
    </script>
</body>
</html>