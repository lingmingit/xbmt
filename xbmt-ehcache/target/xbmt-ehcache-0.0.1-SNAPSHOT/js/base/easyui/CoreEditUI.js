




/****
 * 定义编辑UI 基类 构造方法
 * 方法：保存、关闭
 * 并且 继承 CoreUI类
 */
function CoreEditUI(name , editFormId){
	CoreUI.call(this, name); 
	this.editFormId = editFormId;
}

/****继承父类方法*******/
CoreEditUI.prototype = new CoreUI();  //这行的作用是：将Parent中将所有通过prototype追加的属性和方法都追加到Child，从而实现了继承



/**
 * 获取当前作用域对象
 */
CoreEditUI.prototype.getOpenFormWindowObject = function(){
	return window;
};


/**
 * 保存之前处理方法，目前主要用于分录数据提交
 */
CoreEditUI.prototype.beforeSaveForm = function(){
};


/***
 * 根据界面状态 初始化 form表单中的控件状态
 * ADDNEW 新增、EDIT 编辑、 VIEW 查看
 */
CoreEditUI.prototype.initFormCompoent = function(pageStatusVal , entityObj){
	console.log("pageStatus="+pageStatusVal+" \t entityObj="+entityObj);
	
	if(!pageStatusVal){
		pageStatusVal = $("#pageStatus" , this.getOpenFormWindowObject().document).val();
	}
	
	if(pageStatusVal == PageStatusObj.ADDNEW['value']){
		if(typeof(this.formAddInitialize) == 'function'){
			this.formAddInitialize();//新增界面初始化 方法
		}
	}else if(pageStatusVal == PageStatusObj.EDIT['value']){
		
		if(typeof(this.formViewOrEditInitialize) == 'function'){
			this.formViewOrEditInitialize(entityObj);//编辑和查看界面 公共初始化 方法
		}
		
		if(typeof(this.formEditInitialize) == 'function'){
			this.formEditInitialize(entityObj);//编辑界面初始化 方法
		}
		
	}else if(pageStatusVal == PageStatusObj.VIEW['value']){
		
		if(typeof(this.formViewOrEditInitialize) == 'function'){
			this.formViewOrEditInitialize(entityObj);//编辑和查看界面 公共初始化 方法
		}
		if(typeof(this.beforeFormViewInitialize) == 'function'){
			this.beforeFormViewInitialize(entityObj);//查看界面之前初始化 方法
		}
		//禁用表单所有字段控件
		easyuiCommon.disableForm(this.getOpenFormWindowObject() , this.editFormId , true);
		if(typeof(this.afterFormViewInitialize) == 'function'){
			this.afterFormViewInitialize(entityObj);//查看界面之后 初始化方法
		}
	}
};

/***
 * 刷新列表数据方法
 * 1、首先获取主页选中的tab选项卡
 * 2、再获取tab选项卡中的iframe对象
 * 3、通过iframe对象中的window对象 调用 listUI的刷新方法
 */
CoreEditUI.prototype.refreshDataGrid = function(paramObj){
	var selectTab = $('#mainTabs').tabs('getSelected');
	if(selectTab && selectTab.find('iframe').length > 0){  
	    var tab_ifram = selectTab.find('iframe')[0];  
	    tab_ifram.contentWindow.listUI.refreshDataGrid();  
    }
};



/***
 * 列表弹出 表单界面save方法
 */
CoreEditUI.prototype.saveForm = function(){
	console.log("this.editFormId="+this.editFormId);
	if(!this.editFormId){
		alert("请通过构造方法初始化editFormId参数");
		return;
	}
	//获取function 引用对象
	var that = this;//重写定义 this对象引用问题，解决嵌套调用 this作用域问题
	
	//表单提交
    $('#'+this.editFormId).form('submit',{
        url: $("#"+that.editFormId).attr("action"),
        onSubmit: function(){
        	//保存之前处理方法
        	that.beforeSaveForm();
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



/***
 * 关闭弹出form表单窗口
 * @param isRefresh 是否刷新列表grid
 */
CoreEditUI.prototype.closeFormWindow = function(isRefresh){
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


