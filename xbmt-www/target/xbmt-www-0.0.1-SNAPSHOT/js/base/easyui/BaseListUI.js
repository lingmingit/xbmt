


/****
 * 定义列表带操作按钮(新增、编辑、查看、删除) UI 基类 构造方法
 * 并且 继承 CoreListUI类
 * 方法：新增、编辑、查看、删除、初始化form表单控件状态、弹出form表单窗口、表单保存、关闭弹出窗口
 */
function BaseListUI(name , listGridId , editFormId){
	CoreListUI.call(this, name); 
	this.listGridId = listGridId;
	this.editFormId = editFormId;
}

/****继承父类方法*******/
BaseListUI.prototype = new CoreListUI();  //这行的作用是：将Parent中将所有通过prototype追加的属性和方法都追加到Child，从而实现了继承 




/***
 * 指定【新增界面】url地址
 */
BaseListUI.prototype.getAddFormURL = function(){
	return null;
};


/***
 * 指定【编辑界面】url地址
 */
BaseListUI.prototype.getEditFormURL = function(){
	return null;
};

/***
 * 指定【查看界面】url地址
 */
BaseListUI.prototype.getViewFormURL = function(){
	return null;
};


/***
 * 指定【删除请求】url地址
 */
BaseListUI.prototype.getDeleteRequestURL = function(){
	return null;
};
/***
 * 指定 批量 删除 请求url地址
 * @returns {String}
 */
BaseListUI.prototype.getDeletesRequestURL = function(){
	return null;
};

/***
 * 验证是否允许新增
 * @param selectRowRecord 选中行记录
 * @return true 允许，false 不允许
 */
BaseListUI.prototype.validateAdd = function(selectRowRecord){
	return true;
};

/***
 * 验证是否允许编辑
 * @param selectRowRecord 选中行记录
 * @return true 允许，false 不允许
 */
BaseListUI.prototype.validateEdit = function(selectRowRecord){
	return true;
};

/***
 * 验证是否允许删除
 * @param selectRowRecord 选中行记录
 * @param isPrompt 是否提示错误消息
 * @param paramObj 验证消息对象，如：{msg:''}
 * @return true 允许，false 不允许
 */
BaseListUI.prototype.validateDelete = function(selectRowRecord , isPrompt , paramObj){
	return true;
};

/**
 * 新增、编辑、查看弹出form表单 url 追加自定义参数 (url传参数)
 * @param selectRowData 选中datagrid 数据行记录json对象
 * @param pageStatusVal 界面状态： 新增、编辑、查看
 * @param isAddChildren 是否新增下级
 * @returns 返回追加url传参数  如：number=xx&name=xx
 */
BaseListUI.prototype.getFormUrlAddParam = function(selectRowData , pageStatusVal , isAddChildren){
	return "";
};



/**
 * 获取当前作用域对象
 */
BaseListUI.prototype.getOpenFormWindowObject = function(){
	if(window.parent){
		return window.parent;
	}
	return window;
};

/**
 * 获取弹出 form window 窗口相关样式设置
 */
BaseListUI.prototype.getOpenFormWindowStyle = function(){
	return "width:70%;height:500px;padding:6px;";
};

/**
 * 获取弹出 form 编辑界面 editUI对象
 */
BaseListUI.prototype.getOpenFormEditUIObject = function(){
	if(window.parent){
		if(window.parent.editUI){
			return window.parent.editUI;
		}
	}
	return null;
};


/**
 * 新增、编辑、查看弹出form表单窗口
 * @param formUrl url地址
 * @param pageStatusVal 界面状态： 新增、编辑、查看
 * @param isAddChildren 是否新增下级
 */
