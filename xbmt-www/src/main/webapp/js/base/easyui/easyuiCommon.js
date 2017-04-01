

/**
 * 单据界面状态
 */
var PageStatusObj = {
	ADDNEW:{value:'ADDNEW' , alias: '新增'},
	EDIT: {value:'EDIT' , alias: '编辑'},
	VIEW: {value:'VIEW' , alias: '查看'}
};

/***
 * 定义 公共方法
 */
var easyuiCommon = {
		
		/***
		 * 检查数据列表 grid是否选中行
		 * @param gridId 数据列表id
		 * @param isPrompt 是否弹出提示消息
		 * @return 返回 true 选中记录，false 未选中
		 */
		checkGridRowSelected: function(gridId , isPrompt){
			var selectArray = this.getSelectedRowData(gridId);
	    	if(selectArray == null || selectArray.length == 0){
	    		if(isPrompt && isPrompt == true){
	    			this.showError("错误","请选中行记录");
	    		}
	    		return false;
	    	}
	    	return true;
		},
		/***
		 * 检查数据列表 grid是否选中多行
		 * @param gridId 数据列表id
		 * @param isPrompt 是否弹出提示消息
		 * @return 返回 false未选中 或 选中多条记录， true 选中单条记录
		 */
		checkGridMultipleRowsSelected: function(gridId , isPrompt){
			if(!this.checkGridRowSelected(gridId, isPrompt)){
				return false;
			}
			var selectArray = this.getSelectedRowsArrayData(gridId);
	    	if(selectArray.length > 1){
	    		if(isPrompt && isPrompt == true){
	    			this.showError("错误","请选中单行记录");
	    		}
	    		return false;
	    	}
	    	return true;
		},
		/****
		 * 获取grid选中记录record对象
		 * @param gridId
		 */
		getSelectedRowData: function(gridId){
			return this.getSelectedRowsArrayData(gridId)[0];
		},
		
		/***
		 * 获取dataGrid 选中多条记录record 
		 * @returns 返回数组
		 */
		getSelectedRowsArrayData: function(gridId){
			return $('#'+gridId).datagrid('getSelections');
		},
		/******
		 * 确认取消 询问提示框
		 * title 提示标题 
		 * content 提示内容
		 * onConfirm 确认 取消 函数引用
		 */
		showConfirm: function(title , content , onConfirm){
			if(window.parent){
				window.parent.$.messager.confirm(title , content , onConfirm);
			}else{
				$.messager.confirm(title , content , onConfirm);
			}
		 },

		/******
		 * 警告 询问提示框
		 * title 提示标题 
		 * content 提示内容
		 * onClose 关闭函数引用
		 */
		showWarning: function(title , content , onClose){
			this.showMsgBox("warning" , title , content , onClose);
		 },

		/******
		 * 询问提示框
		 * title 提示标题 
		 * content 提示内容
		 * onClose 关闭函数引用
		 */
		showQuestion: function(title , content , onClose){
			this.showMsgBox("question" , title , content , onClose);
		},
		/******
		 * 询问提示框
		 * title 提示标题 
		 * content 提示内容
		 * onClose 关闭函数引用
		 */
		showQuestion: function(title , content , onClose){
			this.showMsgBox("question" , title , content , onClose);
		},
		
		/******
		 * 错误提示框
		 * title 提示标题 
		 * content 提示内容
		 * onClose 关闭函数引用
		 */
		showError: function(title , content , onClose){
			this.showMsgBox("error" , title , content , onClose);
		},
		/******
		 * 成功提示框
		 * title 提示标题 
		 * content 提示内容
		 * onClose 关闭函数引用
		 */
		showSuccess: function(title , content , onClose){
			this.showMsgBox("info" , title , content , onClose);
		},

		/******
		 * 消息提示框
		 * title 提示标题 
		 * content 提示内容
		 * onClose 关闭函数引用
		 */
		showAlert: function(title , content , onClose){
			this.showMsgBox(null , title , content , onClose);
		},

		/******
		 * 消息提示框
		 * type 消息框类型 可选的值有'alert'、'success'、'error'、'question'、'warning'。默认值为'alert'。
		 * title 提示标题 
		 * content 提示内容
		 * onClose 关闭函数引用
		 */
		showMsgBox: function(type , title , content , onClose){
			//console.log("showMsgBox.type="+type+" \t title="+title+" \t content="+content+" \t onClose="+onClose);
			if(window.parent){
				window.parent.$.messager.alert(title , content , type , onClose);
			}else{
				$.messager.alert(title , content , type , onClose);
			}
		},
		
		/*****
		 * 列表 日期 格式化函数，如：yyyy-MM-dd
		 * @param colValue 当前字段值
		 * @param rowData 当前数据化记录对象
		 */
		dateFormatter: function (colValue, rowData) {
			//console.log("colValue="+colValue+" \t id="+rowData["id"]);
			if(colValue == null || colValue == ""){
				return "";
			}
			return new Date(parseInt(colValue)).pattern("yyyy-MM-dd");
		},
		
		/*****
		 * 列表 日期+时间 格式化函数，如：yyyy-MM-dd HH:mm:ss
		 * @param colValue 当前字段值
		 * @param rowData 当前数据化记录对象
		 */
		dateTimeFormatter: function (colValue, rowData) {
			//console.log("colValue="+colValue+" \t id="+rowData["id"]);
			if(colValue == null || colValue == ""){
				return "";
			}
			return new Date(parseInt(colValue)).pattern("yyyy-MM-dd HH:mm:ss");
		},
		
		/***
		 * 启用、禁用 checkbox 复选框 格式化函数
		 * @param colValue 当前字段值
		 * @param rowData 当前数据化记录对象
		 */
		enableFormatter: function(colValue, rowData){
			//console.log("colValue="+colValue+" \t id="+rowData["id"]);
			if(colValue == true || colValue == 1 || colValue == "1"){
				return "<input type='checkbox' disabled checked />";
			}
			return "<input type='checkbox' disabled />";
		},
		
		/*****
		 * 列表grid 性别格式化函数 
		 * @param colValue 当前字段值
		 * @param rowData 当前数据化记录对象
		 */
		sexFormatter: function(colValue, rowData){
			//console.log("colValue="+colValue+" \t id="+rowData["id"]);
			if(colValue == 1 || colValue == "1"){
				return "男";
			}
			if(colValue == 0 || colValue == "0"){
				return "女";
			}
			return colValue;
		},
		
		/***
		 * 列个 grid icon图标格式化 函数
		 * @param colValue 当前字段值
		 * @param rowData 当前数据化记录对象
		 */
		iconFormatter: function(colValue, rowData){
			if(colValue != null || colValue != ""){
				return "<img src='"+getWebRootPath()+"/images/icon/"+colValue+"' />";
			}
			return colValue;
		},
		
		/****
		 * 禁用form表单中所有的input[文本框、复选框、单选框],
		 * select[下拉选],多行文本框[textarea]
		 * @param windowObj 当前window对象
		 * @param formId 表单id
		 * @param isDisabled  是否禁用 ，true禁用  false 启用
		 */
		disableForm: function(windowObj , formId , isDisabled){
			console.log("windowObj="+windowObj+" \t formId="+formId+" \t isDisabled="+isDisabled);
			if(!windowObj){
				windowObj = window;
			}
			 var attr = "disable";  
			    if(!isDisabled){  
			       attr = "enable";  
			    }  
			    /**
			     * 使用 jquery 选择器进行设置
			     */
			    $("form[id='"+formId+"'] :text" , windowObj.document).attr("disabled",isDisabled); //查找所有文本框  <input type="text" />
			    $("form[id='"+formId+"'] textarea" , windowObj.document).attr("disabled",isDisabled); //查找 所有多行文本框 <textarea>
			    $("form[id='"+formId+"'] select" , windowObj.document).attr("disabled",isDisabled);//查询所有 下拉列表 <select>
			    $("form[id='"+formId+"'] :radio" , windowObj.document).attr("disabled",isDisabled);  //查找所有单选按钮   <input type="radio" />
			    $("form[id='"+formId+"'] :checkbox" , windowObj.document).attr("disabled",isDisabled); //查找所有复选按钮   <input type="checkbox" />
			    $("form[id='"+formId+"'] :button" , windowObj.document).attr("disabled",isDisabled); //查找所有button按钮 [ <input type="button" />,<button></button> ]
			    //console.log("$(form input).length="+$("form input").length+"  \t isDisabled11="+isDisabled);
			    $("form[id='"+formId+"'] input" , windowObj.document).attr("disabled",isDisabled); //查找所有  <input/>
			    $("form[id='"+formId+"'] :submit" , windowObj.document).attr("disabled",isDisabled); //查找所有  <input type="submit" />
			    $("form[id='"+formId+"'] :reset" , windowObj.document).attr("disabled",isDisabled); //查找所有  <input type="reset" />
			    $("form[id='"+formId+"'] button[type='submit']" , windowObj.document).attr("disabled",isDisabled); //查找所有  <button type='submit'>
			    $("form[id='"+formId+"'] button[type='reset']" , windowObj.document).attr("disabled",isDisabled); //查找所有  <button type='reset'>
			    
			    /*****easyui 日期控件*******/
			    //<input disabled="disabled" comboname="birthday" textboxname="birthday" id="birthday" class="easyui-datebox datebox-f combo-f textbox-f" style="width: 50%; display: none;" value="1978-01-01" data-options="required:true,formatter:myformatter,parser:myparser,validType:'date'" type="text">
			    //easyui-datebox datebox-f combo-f textbox-f
			    $("#"+formId+" input[class^='easyui-datebox datebox-f']" , windowObj.document).each(function() {
			    	//console.log("this.id22="+this.id);
			    	windowObj.$('#'+this.id).datebox(attr);
			    });
			    
			    /*****easyui 下拉列表控件[input]*******/
			    //<select disabled="disabled" comboname="sex" textboxname="sex" class="easyui-combobox combobox-f combo-f textbox-f" style="width: 50%; display: none;" data-options="required:true">
			    //easyui-combobox combobox-f combo-f textbox-f
			    $("#"+formId+" select[class^='easyui-combobox combobox-f']" , windowObj.document).each(function() {
			    	//console.log("this.id33="+this.id);
			    	windowObj.$('#'+this.id).combobox(attr);
			    });
			    /*****easyui 下拉列表控件[select]*******/
			    //<input disabled="disabled" comboname="parent.id" textboxname="parent.id" style="display: none;" class="easyui-combobox combobox-f combo-f textbox-f" id="parent"
			    $("#"+formId+" input[class^='easyui-combobox combobox-f']" , windowObj.document).each(function() {
			    	//console.log("this.id33="+this.id);
			    	windowObj.$('#'+this.id).combobox(attr);
			    });
			    
			   
			    /***easyui 链接button按钮  包括分录 button 按钮  btnAddLine\btnRemoveLine\btnAccept\btnReject\btnGetChanges****/
			    //<a id="" group="" href="javascript:void(0)" class="easyui-linkbutton c6 l-btn l-btn-small" iconcls="icon-ok" onclick="saveForm()" style="width: 88px;"><span style="margin-top: 0px;" class="l-btn-left l-btn-icon-left"><span class="l-btn-text">保存</span><span class="l-btn-icon icon-ok">&nbsp;</span></span></a>
			    $("#"+formId+" a[class^='easyui-linkbutton'][id!='btnCancel']" , windowObj.document).each(function() {
			    	//console.log("this.id44="+this.id);
			    	windowObj.$('#'+this.id).linkbutton(attr);
			    });
			    //分录 所有行
			    
			    
		},
		
		/****
		 * 表单日期 转换(将字符串 转换为 Date)
		 * @param dateStr 
		 * @returns {Date}
		 */
		myparser: function(dateStr){
		    if (!dateStr) return new Date();
		    var ss = (dateStr.split('-'));
		    var y = parseInt(ss[0],10);
		    var m = parseInt(ss[1],10);
		    var d = parseInt(ss[2],10);
		    if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
		        return new Date(y,m-1,d);
		    } else {
		        return new Date();
		    }
		},
		/****
		 * 表单日期格式化(将日期对象 指定格式化 )
		 * @param date 
		 * @returns {Date}
		 */
		myformatter: function(date){
		    var y = date.getFullYear();
		    var m = date.getMonth()+1;
		    var d = date.getDate();
		    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
		},
		/****
		 * 将url 中的变量参数替换为具体值，如：/admin/sys/user/edit/{id}/form/{number}/{id}
		 * @param url url地址
		 * @param paramObject 参数对象
		 */
		getURLRegExpMatchParam: function(url , paramObject){
			return window.getURLRegExpMatchParam(url , paramObject);
		},
		
		/**
		 * 获取工程的相对路径<p>
		 */
		getWebRootPath: function(){
			return window.getWebRootPath();
		},
		/***
		 * ajax get 提交相关业务处理方法
		 * @param url 请求地址
		 * @param jsonParam 提交json参数
		 * @param callSuccessFun 请求成功后函数引用
		 * @param callFailureFun 请求失败后函数引用
		 */
		ajaxGetSubmitOperate: function(url , jsonParam ,  callSuccessFun , callFailureFun){
			window.ajaxGetSubmitOperate(url , jsonParam ,  callSuccessFun , callFailureFun);
		},
		/***
		 * ajax post 提交相关业务处理方法
		 * @param url 请求地址
		 * @param jsonParam 提交json参数
		 * @param callSuccessFun 请求成功后函数引用
		 * @param callFailureFun 请求失败后函数引用
		 */
		ajaxPostSubmitOperate: function(url , jsonParam ,  callSuccessFun , callFailureFun){
			window.ajaxGetSubmitOperate(url , jsonParam ,  callSuccessFun , callFailureFun);
		} ,
		
		/***
		 * 将json对象数组中的某个属性名称 转换为 值数组
		 * 如：[{id:'11',name:'11'},{id:'22',name:'22'}]
		 * 返回 id属性值数组： ['11' , '22']
		 * @param array
		 * @param fileName
		 */
		getFieldValueArray: function(array , fileName){
			if(array == null || array.length == 0){
				return [];
			}
			var arrayValue = [];
			for(var i = 0 ; i < array.length ; i++){
				arrayValue[i] = array[i][fileName];
			}
			return arrayValue;
		},
		
		/***
		 * 转换JSON属性,嵌套对象与点号
		 * { "id":1, "name":"Jack", "parent.id":2 } 转换为 { "id":1, "name":"Jack", "parent": { "id":2 } }
		 */
		serializeObject: function(arrayData){
			return null;
		}
};


/***
 * 基于jQuery的一个扩展form序列化到json对象
 
$.fn.serializeObject = function() {
	var arrayData, objectData;
	arrayData = this.serializeArray();
	objectData = {};
	$.each(arrayData, function() {
		var value;
		if (this.value != null) {
			value = this.value;
		} else {
			value = '';
		}
		// search for "parent.id" like attribute
		if (this.name.indexOf('.') != -1) {
			var attrs = this.name.split('.');
			var tx = objectData;
			for ( var i = 0; i < attrs.length - 1; i++) {
				if (objectData[attrs[i]] == undefined)
					objectData[attrs[i]] = {};
				tx = objectData[attrs[i]];
			}
			tx[attrs[attrs.length - 1]] = value;
		} else {
			if (objectData[this.name] != null) {
				if (!objectData[this.name].push) {
					objectData[this.name] = [ objectData[this.name] ];
				}
				objectData[this.name].push(value);
			} else {
				objectData[this.name] = value;
			}
		}
	});
	return objectData;
};*/