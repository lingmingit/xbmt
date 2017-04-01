

/****
 * 定义CoreUI 基类
 * @param name 指定UI名称
 */
function CoreUI(name) {
    this.name = name;
}

/****
 * 定义基类 构造方法 获取 其它 基类方法
 */
CoreUI.prototype = {
	    constructor: CoreUI, //强制声明构造函数，默认是Object
	    /***
	     * 显示UI 名称
	     */
	    showUIName: function() {
	        alert(this.name);
	    },
	    /***
	     * 弹出提示错误信息
	     * @param title 标题
	     * @param content 内容
	     */
	    showError: function(title , content){
	    	easyuiCommon.showError(title , content);
	    },
	    /***
	     * 弹出成功提示
	     * @param title 标题
	     * @param content 内容
	     * @param onClose点击确认函数引用
	     */
	    showSuccess: function(title , content , onClose){
	    	easyuiCommon.showSuccess(title , content , onClose);
	    },
	    /******
		 * 确认取消 询问提示框
		 * title 提示标题 
		 * content 提示内容
		 * onConfirm 确认 取消 函数引用
		 */
		showConfirm: function(title , content , onConfirm){
			easyuiCommon.showConfirm(title , content , onConfirm);
		},
		/****
		 * 指定【请求URL前缀】URI地址
		 */
		getRequestURI: function(){
			var windowObj = window;
			if(window.parent){
				windowObj = window.parent;
			}
			return $("#AdminRequestURI", windowObj.document).val();
		}
};



