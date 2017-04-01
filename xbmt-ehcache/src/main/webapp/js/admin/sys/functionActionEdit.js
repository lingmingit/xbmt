



/****
 * 定义模块编辑UI 构造方法
 * 并且 继承 DataBaseEditUI类
 * 
 */
function FunctionActionEditUI(name , editFormId){
	DataBaseEditUI.call(this, name , editFormId); 
}

/****继承父类方法*******/
FunctionActionEditUI.prototype = new DataBaseEditUI();


/**初始化 当前编辑对象 , 'editform'**/
var editUI = new FunctionActionEditUI("系统功能管理" ,  "editform");



/***
 * 新增界面初始化 方法
 */
FunctionActionEditUI.prototype.formAddInitialize = function(){
	
	
};



