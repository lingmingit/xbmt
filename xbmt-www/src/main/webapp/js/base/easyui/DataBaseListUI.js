


/****
 * 定义基础资料列表UI 基类 构造方法
 * 并且 继承 BaseListUI类
 * 方法：启用、禁用、验证启用|禁用、grid数据行选中控制工具栏按钮状态
 */
function DataBaseListUI(name , listGridId , editFormId){
	BaseListUI.call(this, name , listGridId , editFormId); 
}

/****继承父类方法*******/
DataBaseListUI.prototype = new BaseListUI();


/***
 * 指定【启用请求】url地址
 */
DataBaseListUI.prototype.getEnableRequestURL = function(){
	return null;
};
/***
 * 指定【禁用请求】url地址
 */
DataBaseListUI.prototype.getUnEnableRequestURL = function(){
	return null;
};


/***
 * 指定【批量启用请求】url地址
 */
DataBaseListUI.prototype.getEnablesRequestURL = function(){
	return null;
};
/***
 * 指定【批量禁用请求】url地址
 */
DataBaseListUI.prototype.getUnEnablesRequestURL = function(){
	return null;
};


/***
 * 验证是否允许编辑
 * @param selectRowRecord 选中行记录
 * @return true 允许，false 不允许
 */
DataBaseListUI.prototype.validateEdit = function(selectRowRecord){
	var isSystem = selectRowRecord["isSystem"];
	if(isSystem){
		this.showError("错误","系统默认记录，不能编辑!!");
		return false;
	}
	var isEnable = selectRowRecord["isEnable"];
	if(isEnable){
		this.showError("错误","记录启用，不能编辑!!");
		return false;
	}
	return true;
};

/***
 * 验证是否允许删除
 * @param selectRowRecord 选中行记录
 * @param isPrompt 是否提示错误消息
 * @param paramObj 验证消息对象，如：{msg:''}
 * @return true 允许，false 不允许
 */
DataBaseListUI.prototype.validateDelete = function(selectRowRecord , isPrompt , paramObj){
	var isSystem = selectRowRecord["isSystem"];
	if(isSystem){
		if(isPrompt == true){
			this.showError("错误","系统默认记录，不能删除!!");
		}
		paramObj.msg = selectRowRecord['numbers']+"系统默认记录，不能删除!!";
		return false;
	}
	var isEnable = selectRowRecord["isEnable"];
	if(isEnable){
		if(isPrompt == true){
			this.showError("错误","记录启用，不能删除!!");
		}
		paramObj.msg = selectRowRecord['numbers']+"记录启用，不能删除!!";
		return false;
	}
	return true;
};



/***
 * 验证是否允许启用
 * @param selectRowRecord 选中行记录
 * @return true 允许，false 不允许
 */
DataBaseListUI.prototype.validateEnable = function(selectRowRecord , isPrompt , paramObj){
	var isEnable = selectRowRecord["isEnable"];
	if(isEnable){
		if(isPrompt == true){
			this.showError("错误","已经启用，不能再启用!!");
		}
		paramObj.msg = selectRowRecord['numbers']+"已经启用，不能再启用!!";
		return false;
	}
	return true;
};

/***
 * 验证是否允许禁用
 * @param selectRowRecord 选中行记录
 * @return true 允许，false 不允许
 */
DataBaseListUI.prototype.validateUnEnable = function(selectRowRecord , isPrompt , paramObj){
	var isSystem = selectRowRecord["isSystem"];
	if(isSystem){
		if(isPrompt == true){
			this.showError("错误","系统默认记录，不能禁用!!");
		}
		paramObj.msg = selectRowRecord['numbers']+"系统默认记录，不能禁用!!";
		return false;
	}
	var isEnable = selectRowRecord["isEnable"];
	if(!isEnable){
		if(isPrompt == true){
			this.showError("错误","已经禁用，不能再禁用!!");
		}
		paramObj.msg = selectRowRecord['numbers']+"已经禁用，不能再禁用!!";
		return false;
	}
	return true;
};



/***
 * 列表【启用按钮】处理方法
 */
