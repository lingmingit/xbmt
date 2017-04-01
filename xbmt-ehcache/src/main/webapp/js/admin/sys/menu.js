


/****
 * 定义系统菜单列表UI 构造方法
 * 并且 继承 DataBaseListUI类
 */
function MenuListUI(name , listGridId  , editFormId){
	TreeGridBaseListUI.call(this, name , listGridId , editFormId); 
}

/****继承父类方法*******/
MenuListUI.prototype = new TreeGridBaseListUI();

/**初始化 当前列表对象 , 'editform'**/
var listUI = new MenuListUI("系统菜单" , "listDataGrid" , "editform");





/***
 * 指定列表搜索查询的条件参数对象
 */
MenuListUI.prototype.getSearchParamObject = function(){
	return {
		numbers: $('#s_numbers').val(),
		name: $('#s_name').val()
	};
};


/***
 * 重置清空查询 参数
 */
MenuListUI.prototype.resetUISearchParamValue = function(){
	$('#s_numbers').textbox('setValue' , null);
	$('#s_name').textbox('setValue' , null);
};



/***
 * 指定新增 url 地址
 * @returns {String}
 */		 
MenuListUI.prototype.getAddFormURL = function(){
	return "/sys/menu/add/form";
};


/***
 * 指定c编辑 url 地址
 * @returns {String}
 */
MenuListUI.prototype.getEditFormURL = function(){
	return "/sys/menu/edit/{id}/form";
};


/***
 * 指定查看 url 地址
 * @returns {String}
 */
MenuListUI.prototype.getViewFormURL = function(){
	return "/sys/menu/view/{id}/form";
};


/***
 * 指定 删除 请求url地址
 * @returns {String}
 */
MenuListUI.prototype.getDeleteRequestURL = function(){
	return "/sys/menu/delete/{id}";
};


/***
 * 指定 批量 删除 请求url地址
 * @returns {String}
 */
MenuListUI.prototype.getDeletesRequestURL = function(){
	return "/sys/menu/deletes/{ids}";
};


/***
 * 指定 启用 请求url地址
 * @returns {String}
 */
MenuListUI.prototype.getEnableRequestURL = function(){
	return "/sys/menu/enable/{id}";
};


/***
 * 指定 禁用 请求url地址
 * @returns {String}
 */
MenuListUI.prototype.getUnEnableRequestURL = function(){
	return "/sys/menu/unEnable/{id}";
};


/***
 * 指定 批量启用 请求url地址
 * @returns {String}
 */
MenuListUI.prototype.getEnablesRequestURL = function(){
	return "/sys/menu/enables/{ids}";
};


/***
 * 指定 批量禁用 请求url地址
 * @returns {String}
 */
MenuListUI.prototype.getUnEnablesRequestURL = function(){
	return "/sys/menu/unEnables/{ids}";
};


/**
 * 获取弹出 form window 窗口相关样式设置
 */
MenuListUI.prototype.getOpenFormWindowStyle = function(){
	return "width:70%;height:720px;padding:6px;";
};



/***
 * 格式化 模块名称
 * @param colValue 当前字段值
 * @param rowData 当前数据化记录对象
 */
MenuListUI.prototype.moduleNameFormatter = function(colValue, rowData){
	return colValue['name'];
};


/**
 * 新增、编辑、查看弹出form表单 url 追加自定义参数 (url传参数)
 * @param selectRowData 选中datagrid 数据行记录json对象
 * @param pageStatusVal 界面状态： 新增、编辑、查看
 * @param isAddChildren 是否新增下级
 * @returns 返回追加url传参数  如：number=xx&name=xx
 */
MenuListUI.prototype.getFormUrlAddParam = function(selectRowData , pageStatusVal , isAddChildren){
	//console.log("MenuListUI.prototype.getFormUrlAddParam");
	if(pageStatusVal == PageStatusObj.ADDNEW['value'] && isAddChildren && isAddChildren == true){
		if(selectRowData){
			//console.log("selectRowData['module']['id']="+selectRowData['module']['id']);
			return "moduleId="+selectRowData['module']['id']+"&pId="+selectRowData['id']+"&pNumbers="+selectRowData['longNumber']+"&pLevels="+selectRowData['levels']+"";
		}
	}
	return "";
};




/****
 * 页面 自加载
 */
$(document).ready(function() {
	
      /****
       * 跟列表grid 增加事件
       * onSelect rowIndex, rowData 在用户选择一行的时候触发，参数包括：
		 rowIndex：选择的行的索引值，索引从0开始。
         rowData：对应于所选行的记录。 
       * 
       * onDblClickRow rowIndex, rowData 在用户双击一行的时候触发，参数包括：
			rowIndex：点击的行的索引值，该索引值从0开始。
			rowData：对应于点击行的记录。 
       */
      $('#'+listUI.listGridId).datagrid({
    	  	onSelect: function(rowIndex, rowData){
    			//console.log("rowIndex="+rowIndex+" \t rowData="+rowData['id']);
    	  		listUI.listGrid_onRowSelect(rowIndex , rowData);
    		},
    		onDblClickRow: function(rowIndex, rowData){
    			//console.log("rowIndex="+rowIndex+" \t rowData="+rowData['id']);
    			listUI.listGrid_onRowDblClick(rowIndex, rowData);
    		}
    	});
      
});
