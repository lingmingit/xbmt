



/****
 * 定义模块编辑UI 构造方法
 * 并且 继承 DataBaseEditUI类
 * 
 */
function ModulesEditUI(name , editFormId){
	DataBaseEditUI.call(this, name , editFormId); 
}

/****继承父类方法*******/
ModulesEditUI.prototype = new DataBaseEditUI();


/**初始化 当前编辑对象 , 'editform'**/
var editUI = new ModulesEditUI("模块管理" ,  "editform");


