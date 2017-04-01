



/****
 * 定义编辑带分录 UI 基类 构造方法
 * 方法：保存、关闭
 * 并且 继承 BaseEditUI类
 * 
 * @param entryGridId 分录gridId
 */
function BaseEntryEditUI(name , editFormId , entryGridId){
	BaseEditUI.call(this, name); 
	this.editFormId = editFormId;
	this.entryGridId = entryGridId;
	this.editIndex = undefined;//分录当前编辑行索引
}

/****继承父类方法*******/
BaseEntryEditUI.prototype = new BaseEditUI();  //这行的作用是：将Parent中将所有通过prototype追加的属性和方法都追加到Child，从而实现了继承

/***
 * 指定分录列名称(下拉列表 关联字段列)
 * 如：
 * [
 *  {colName: 'action' , disNameColName:'aName' },
 *  {colName: '' , disNameColName:'' }
 * ]
 */
BaseEntryEditUI.prototype.getEntryComboxColumnArray = function(){
	return [];
};

/***
 * 分录编辑
 */
BaseEntryEditUI.prototype.endEditing = function(){
   /******/
	//console.log("endEditing..="+editUI.editIndex+" \t this.entryGridId="+this.entryGridId);
	 if (editUI.editIndex == undefined){
		 return true;
	 }
     if ($('#'+this.entryGridId).datagrid('validateRow', editUI.editIndex)){
    	 //指定分录列名称(下拉列表 关联字段列)
    	 var entryComboxColumnArray = this.getEntryComboxColumnArray();
    	 for(var i = 0 ; i < entryComboxColumnArray.length ; i++){
    		 var ed = $('#'+this.entryGridId).datagrid('getEditor', {index:editUI.editIndex , field:entryComboxColumnArray[i].colName});
    		 var value = $(ed.target).combobox('getValue');
    	     var alias = $(ed.target).combobox('getText');
    	     console.log("index="+editUI.editIndex+" \t value="+value+" \t alias="+alias);
    	     $('#'+this.entryGridId).datagrid('getRows')[editUI.editIndex][entryComboxColumnArray[i].disNameColName] = alias;
    	 }
        $('#'+this.entryGridId).datagrid('endEdit', editUI.editIndex);
        editUI.editIndex = undefined;
        return true;
     } else {
        return false;
     }
     
};

/***
 * 分录单元格点击事件
 * @param index 行索引
 * @param field 字段名称
 */
BaseEntryEditUI.prototype.entryGrid_onClickCell = function(index, field){
	//console.log("index="+index+" \t field="+field+" \t this.editIndex="+editUI.editIndex+" \t this.entryGridId="+this.entryGridId);
    if (editUI.editIndex != index){
    	var flag = editUI.endEditing();
    	//console.log("flag="+flag);
        if (flag){
            $('#'+editUI.entryGridId).datagrid('selectRow', index).datagrid('beginEdit', index);
            var ed = $('#'+editUI.entryGridId).datagrid('getEditor', {index:index , field:field});
           // console.log("ed="+ed);
            if (ed){
                ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
            }
            editUI.editIndex = index;
        } else {
            $('#'+editUI.entryGridId).datagrid('selectRow', editUI.editIndex);
        }
    }
};


/***
 * 分录初始化行 默认数据行
 */
BaseEntryEditUI.prototype.getDefaultNewRowData = function(){
	//return {id:null , action:{id: null , name: null} , buttonIdName: null , actionName: null};
	return {};
};

/**
 * 新增分录行
 */
BaseEntryEditUI.prototype.appendEntryRow = function(){
	console.log("this.entryGridId="+this.entryGridId);
	$('#'+this.entryGridId).datagrid('appendRow',this.getDefaultNewRowData());
};

/**
 * 删除分录行
 */
BaseEntryEditUI.prototype.removeEntryRow = function(){
	console.log("editIndex..="+this.editIndex+" \t this.entryGridId="+this.entryGridId);
	if(this.editIndex == undefined){
		return;
	}
	$('#'+this.entryGridId).datagrid('cancelEdit', this.editIndex).datagrid('deleteRow',this.editIndex);
	this.editIndex = undefined;
};


