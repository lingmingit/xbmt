


/****
 * 定义系统模块列表UI 构造方法
 * 并且 继承 DataBaseListUI类
 */
function ModulesListUI(name , listGridId  , editFormId){
	DataBaseListUI.call(this, name , listGridId , editFormId); 
}

/****继承父类方法*******/
ModulesListUI.prototype = new DataBaseListUI();

/**初始化 当前列表对象 , 'editform'**/
var listUI = new ModulesListUI("系统模块" , "listDataGrid" , "editform");





/***
 * 指定列表搜索查询的条件参数对象
 */
ModulesListUI.prototype.getSearchParamObject = function(){
	return {
		numbers: $('#s_numbers').val(),
		name: $('#s_name').val()
	};
};


/***
 * 重置清空查询 参数
 */
ModulesListUI.prototype.resetUISearchParamValue = function(){
	$('#s_numbers').textbox('setValue' , null);
	$('#s_name').textbox('setValue' , null);
};



/***
 * 指定新增 url 地址
 * @returns {String}
 */		 
ModulesListUI.prototype.getAddFormURL = function(){
	return "/sys/modules/add/form";
};


/***
 * 指定c编辑 url 地址
 * @returns {String}
 */
ModulesListUI.prototype.getEditFormURL = function(){
	return "/sys/modules/edit/{id}/form";
};


/***
 * 指定查看 url 地址
 * @returns {String}
 */
ModulesListUI.prototype.getViewFormURL = function(){
	return "/sys/modules/view/{id}/form";
};


/***
 * 指定 删除 请求url地址
 * @returns {String}
 */
ModulesListUI.prototype.getDeleteRequestURL = function(){
	return "/sys/modules/delete/{id}";
};


/***
 * 指定 批量 删除 请求url地址
 * @returns {String}
 */
ModulesListUI.prototype.getDeletesRequestURL = function(){
	return "/sys/modules/deletes/{ids}";
};


/***
 * 指定 启用 请求url地址
 * @returns {String}
 */
ModulesListUI.prototype.getEnableRequestURL = function(){
	return "/sys/modules/enable/{id}";
};


/***
 * 指定 禁用 请求url地址
 * @returns {String}
 */
ModulesListUI.prototype.getUnEnableRequestURL = function(){
	return "/sys/modules/unEnable/{id}";
};


/***
 * 指定 批量启用 请求url地址
 * @returns {String}
 */
ModulesListUI.prototype.getEnablesRequestURL = function(){
	return "/sys/modules/enables/{ids}";
};


/***
 * 指定 批量禁用 请求url地址
 * @returns {String}
 */
ModulesListUI.prototype.getUnEnablesRequestURL = function(){
	return "/sys/modules/unEnables/{ids}";
};






/**
 * 获取弹出 form window 窗口相关样式设置
 */
ModulesListUI.prototype.getOpenFormWindowStyle = function(){
	return "width:50%;height:280px;padding:6px;";
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
