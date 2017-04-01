



/****
 * 定义基础资料带分录编辑UI 基类 构造方法
 * 方法：保存、关闭
 * 并且 继承 BaseEntryEditUI类
 */
function DataBaseEntryEditUI(name , editFormId , entryGridId){
	BaseEntryEditUI.call(this, name , editFormId , entryGridId); 
}

/****继承父类方法*******/
DataBaseEntryEditUI.prototype = new BaseEntryEditUI();  //这行的作用是：将Parent中将所有通过prototype追加的属性和方法都追加到Child，从而实现了继承



/***
 * form表单 编辑状态时  设置控件状态
 */
DataBaseEntryEditUI.prototype.formEditInitialize = function(){
	//console.log("formEditInitialize");
	$('#numbers').textbox('readonly',true);
};
