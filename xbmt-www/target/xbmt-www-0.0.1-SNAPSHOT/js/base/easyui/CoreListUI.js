
/****
 * 定义列表UI 基类 构造方法
 * 方法：列表条件查询重置、判断是否选中行、获取列表选中数据行记录、刷新grid、行选中事件、双击数据行事件
 * 并且 继承 CoreUI类
 */
function CoreListUI(name , listGridId){
	CoreUI.call(this, name); 
	this.listGridId = listGridId;
}

/****继承父类方法*******/
CoreListUI.prototype = new CoreUI();  //这行的作用是：将Parent中将所有通过prototype追加的属性和方法都追加到Child，从而实现了继承



/***
 * 指定列表 查询 条件 json对象，如：{number:'' , name , ''}
 */
CoreListUI.prototype.getSearchParamObject = function(){
	return null;
};

/***
 * 重置 查询条件 前台UI 控件值
 */
CoreListUI.prototype.resetUISearchParamValue = function(){
	return null;
};

/***
 * 搜索查询 方法
 */
CoreListUI.prototype.searchAction = function(){
	this.refreshDataGrid(this.getSearchParamObject());
};

/***
 * 重置查询条件 方法
 */
CoreListUI.prototype.resetAction = function(){
	this.resetUISearchParamValue();
	this.searchAction();
};

/***
 * 刷新列表数据方法
 */
CoreListUI.prototype.refreshDataGrid = function(paramObj){
	if(!this.listGridId){
		alert("请通过构造方法初始化listGridId 参数");
		return;
	}
	//console.log("paramObj="+paramObj);
	$('#'+this.listGridId).datagrid('unselectAll');
	if(paramObj){
		
		$('#'+this.listGridId).datagrid('reload' , paramObj);
	}else{
		$('#'+this.listGridId).datagrid('reload');
	}
};

/***
 * 刷新列表数据方法
 * js 不支持重载方法
CoreListUI.prototype.refreshDataGrid = function(){
	console.log("refreshDataGrid=");
	$('#'+this.listGridId).datagrid('reload');
};
*/


/***
 * 检查是否选中行记录
 */
CoreListUI.prototype.checkRowSelected = function(isPrompt){
	return easyuiCommon.checkGridRowSelected(this.listGridId , isPrompt);
};



/***
 * 检查是否选中多条记录
 */
CoreListUI.prototype.checkRowSelected = function(isPrompt){
	return easyuiCommon.checkGridRowSelected(this.listGridId , isPrompt);
};

/***
 * 检查数据列表 grid是否选中多行
 * @param gridId 数据列表id
 * @param isPrompt 是否弹出提示消息
 * @return 返回 false未选中 或 选中多条记录， true 选中单条记录
 */
CoreListUI.prototype.checkGridMultipleRowsSelected = function(){
	return easyuiCommon.checkGridMultipleRowsSelected(this.listGridId , isPrompt);
};


/***
 * 获取grid 数据选中首行
 */
CoreListUI.prototype.getSelectedRowData = function(){
	return easyuiCommon.getSelectedRowData(this.listGridId);
};

/***
 * 获取grid 数据选中所有行
 */
CoreListUI.prototype.getSelectedRowsArrayData = function(){
	return easyuiCommon.getSelectedRowsArrayData(this.listGridId);
};

/****
 * datagrid 行选中事件【btnAdd、btnEdit、btnDelete、btnEnable、btnUnEnable】
* @param rowIndex - 行号（从0开始）
* @param rowData - 选择的行所代表的JSON对象
 */
CoreListUI.prototype.listGrid_onRowSelect = function(rowIndex , rowData){
};


/***
 * onDblClickRow rowIndex, rowData 在用户双击一行的时候触发，参数包括：
 * @param rowIndex 点击的行的索引值，该索引值从0开始。
 * @param rowData 对应于点击行的记录。 
 */
CoreListUI.prototype.listGrid_onRowDblClick = function(rowIndex , rowData){
	
};