DataBaseListUI.prototype.enableRecord = function(){
	var requestURL = this.getEnableRequestURL();
	if(requestURL == null || requestURL == ""){
		alert("function[getEnableRequestURL]指定启用url为空!!");
		return;
	}
	//判断是否选中行记录
	if(!this.checkRowSelected(true)){
		return;
	}
	//获取选中行数据
	var selectedRowRecord = this.getSelectedRowData();
	if(!this.validateEnable(selectedRowRecord , true , {})){
		return;
	}
	//获取指定 删除请求url 地址
	var url = easyuiCommon.getURLRegExpMatchParam(requestURL , selectedRowRecord);
	if(!url || url == null || url == ""){
		return;
	}
	//项目根目录 +  请求前缀URI + 具体请求地址
	url = easyuiCommon.getWebRootPath()+ this.getRequestURI() + url;
	//解决 this 作用域问题
	var that = this;
	easyuiCommon.ajaxGetSubmitOperate(url , {} , function(url , param , result){
		that.showSuccess("成功" , result.resultMsg , function(){
			 that.refreshDataGrid();
		  });
	} , function(url , param , result){
		that.showError("失败" , result.resultMsg);
	});
};



/***
 * 列表【批量启用按钮】处理方法
 */
DataBaseListUI.prototype.enablesRecord = function(){
	var requestURL = this.getEnablesRequestURL();
	if(requestURL == null || requestURL == ""){
		alert("function[getEnablesRequestURL]指定批量启用url为空!!");
		return;
	}
	//判断是否选中行记录
	if(!this.checkRowSelected(true)){
		return;
	}
	//错误消息
	var errorMsg = "";
	//获取选中批量行数据
	var selectedArray = this.getSelectedRowsArrayData();
	var totalCount = selectedArray.length;
	//需要删除的 数组 array
	var deleteArray = [];
	var index = 0;
	for(var i = 0 ; i < totalCount ; i++){
		var paramObj = {};
		if(!this.validateEnable(selectedArray[i] , false , paramObj)){
			errorMsg += (paramObj.msg+"<br/>");
			//selectedArray.splice(i , 1);//  验证失败的元素对象
			//2015-11-24由于使用数组splice删除方法存在问题(导致第二次获取grid选中数据不正确)，直接换另一种方法处理
		}else{
			deleteArray[index++] = selectedArray[i];//设置需要删除的元素
		}
	}
	if(deleteArray.length == 0){
		this.showError("错误", "您勾选的记录都不能启用,请重新选择!!<br/>详细信息："+errorMsg);
		return;
	}
	var msgContent = "您确认启用勾选记录吗?";
	var passCount = deleteArray.length;//通过删除验证的数量
	var notPassCount = totalCount - passCount;
	if(notPassCount > 0){
		msgContent = "您总共勾选"+totalCount+"条记录，其中有"+notPassCount+"条不能启用，将被忽略，实际提交"+passCount+"条，您确认启用吗？<br/>";
		msgContent += "详细信息："+errorMsg;
	}
	//解决 this 作用域问题
	var that = this;
	//确认删除 提示框
	this.showConfirm("确认启用" , msgContent , function(v){
		//console.log("v="+v);
		if(v){
			
			var idValArray = easyuiCommon.getFieldValueArray(deleteArray , "id"); 
			//获取指定 删除请求url 地址
			var url = easyuiCommon.getURLRegExpMatchParam(requestURL , {ids:idValArray});
			if(!url || url == null || url == ""){
				return;
			}
			console.log("url="+url);
			//项目根目录 +  请求前缀URI + 具体请求地址
			url = easyuiCommon.getWebRootPath()+ that.getRequestURI() + url;
			easyuiCommon.ajaxGetSubmitOperate(url , {} , function(url , param , result){
				that.showSuccess("成功" , result.resultMsg , function(){
    				  that.refreshDataGrid();
    			  });
    		} , function(url , param , result){
    			that.showError("失败" , result.resultMsg);
    		});
		}
	});
};


/***
 * 列表【禁用按钮】处理方法
 */
DataBaseListUI.prototype.unEnableRecord = function(){
	var requestURL = this.getUnEnableRequestURL();
	if(requestURL == null || requestURL == ""){
		alert("function[getUnEnableRequestURL]指定禁用url为空!!");
		return;
	}
	//判断是否选中行记录
	if(!this.checkRowSelected(true)){
		return;
	}
	//获取选中行数据
	var selectedRowRecord = this.getSelectedRowData();
	if(!this.validateUnEnable(selectedRowRecord)){
		return;
	}
	//获取指定 删除请求url 地址
	var url = easyuiCommon.getURLRegExpMatchParam(requestURL , selectedRowRecord);
	if(!url || url == null || url == ""){
		return;
	}
	//项目根目录 +  请求前缀URI + 具体请求地址
	url = easyuiCommon.getWebRootPath()+ this.getRequestURI() + url;
	//解决 this 作用域问题
	var that = this;
	easyuiCommon.ajaxGetSubmitOperate(url , {} , function(url , param , result){
		that.showSuccess("成功" , result.resultMsg , function(){
			 that.refreshDataGrid();
		  });
	} , function(url , param , result){
		that.showError("失败" , result.resultMsg);
	});
};




