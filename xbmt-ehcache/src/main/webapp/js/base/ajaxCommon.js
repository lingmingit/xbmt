


/***
 * ajax get 提交相关业务处理方法
 * @param param 提交参数json对象
 * @param isRefreshPage 是否刷新当前页面
 */
function ajaxGetSubmitOperate(url , param , callSuccessFun , callFailureFun){
	//traditional: true
	$.ajaxSettings.traditional = true;
	$.get(url, param, function(result) {
		var resultObj = result;
		if(typeof(result) == "string"){
			resultObj = eval('('+result+')');//转换json对象
		}
		if(typeof(resultObj) == 'object'){
			if(resultObj.resultFlag == true || resultObj.resultFlag == "true"){
				//成功后调用成功方法
				if(typeof(callSuccessFun) == 'function'){
					eval(callSuccessFun(url , param , resultObj));
				}
			}else{
				//alert("处理失败，详细信息："+resultObj.msg);
				//后台处理失败后调用成功方法
				if(typeof(callFailureFun) == 'function'){
					eval(callFailureFun(url , param , resultObj));
				}
			}
		}else{
			alert("返回结果错误，"+result);
			//后台处理失败后调用成功方法
			if(typeof(callFailureFun) == 'function'){
				eval(callFailureFun(url , param , result));
			}
		}
	});
}


/***
 * ajax post 提交相关业务处理方法
 * @param param 提交参数json对象
 * @param isRefreshPage 是否刷新当前页面
 */
function ajaxPostSubmitOperate(url , param , callSuccessFun , callFailureFun){
	//traditional: true
	$.ajaxSettings.traditional = true;
	$.post(url, param, function(result) {
		var resultObj = result;
		if(typeof(result) == "string"){
			resultObj = eval('('+result+')');//转换json对象
		}
		if(typeof(resultObj) == 'object'){
			if(resultObj.resultFlag == true || resultObj.resultFlag == "true"){
				//成功后调用成功方法
				if(typeof(callSuccessFun) == 'function'){
					eval(callSuccessFun(url , param , resultObj));
				}
			}else{
				//alert("处理失败，详细信息："+resultObj.msg);
				//后台处理失败后调用成功方法
				if(typeof(callFailureFun) == 'function'){
					eval(callFailureFun(url , param , resultObj));
				}
			}
		}else{
			alert("返回结果错误，"+result);
			//后台处理失败后调用成功方法
			if(typeof(callFailureFun) == 'function'){
				eval(callFailureFun(url , param , result));
			}
		}
	});
}
