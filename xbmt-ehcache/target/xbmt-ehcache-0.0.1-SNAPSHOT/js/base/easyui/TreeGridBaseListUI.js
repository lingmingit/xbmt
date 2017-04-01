

/****
 * 定义基础资料树形列表UI 基类 构造方法
 * 并且 继承 TreeGridListUI类
 * 方法：新增下级方法
 */
function TreeGridBaseListUI(name , listGridId , editFormId){
	DataBaseListUI.call(this, name , listGridId , editFormId); 
}

/****继承父类方法*******/
TreeGridBaseListUI.prototype = new DataBaseListUI();


/***
 * 刷新列表数据方法
 */
TreeGridBaseListUI.prototype.refreshDataGrid = function(paramObj){
	if(!this.listGridId){
		alert("请通过构造方法初始化listGridId 参数");
		return;
	}
	//console.log("paramObj="+paramObj);
	$('#'+this.listGridId).treegrid('unselectAll');
	if(paramObj){
		
		$('#'+this.listGridId).treegrid('reload' , paramObj);
	}else{
		$('#'+this.listGridId).treegrid('reload');
	}
};


/***
 * 指定【新增下级界面】url地址
 **/
TreeGridBaseListUI.prototype.getAddChildrenFormURL = function(){
	return this.getAddFormURL();
};


/***
 * 验证是否允许新增下级
 * @param selectRowRecord 选中行记录
 * @return true 允许，false 不允许
 */
TreeGridBaseListUI.prototype.validateAddChildren = function(selectRowRecord){
	if(selectRowRecord){
		if(selectRowRecord["isLeaf"] == true){
			this.showError("错误","当前为叶子节点不允许新增下级!!");
			return false;
		}
	}
	return true;
};




/***
 * 列表【新增下级按钮】处理方法
 */
TreeGridBaseListUI.prototype.addChildrenRecord = function(){
	if(typeof(this.getAddChildrenFormURL) != 'function'){
		alert("请通过function[getAddChildrenFormURL]指定新增url");
		return;
	}
	var formUrl = this.getAddChildrenFormURL();
	if(formUrl == null || formUrl == ""){
		alert("function[getAddChildrenFormURL]指定新增url为空!!");
		return;
	}
	//获取选中行数据
	var selectedRowRecord = this.getSelectedRowData();
	if(!this.validateAddChildren(selectedRowRecord)){
		return;
	}
	//解决 this 作用域问题
	var that = this;
	//判断是否选中行记录
	if(!this.checkRowSelected(false)){
		this.showConfirm("确认提示", "您未选中父级，将默认新增父级菜单，确认吗？", function(v){
			if(v){
				//弹出窗口
				that.openOmDialogForm(formUrl , PageStatusObj.ADDNEW['value'] , true);
			}
		});
	}else{
		//弹出窗口
		that.openOmDialogForm(formUrl , PageStatusObj.ADDNEW['value'] , true);
	}
};
