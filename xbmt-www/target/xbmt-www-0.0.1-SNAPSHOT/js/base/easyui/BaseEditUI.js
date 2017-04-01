



/****
 * 定义编辑UI 基类 构造方法
 * 方法：保存、关闭
 * 并且 继承 CoreEditUI类
 */
function BaseEditUI(name , editFormId ){
	CoreEditUI.call(this, name); 
	this.editFormId = editFormId;
}

/****继承父类方法*******/
BaseEditUI.prototype = new CoreEditUI();  //这行的作用是：将Parent中将所有通过prototype追加的属性和方法都追加到Child，从而实现了继承

/***
 * 分录编辑
 */
BaseEditUI.prototype.endEditing = function(){
};