BaseListUI.prototype.openOmDialogForm = function(formUrl , pageStatusVal , isAddChildren){
	if(!this.editFormId){
		alert("请通过构造方法初始化editFormId 参数");
		return;
	}
	
	if(!formUrl || formUrl == null || formUrl == ""){
		return;
	}
	//获取选中数据行记录对象
	var selectRowData = this.getSelectedRowData();
	
	if(formUrl.indexOf("?") != -1){
		formUrl += "&sysRandom="+Math.random();
	}else{
		formUrl += "?sysRandom="+Math.random();
	}
	//获取 form 表单 url 追加参数
	var addParamUrl = this.getFormUrlAddParam(selectRowData, pageStatusVal  , isAddChildren);
	//console.log("addParamUrl="+addParamUrl);
	if(addParamUrl != "" && addParamUrl.length > 0){
		formUrl += ("&"+addParamUrl);
	}
	//项目根目录 +  请求前缀URI + 具体请求地址
	formUrl = easyuiCommon.getWebRootPath()+ this.getRequestURI() + formUrl;
	console.log("formUrl="+formUrl);
	var that = this;//重写定义 this对象引用问题，解决嵌套调用 this作用域问题
	
	//加载 url 地址
	this.getOpenFormWindowObject().$('#form-panel').panel({    
	    href:formUrl,    
	    onLoad:function(){    
	        //页面加载完成后 初始化 form表单 相关控件 或参数
	    	//that.initFormCompoent(pageStatusVal , selectRowData);
	    	that.getOpenFormEditUIObject().initFormCompoent(pageStatusVal , selectRowData);
	    }
	});
	var formWindowStyle = that.getOpenFormWindowStyle();
	console.log("formWindowStyle="+formWindowStyle);
	//指定弹出form window相关参数
	this.getOpenFormWindowObject().$('#form-dialog-modal').window({    
		style: formWindowStyle,
	    title: that.name 
	 });  
	this.getOpenFormWindowObject().$("#form-dialog-modal").window('open');
};




/***
 * 列表【新增按钮】处理方法
 */
BaseListUI.prototype.addRecord = function(){
	if(typeof(this.getAddFormURL) != 'function'){
		alert("请通过function[getAddFormURL]指定新增url");
		return;
	}
	var formUrl = this.getAddFormURL();
	if(formUrl == null || formUrl == ""){
		alert("function[getAddFormURL]指定新增url为空!!");
		return;
	}
	//获取选中行数据
	var selectedRowRecord = this.getSelectedRowData();
	if(!this.validateAdd(selectedRowRecord)){
		return;
	}
	//弹出窗口
	this.openOmDialogForm(formUrl , PageStatusObj.ADDNEW['value']);
};



/***
 * 列表【编辑按钮】处理方法
 */
BaseListUI.prototype.editRecord = function(){
	var formUrl = this.getEditFormURL();
	if(formUrl == null || formUrl == ""){
		alert("function[getEditFormURL]指定编辑url为空!!");
		return;
	}
	//判断是否选中行记录
	if(!this.checkRowSelected(true)){
		return;
	}
	//获取选中行数据
	var selectedRowRecord = this.getSelectedRowData();
	if(!this.validateEdit(selectedRowRecord)){
		return;
	}
	
	//将url 中的变量参数替换为具体值
	formUrl = easyuiCommon.getURLRegExpMatchParam(formUrl , selectedRowRecord);
	//弹出窗口
	this.openOmDialogForm(formUrl , PageStatusObj.EDIT['value']);
};




/***
 * 列表【查看按钮】处理方法
 */
BaseListUI.prototype.viewRecord = function(){
	
	var formUrl = this.getViewFormURL();
	if(formUrl == null || formUrl == ""){
		alert("function[getViewFormURL]指定查看url为空!!");
		return;
	}
	//判断是否选中行记录
	if(!this.checkRowSelected(true)){
		return;
	}
	//将url 中的变量参数替换为具体值
	formUrl = easyuiCommon.getURLRegExpMatchParam(formUrl , this.getSelectedRowData());
	//弹出窗口
	this.openOmDialogForm(formUrl , PageStatusObj.VIEW['value']);
};


/***
 * 列表【删除按钮】处理方法
 */
