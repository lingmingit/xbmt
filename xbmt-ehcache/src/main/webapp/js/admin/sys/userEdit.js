


/****
 * 定义用户编辑UI 构造方法
 * 并且 继承 DataBaseEditUI类
 * 
 */
function UserEditUI(name , editFormId){
	DataBaseEditUI.call(this, name , editFormId); 
}

/****继承父类方法*******/
UserEditUI.prototype = new DataBaseEditUI();


/**初始化 当前编辑对象 , 'editform'**/
var editUI = new UserEditUI("用户管理" ,  "editform");