/**
 * 获取分录改变的数据记录
 * 从上一次的提交获取改变的所有行。
 * @param type 类型参数指明用哪些类型改变的行，可以使用的值有：inserted,deleted,updated等。当类型参数未配置的时候返回所有改变的行。
 */
BaseEntryEditUI.prototype.getChangeEntryRows = function(type){
	return $('#'+this.entryGridId).datagrid('getChanges',type);
};



/***
 * 获取分录新增数据行对象
 */
BaseEntryEditUI.prototype.getAddedEntryRows = function(){
	return this.getChangeEntryRows("inserted");
};

/***
 * 获取分录删除数据行对象
 */
BaseEntryEditUI.prototype.getDeletedEntryRows = function(){
	return this.getChangeEntryRows("deleted");
};


/***
 * 获取分录修改数据行对象
 */
BaseEntryEditUI.prototype.getUpdatedEntryRows = function(){
	return this.getChangeEntryRows("updated");
};

/***
 * 提交所有从加载或者上一次调用acceptChanges函数后更改的数据。
 */
BaseEntryEditUI.prototype.entryAccept = function(){
	 if (this.endEditing()){
	      $('#'+this.entryGridId).datagrid('acceptChanges');
	 }
};

/***
 * 回滚所有从创建或者上一次调用acceptChanges函数后更改的数据。
 */
BaseEntryEditUI.prototype.entryReject = function(){
	 $('#'+this.entryGridId).datagrid('rejectChanges');
	 this.editIndex = undefined;
};

/***
 * 从上一次的提交获取改变的所有行。类型参数指明用哪些类型改变的行，可以使用的值有：inserted,deleted,updated等。当类型参数未配置的时候返回所有改变的行。
 */
BaseEntryEditUI.prototype.getEntryChange = function(type){
	//结束编辑
	this.endEditing();
	var rows =  $('#'+this.entryGridId).datagrid('getChanges');
	 var addRows = this.getAddedEntryRows();
 	 var delRows = this.getDeletedEntryRows();
 	 var updateRows = this.getUpdatedEntryRows();
	 console.log("JSON.stringify(addRows)="+JSON.stringify(addRows));
	 console.log("JSON.stringify(delRows)="+JSON.stringify(delRows));
	 console.log("JSON.stringify(updateRows)="+JSON.stringify(updateRows));
	 alert(rows.length+' rows are changed!');
};




/**
 * 保存之前处理方法，处理分录数据
 */
BaseEntryEditUI.prototype.procesEntryData = function(){
	//首先分录 结束编辑
	this.endEditing();
	//将分录 新增、编辑、删除数据 设置隐藏文本框 
	var addRows = this.getAddedEntryRows();
	var delRows = this.getDeletedEntryRows();
	var updateRows = this.getUpdatedEntryRows();
	
	console.log("JSON.stringify(addRows)="+JSON.stringify(addRows));
	if(addRows && addRows.length > 0){
		$("#addedEntryRows").val(JSON.stringify(addRows));
	}
	if(delRows && delRows.length > 0){
		$("#deletedEntryRows").val(JSON.stringify(delRows));
	}
	if(updateRows && updateRows.length > 0){
		$("#updatedEntryRows").val(JSON.stringify(updateRows));
	}
};

/***
 * 加载分录数据url地址
 */
BaseEntryEditUI.prototype.getLoadEntryListURL = function(){
	return null;
};


/***
 * 加载分录数据
 */
BaseEntryEditUI.prototype.reloadEntryList = function (){
	var pageStatusVal = $("#pageStatus" , this.getOpenFormWindowObject().document).val();
	if(pageStatusVal == PageStatusObj.EDIT['value'] || pageStatusVal == PageStatusObj.VIEW['value']){
		$('#'+this.entryGridId).datagrid({url : this.getLoadEntryListURL()});
		$('#'+this.entryGridId).datagrid('reload');
	}
};



/**
 * 保存之前处理方法，目前主要用于分录数据提交
 */
BaseEntryEditUI.prototype.beforeSaveForm = function(){
	//调用处理分录数据方法
	this.procesEntryData();
};

/***
 * 保存之后处理方法，目前主要用于分录 
 */
BaseEntryEditUI.prototype.afterSaveForm = function(){
	//提交所有从加载或者上一次调用acceptChanges函数后更改的数据。
	this.entryAccept();
	//刷新分录
	//this.reloadEntryList();
};