BaseListUI.prototype.deleteRecord = function(){
	var requestURL = this.getDeleteRequestURL();
	if(requestURL == null || requestURL == ""){
		alert("function[getDeleteRequestURL]指定删除url为空!!");
		return;
	}
	//判断是否选中行记录
	if(!this.checkRowSelected(true)){
		return;
	}
	//获取选中行数据
	var selectedRowRecord = this.getSelectedRowData();
	if(!this.validateDelete(selectedRowRecord , true , {})){
		return;
	}
	//解决 this 作用域问题
	var that = this;
	//确认删除 提示框
	this.showConfirm("确认删除" , "您确认删除该记录吗?" , function(v){
		console.log("v="+v);
		if(v){
			//获取指定 删除请求url 地址
			var url = easyuiCommon.getURLRegExpMatchParam(requestURL , selectedRowRecord);
			if(!url || url == null || url == ""){
				return;
			}
			//项目根目录 +  请求前缀URI + 具体请求地址
			url = easyuiCommon.getWebRootPath()+ that.getRequestURI() + url;
			easyuiCommon.ajaxGetSubmitOperate(url , {} , function(url , param , result){
				that.showSuccess("成功" , result.resultMsg , function(){
    				  //console.log("listGridId="+listGridId);
					 that.refreshDataGrid();
    			  });
    		} , function(url , param , result){
    			that.showError("失败" , result.resultMsg);
    		});
		}
	});
};




/***
 * 列表【批量删除按钮】处理方法
 */
