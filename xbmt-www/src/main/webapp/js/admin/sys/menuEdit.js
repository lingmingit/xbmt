



/****
 * 定义模块编辑UI 构造方法
 * 并且 继承 DataBaseEntryEditUI类
 * 
 */
function MenuEditUI(name , editFormId , entryGridId){
	DataBaseEntryEditUI.call(this, name , editFormId , entryGridId); 
}

/****继承父类方法*******/
MenuEditUI.prototype = new DataBaseEntryEditUI();


/**初始化 当前编辑对象 , 'editform'**/
var editUI = new MenuEditUI("菜单管理" ,  "editform" , "functionActionEntry");


/***
 * 指定分录列名称(下拉列表 关联字段列)
 * 如：
 * [
 *  {colName: 'action' , disNameColName:'aName' },
 *  {colName: '' , disNameColName:'' }
 * ]
 */
BaseEntryEditUI.prototype.getEntryComboxColumnArray = function(){
	return [
	        {colName: 'action' , disNameColName:'aName' }
	    ];
};



/***
 * 新增界面初始化 方法
 */
MenuEditUI.prototype.formAddInitialize = function(){
	
	//设置 如果增加下级 禁用 模块、上级菜单 
	if($("#pId").val() != null && $("#pId").val() != ""){
		//$('#parent').combobox('setValue', $("#pId").val());
		$('#parent').combobox('select', $("#pId").val());
		
		//$('#module').combobox('setValue', $("#moduleId").val());//设置value ，后台Controller 不能获取 module对象
		$('#module').combobox('select', $("#moduleId").val());//通过选中方法 ，后台Controller就可以正确获取
		
		//$('#parent').combobox('disable');
		//$('#module').combobox('disable');// disable 控件 该表单参数不能提交后台
		$('#parent').combobox('readonly' ,true);
		$('#module').combobox('readonly' ,true);
	}
	//console.log("longNumbers="+$("#pNumbers").val());
	//设置长编码
	if($("#pNumbers").val() != null && $("#pNumbers").val() != ""){
		$('#longNumber').textbox('setValue' , $("#pNumbers").val());
	}
	//设置树节点级别
	//console.log("pLevels="+$("#pLevels").val());
	if($("#pLevels").val() != null && $("#pLevels").val() != ""){
		var pLevels = $("#pLevels").val();
		var levels = parseInt(pLevels)+1;
		console.log("levels="+levels);
		$('#levels').val(levels);
	}
	
	
};

/***
 * 编辑或查看界面 公共初始化 方法
 */
MenuEditUI.prototype.formViewOrEditInitialize = function(entityObj){
	if(entityObj){
		$("#isLeaf").attr("checked", entityObj.isLeaf);
		  //根据checkbox 设置url 文本框
		 this.setURLtextbox();
	}
};


/***
 * 模块菜单选择 过滤 所对应的菜单 列表
 */
MenuEditUI.prototype.mouduleOnselect = function (rec){
	if(!rec){
		return;
	}
	var that = this;
	//console.log("rec.value="+rec.value);
	var url = easyuiCommon.getWebRootPath()+editUI.getRequestURI()+'/dataItem/menu?moduleId='+rec.value;    
    $('#parent').combobox('reload', url);   
    $('#parent').combobox('setValue', null);
}

/***
 * 增加子节点选中事件
 */
MenuEditUI.prototype.isLeafOnchange = function (){
	
	var that = this;
	 $('#isLeaf').on("change" , function(){
   	  //alert("11="+this.checked);
		  //根据checkbox 设置url 文本框
		 that.setURLtextbox();
     });
};

/***
 * 根据checkbox 设置url 文本框
 */
MenuEditUI.prototype.setURLtextbox = function (){
   	  //alert("11="+this.checked);
   	  if($('#isLeaf').is(":checked")){
   		  $('#url').textbox('enable');
   	  }else{
   		  $('#url').textbox('disable');
   		  $('#url').textbox('clear');
   	  }
};


/***
 * 加载分录数据url地址
 */
BaseEntryEditUI.prototype.getLoadEntryListURL = function(){
	var id = $('#id').val();
	var url = easyuiCommon.getWebRootPath()+editUI.getRequestURI()+'/sys/menu/entryList/'+id;    
	console.log("getLoadEntryListURL.url="+url);
	return url;
};






/****
 * 页面 自加载
 */
$(document).ready(function() {
	
      //增加子节点选中事件
     editUI.isLeafOnchange();
      //console.log("pId="+$("#pId").val()+" \t pNumbers="+$("#pNumbers").val()+" \t pLevels="+$("#pLevels").val());
     //加载分录数据
     editUI.reloadEntryList();
     
     
});

