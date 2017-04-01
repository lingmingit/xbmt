'use strict';
/* App Filters */

var xbmtFilter = angular.module('xbmtFilter', []);








/**
 * 将字符串转换为日期形式
 */
xbmtFilter.filter('todate', function() {  
    return function(input, type) {
//    	if(input == 'undefined' || input == null) {
//    		return "";
//    	}
//    	alert(input + "," + type);
    	if(input == 'undefined' || input == null) {
    		return null;
    	} else {
	    	var date = new Date(input.replace(/-/ig,'/'));
	        return date  
    	}
    };  
}); 

/**
 * 从日期字符串中截取日期部分内容
 */
xbmtFilter.filter('getdate', function() {  
    return function(input, type) {
    	if(input == 'undefined' || input == null) {
    		return null;
    	} else {
    		var str = input.substring(0,10).split("/");
    		var date = '';
    		if(str[0].length == 2) {
    			date = str[2] + '-' + str[0] + '-' + str[1];
    		} else {
    			date = input.replace(/\//g,'-').substring(0,10);
    		}
	    	//var date = input.replace(/\//g,'-').substring(0,10);
	        return date  
    	}
    };  
});

xbmtFilter.filter('to_trusted', ['$sce', function ($sce) {
	return function (text) {
	    return $sce.trustAsHtml(text);
	};
}]); 


/**
 * 从日期字符串中截取日期月和日部分内容
 */
xbmtFilter.filter('getMonthAndDay', function() {
	return function(input) {
		if(input == 'undefined' || input == null) {
			return null;
		} else {
			var str = input.substring(5,10).split("/");
			var date = '';
			if(str[0].length == 2) {
				//date = str[2] + '-' + str[0] + '-' + str[1];
				date =  str[0] + '-' + str[1];
			} else {
				date = input.replace(/\//g,'-').substring(5,10);
			}
			//var date = input.replace(/\//g,'-').substring(0,10);
			return date;
		}
	};
});




/**
 * 格式化 采购产品 报价剩余天数 显示html字符串
 */
xbmtFilter.filter('getPOSDay', function($sce , DateService) {  
    return function(input, param) {
    	if(input == 'undefined' || input == null) {
    		return null;
    	} else {
    		//获取filter的参数数组
    		var args = Array.prototype.slice.call(arguments);  
    		//console.log("input="+input+" \t param="+param);
    		//console.log(args);
    		//console.log("typeof.args[0]="+args[0]+" \t args[1]="+args[1]+" args[2]="+args[2]);
    		
    		if(!args[0] || args[0] == null || args[0] ==""){
    			return "";
    		}
    		if(!args[1] || args[1] == null || args[1] ==""){
    			return "";
    		}
    		if(!args[2] || args[2] == null || args[2] ==""){
    			return "";
    		}
    		var temp = DateService.getPOSDayStr(DateService.getDate(args[0]) , DateService.getDate(args[1]) , DateService.getDate(args[2]));
    		temp = $sce.trustAsHtml(temp);
    		//console.log(temp);
    		return temp;
    	}
    };  
}); 



/****
 数据格式化
 如：数据中心某个值 如果为null 或 空 ，显示为-
 ****/
xbmtFilter.filter('formatNumValue', function(){
	return function(value , type){
		console.log("value="+value);//  注意：0 == '' 返回true，因两边类型不匹配， 0 为false，'' 为false， 所以 false = false 返回true
		if(value == 'undefined' || value == null || value.toString() == "" || value == '&nbsp;'){
			return "—";
		}
		return value;
	};
});

/****
 订单状态(0未处理，1通过审核，2未通过审核,3取消，4终止)
 ****/
xbmtFilter.filter('status', function(){
	return function(input){
		if(input == 0) {
			return "未确认";
		} else if(input == 1) {
			return "进行中";
		} else if(input == 2) {
			return "已取消";
		} else if(input == 3) {
			return "已终止";
		} else if(input == 4) {
			return "已完成";
		}
		return "状态有误";
	};
});

/****
 日期显示星期处理
 ****/
xbmtFilter.filter('weekdate', function(){
	return function(input){
//		Sun,Mon,Tue,Wed,Thu,Fri,Sat
		return input.replace('Sun', '星期天')
			.replace('Mon', '星期一')
			.replace('Tue', '星期二')
			.replace('Wed', '星期三')
			.replace('Thu', '星期四')
			.replace('Fri', '星期五')
			.replace('Sat', '星期六');
	};
});


/****
 数据格式化
 如：数据中心某个值 如果为null 或 空 ，显示为-
 ****/
xbmtFilter.filter('formatNumValue', function(){
	return function(value , type){
		//console.log("value="+value);//  注意：0 == '' 返回true，因两边类型不匹配， 0 为false，'' 为false， 所以 false = false 返回true
		if(value == 'undefined' || value == null || value.toString() == "" || value == '&nbsp;'){
			return "-";
		}
		return value;
	};
});