BaseListUI.prototype.deletesRecord = function(){
	var requestURL = this.getDeletesRequestURL();
	if(requestURL == null || requestURL == ""){
		alert("function[getDeletesRequestURL]指定批量删除url为空!!");
		return;
	}
	//判断是否选中行记录
	if(!this.checkRowSelected(true)){
		return;
	}
	//错误消息
	var errorMsg = "";
	//获取选中批量行数据
	var selectedArray =  this.getSelectedRowsArrayData();
	var totalCount = selectedArray.length;
	//console.log("selectedArray="+selectedArray);
	//需要删除的 数组 array
	var deleteArray = [];
	var index = 0;
	for(var i = 0 ; i < totalCount ; i++){
		var paramObj = {};
		if(!this.validateDelete(selectedArray[i] , false , paramObj)){
			errorMsg += (paramObj.msg+"<br/>");
			//console.log("i="+i+" \t msg="+paramObj.msg);
			//selectedArray.splice(i , 1);//删除  验证失败的元素对象
			//2015-11-24由于使用数组splice删除方法存在问题(导致第二次获取grid选中数据不正确)，直接换另一种方法处理
		}else{
			deleteArray[index++] = selectedArray[i];//设置需要删除的元素
		}
	}
	//console.log("deleteArray="+deleteArray);
	//console.log("errorMsg="+errorMsg);
	
	if(deleteArray.length == 0){
		this.showError("错误", "您勾选的记录都不能删除,请重新选择!!<br/>详细信息："+errorMsg);
		return;
	}
	var msgContent = "您确认删除勾选记录吗?";
	var passCount = deleteArray.length;//通过删除验证的数量
	var notPassCount = totalCount - passCount;
	if(notPassCount > 0){
		msgContent = "您总共勾选"+totalCount+"条记录，其中有"+notPassCount+"条不能删除，将被忽略，实际提交"+passCount+"条，您确认删除吗？<br/>";
		msgContent += "详细信息："+errorMsg;
	}
	//解决 this 作用域问题
	var that = this;
	//console.log("msgContent="+msgContent);
	//确认删除 提示框
	this.showConfirm("确认删除" , msgContent , function(v){
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
 * onDblClickRow rowIndex, rowData 在用户双击一行的时候触发，参数包括：
 * @param rowIndex 点击的行的索引值，该索引值从0开始。
 * @param rowData 对应于点击行的记录。 
 */
BaseListUI.prototype.listGrid_onRowDblClick = function(rowIndex , rowData){
	//this.viewRecord();
	var formUrl = this.getViewFormURL();
	if(formUrl == null || formUrl == ""){
		alert("function[getViewFormURL]指定查看url为空!!");
		return;
	}
	//判断是否选中行记录
	if(!this.checkRowSelected(true)){
		return;
	}
	//将url 中的变量参数替换为具体值
	formUrl = easyuiCommon.getURLRegExpMatchParam(formUrl , this.getSelectedRowData());
	//弹出窗口
	this.openOmDialogForm(formUrl , PageStatusObj.VIEW['value']);
};





/***
 * 关闭弹出form表单窗口
 * @param isRefresh 是否刷新列表grid
 */
BaseListUI.prototype.closeFormWindow = function(isRefresh){
	//关闭窗口之前
	if(typeof(this.beforeCloseFormWindow) == 'function'){
		return this.beforeCloseFormWindow();
	}
	//关闭窗口
	$("#form-dialog-modal").window('close');
	if(isRefresh){
		this.refreshDataGrid();
	}
	//关闭窗口之后
	if(typeof(this.afterCloseFormWindow) == 'function'){
		return this.afterCloseFormWindow();
	}
};




/***
 * 根据界面状态 初始化 form表单中的控件状态
 * ADDNEW 新增、EDIT 编辑、 VIEW 查看
  2015-11-20 由于 listUI和 editUI的分开，所以注释
BaseListUI.prototype.initFormCompoent = function(pageStatusVal , entityObj){
	console.log("pageStatus="+pageStatusVal+" \t entityObj="+entityObj);
	
	if(!pageStatusVal){
		pageStatusVal = $("#pageStatus" , this.getOpenFormWindowObject()).val();
	}
	
	if(pageStatusVal == PageStatusObj.ADDNEW['value']){
		if(typeof(this.formAddInitialize) == 'function'){
			this.formAddInitialize();//新增界面初始化 方法
		}
	}else if(pageStatusVal == PageStatusObj.EDIT['value']){
		if(typeof(this.formEditInitialize) == 'function'){
			this.formEditInitialize(entityObj);//编辑界面初始化 方法
		}
		
	}else if(pageStatusVal == PageStatusObj.VIEW['value']){
		if(typeof(this.beforeFormViewInitialize) == 'function'){
			this.beforeFormViewInitialize(entityObj);//查看界面之前初始化 方法
		}
		console.log("view====");
		//禁用表单所有字段控件
		easyuiCommon.disableForm(this.getOpenFormWindowObject() , this.editFormId , true);
		if(typeof(this.afterFormViewInitialize) == 'function'){
			this.afterFormViewInitialize(entityObj);//查看界面之后 初始化方法
		}
	}
};
*/
/***
 * 列表弹出 表单界面save方法
 * 2015-11-20 由于 listUI和 editUI的分开，所以注释
BaseListUI.prototype.saveForm = function(){
	console.log("this.editFormId="+this.editFormId);
	if(!this.editFormId){
		alert("请通过构造方法初始化editFormId 参数");
		return;
	}
	//获取function 引用对象
	var that = this;//重写定义 this对象引用问题，解决嵌套调用 this作用域问题
	
	//表单提交
    $('#'+this.editFormId).form('submit',{
        url: $("#"+that.editFormId).attr("action"),
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
        	if(typeof(result) == 'string'){
        		result = eval('('+result+')');
        	}
        	 if(result.resultFlag == true){
        		$("#id").val(result.resultObj.id);
           	   $("#pageStatus").val(PageStatusObj.EDIT['value']);
        		console.log("res.resultObj.id="+result.resultObj.id+" \t pageStatus="+PageStatusObj.EDIT['value']);
        		
        		that.initFormCompoent(PageStatusObj.EDIT['value'] , result.resultObj);//页面加载完成后 初始化 form表单 相关控件 或参数
        		if(typeof(that.afterSaveForm) == 'function'){
        			that.afterSaveForm(result);//保存成功之后方法
        		}
        		that.showSuccess("成功" , result.resultMsg , function(){
          		   that.closeFormWindow(true);//关闭弹窗口
          	   });
             }else{
          	  // alert(res.resultMsg);
            	 that.showError("失败" , result.resultMsg);
             }
        }
    });
};
 */