/***
 * 列表【批量禁用按钮】处理方法
 */
DataBaseListUI.prototype.unEnablesRecord = function(){
	var requestURL = this.getUnEnablesRequestURL();
	if(requestURL == null || requestURL == ""){
		alert("function[getUnEnablesRequestURL]指定批量禁用url为空!!");
		return;
	}
	//判断是否选中行记录
	if(!this.checkRowSelected(true)){
		return;
	}
	//错误消息
	var errorMsg = "";
	//获取选中批量行数据
	var selectedArray = this.getSelectedRowsArrayData();
	var totalCount = selectedArray.length;
	//需要删除的 数组 array
	var deleteArray = [];
	var index = 0;
	for(var i = 0 ; i < totalCount ; i++){
		var paramObj = {};
		if(!this.validateUnEnable(selectedArray[i] , false , paramObj)){
			errorMsg += (paramObj.msg+"<br/>");
			//selectedArray.splice(i , 1);//  验证失败的元素对象
			//2015-11-24由于使用数组splice删除方法存在问题(导致第二次获取grid选中数据不正确)，直接换另一种方法处理
		}else{
			deleteArray[index++] = selectedArray[i];//设置需要删除的元素
		}
	}
	if(deleteArray.length == 0){
		this.showError("错误", "您勾选的记录都不能禁用,请重新选择!!<br/>详细信息："+errorMsg);
		return;
	}
	var msgContent = "您确认禁用勾选记录吗?";
	var passCount = deleteArray.length;//通过删除验证的数量
	var notPassCount = totalCount - passCount;
	if(notPassCount > 0){
		msgContent = "您总共勾选"+totalCount+"条记录，其中有"+notPassCount+"条不能禁用，将被忽略，实际提交"+passCount+"条，您确认禁用吗？<br/>";
		msgContent += "详细信息："+errorMsg;
	}
	//解决 this 作用域问题
	var that = this;
	//确认删除 提示框
	this.showConfirm("确认禁用" , msgContent , function(v){
		//console.log("v="+v);
		if(v){
			
			var idValArray = easyuiCommon.getFieldValueArray(deleteArray , "id"); 
			//获取指定 删除请求url 地址
			var url = easyuiCommon.getURLRegExpMatchParam(requestURL , {ids:idValArray});
			if(!url || url == null || url == ""){
				return;
			}
			console.log("url="+url);
			//项目根目录 +  请求前缀URI + 具体请求地址
			url = easyuiCommon.getWebRootPath()+ that.getRequestURI() + url;
			easyuiCommon.ajaxGetSubmitOperate(url , {} , function(url , param , result){
				that.showSuccess("成功" , result.resultMsg , function(){
    				  that.refreshDataGrid();
    			  });
    		} , function(url , param , result){
    			that.showError("失败" , result.resultMsg);
    		});
		}
	});
};



/****
 * datagrid 行选中事件【btnAdd、btnEdit、btnDelete、btnEnable、btnUnEnable】
* @param rowIndex - 行号（从0开始）
* @param rowData - 选择的行所代表的JSON对象
 */
DataBaseListUI.prototype.listGrid_onRowSelect = function(rowIndex , rowData){
	if(!rowData){
		return;
	}
	var isSystem = rowData["isSystem"];//系统初始化用户
	var isEnable = rowData["isEnable"];//启用
	if(isSystem){//不能删除、启用、禁用
		$('#btnEdit').linkbutton('disable');
		$('#btnDelete').linkbutton('disable');
		$('#btnEnable').linkbutton('disable');
		$('#btnUnEnable').linkbutton('disable');
	}else{
		if(isEnable){//启用：不能编辑、不能删除、不能启用、可禁用
			$('#btnEdit').linkbutton('disable');
			$('#btnDelete').linkbutton('disable');
    		$('#btnEnable').linkbutton('disable');
    		$('#btnUnEnable').linkbutton('enable');
    	}else{
    		$('#btnEdit').linkbutton('enable');
    		$('#btnDelete').linkbutton('enable');
    		$('#btnEnable').linkbutton('enable');
    		$('#btnUnEnable').linkbutton('disable');
    	}
	}
};

