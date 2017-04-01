


/***
 * 刷新验证码方法
 */
function refreshValidateCode(){
	$('#authCode').attr('src', getWebRootPath()+'/captcha.svl?' + Math.random());
}


/***
 * 表单form提交
 */
function formOnSubmit(){
	//验证码输入框对象是否存在
	var validateCodeObj = document.getElementById("validateCode");
	//alert("validateCodeObj="+validateCodeObj);
	if(validateCodeObj){
		var cdCode = validateCodeObj.value;
		//alert("cdCode="+cdCode);
		if(cdCode == null || cdCode == ""){
			alert("请输入验证码");
			return;
		}
		var flag = getValidateCode(cdCode);
		//alert("numbers="+numbers+" \t flag="+flag);
	    if(!flag){
	    	alert("验证码不正确!!");
	    	refreshValidateCode();//重新刷新验证码
	    	return false;
	    }
	}
	return true;
}



/**
 * 检查开市时间范围
 */
 function getValidateCode(cdCode) {
   var url = getWebRootPath()+"/xbmtadmin/console/validateCode/"+cdCode;
	var checkFlag = false;//默认
	// 设置同步
	$.ajaxSetup({
		async : false
	});
	$.post(url , {"validateCode" : cdCode}, function(result) {
		var resultObj = eval('('+result+')');//转换json对象
		//alert("result="+result);
		if (resultObj.flag == true || resultObj.flag == "true") {
			checkFlag = true;//
		}
	});
	// 恢复异步
    $.ajaxSetup({
        async: true
    });
	return checkFlag;
}



/***
 * 重置form表单
 */
function resetForm(){
	//alert("resetForm");
	document.getElementById("username").value = null;
	document.getElementById("password").value = null;
	return false;
}