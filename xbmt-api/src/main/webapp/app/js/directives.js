/*'use strict';*/
/* App Directives */


var xbmtDirectives = angular.module('xbmt.directives', []);
/****
 * 定义hideTabs指令， 用于某些页面动态隐藏底部菜单栏
 */
xbmtDirectives.directive('hideTabs', function($rootScope) {
    return {
        restrict: 'A',
        link: function(scope, element, attributes) {
        	//$ ionicView.beforeEnter 视图是即将进入并成为活动视图。 类似于Android的activity中的onResume（）方法。
            scope.$on('$ionicView.beforeEnter', function() {
                scope.$watch(attributes.hideTabs, function(value){
                    $rootScope.hideTabs = value;
                });
            });
            //$ ionicView.beforeLeave  视图是即将离开，不再是活动视图。  类似于Android的activity中的onPause（）方法。
            scope.$on('$ionicView.beforeLeave', function() {
                $rootScope.hideTabs = false;
            });
        }
    };
});

/**
 * 循环结束时执行指令
 */
xbmtDirectives.directive('repeatDone', function () {
   return function (scope, element, attrs) {
     if (scope.$last) { // all are rendered
       scope.$eval(attrs.repeatDone);
     }
   }
});

/**
 * 将字符串转换为数字
 * 解决错误：Error: [ngModel:numfmt] Expected `501` to be a number
 */
xbmtDirectives.directive('input', function(){
    return {
        require: 'ngModel',
        link: function(scope, elem, attrs, ngModel){
            if(attrs.type == 'number'){
                ngModel.$formatters.push(function(value){
                    return parseFloat(value);
                });
            }
        }
    };
});

/**
 * 将字符串转换为数字(如果内容为空，则默认为0)
 * 解决错误：Error: [ngModel:numfmt] Expected `501` to be a number
 */
xbmtDirectives.directive('input2', [function() {
    return {
        restrict: 'E',
        require: '?ngModel',
        link: function(scope, element, attrs, ngModel) {
            if (
                   'undefined' !== typeof attrs.type
                && 'number' === attrs.type
                && ngModel
            ) {
                ngModel.$formatters.push(function(modelValue) {
                    return Number(modelValue);
                });

                ngModel.$parsers.push(function(viewValue) {
                    return Number(viewValue);
                });
            }
        }
    }
}]);

/****
 * 文件上传指令
 * 经过调试发现其中56行代码【scope.file= ...】 赋值后,在Controller中使用时，该file为空
 * 1、第一种方法：通过58行代码传递函数 参数，如 【scope.getFile(scope.file);】 这种方法Controller中是否可以正确获取的
 * 2、第二种方法 Controller中单独定义 scope.user.file 对象，通过这个对象进行赋值操作
 */
xbmtDirectives.directive('fileModel', ['$parse', function ($parse) {
	  return {
	    restrict: 'A',
	    link: function(scope, element, attrs, ngModel) {
	      var model = $parse(attrs.fileModel);
	      var modelSetter = model.assign;
	      element.bind('change', function(event){
	        scope.$apply(function(){
	          modelSetter(scope, element[0].files[0]);
	        });
	        //附件预览
	        scope.file = (event.srcElement || event.target).files[0];
	        scope.user.file = scope.file;
	        scope.getFile();
	      });
	    }
	  };
}]);


/*****
 * 我的产品列表根据产品状态【0待发布，1已发布 ,2待处理，3不同意发布，4删除，我的里面不显示，5已取消】 显示不同icon
 */
xbmtDirectives.directive('productStatus' ,['$rootScope', '$compile' , '$log' , 'PRODUCT_STATUS' , function($rootScope , $compile , $log , PRODUCT_STATUS){
	//console.log("productStatus.........");
	
	//根据状态初始化icon 显示
	var getProductStatus = function(statusVal , publishDate){
		var html = "";
		var status = '';
		//0待发布，1已发布 ,2待处理，3不同意发布，4删除，我的里面不显示，5已取消
		if(statusVal == PRODUCT_STATUS.WaitPublish['value']){
			status = 'WaitPublish';
		}else if(statusVal == PRODUCT_STATUS.Publish['value']){
			status = 'Publish';
		}else if(statusVal == PRODUCT_STATUS.Pending['value']){
			status = 'Pending';
		}else if(statusVal == PRODUCT_STATUS.Disagree['value']){
			status = 'Disagree';
		}else if(statusVal == PRODUCT_STATUS.Del['value']){
			status = 'Del';
		}else if(statusVal == PRODUCT_STATUS.Cancel['value']){
			status = 'Cancel';
		}
		//console.log("status="+status+" \t statusVal="+statusVal);
		html += '<i ng-click="iconClick1(item.status);" class="icon '+ PRODUCT_STATUS[status]['icon']+'" style="color: '+ PRODUCT_STATUS[status]["color"]+'; float: left;"></i>';
		html += '<span class="" style="margin-left: 10px;">'+PRODUCT_STATUS[status]["alias"]+' </span>';
		html += '<span class="list_riqi2" style="margin-left: 10px;">'+publishDate+'</span>';
		//html += '<span  style="margin-left: 10px;">'+statusVal+'</span>';
		//html += '<span  style="margin-left: 10px;" ><input ng-model="item.status" /></span>';
		//已取消、待发布  增加删除按钮
		if(statusVal == PRODUCT_STATUS.Cancel['value'] || statusVal == PRODUCT_STATUS.WaitPublish['value']){
			html += '<span ng-click="delProduct();" class="icon xbmt-dustbin color_288" style="float: right;"></span>';
		}
		return html;
	};
	
	return {
		restrict: 'E',
		template: '<div style="width: 100%;"></div>' , 
		replace: true ,
		scope: {
            item: '='
        },
		link: function(scope, elem, attrs){
			//console.log("ps.elem..="+elem+" \t attrs.status="+attrs.status+" \t publishDate="+attrs.publishDate+" \t icon-click="+attrs.iconClick);
			//测试点击事件， 必须通过function中进行调用父作用域的，直接写到标签上不能使用
			scope.iconClick1 = function(status){
				console.log("status="+status);
				//第一种方法调用
				//scope.$parent.vm.iconClick(attrs.id  , attrs.index , attrs.status);
				//第二种方法 eval 函数调用
				var str = "scope.$parent.vm.iconClick(attrs.id  , attrs.index , attrs.status);";
				eval(str);
			};
			
			//删除产品方法
			scope.delProduct = function(){
				scope.$parent.vm.delProduct(attrs.id , attrs.index);
			};
			
			//初始化产品状态icon
			var html = getProductStatus(scope.item.status , attrs.publishDate);
			elem.append($compile(html)(scope));
		} 
	};
}]);





/*****
 * 我的产品列表根据产品状态【0待发布，1已发布 ,2待处理，3不同意发布，4删除，我的里面不显示，5已取消】 显示不同 操作按钮
 */
xbmtDirectives.directive('productTools' ,['$rootScope','$compile' , '$log' , 'PRODUCT_STATUS' , 'MyProductService' , function($rootScope , $compile , $log , PRODUCT_STATUS , MyProductService){
	console.log("productTools.........");
	
	//根据状态初始化 工具栏按钮
	var getBtnToolsHtml = function(statusVal , id , index ){
		var html = "";
		var status = '';
		//0待发布，1已发布 ,2待处理，3不同意发布，4删除，我的里面不显示，5已取消
		if(statusVal == PRODUCT_STATUS.WaitPublish['value']){
			status = 'WaitPublish';
		}else if(statusVal == PRODUCT_STATUS.Publish['value']){
			status = 'Publish';
		}else if(statusVal == PRODUCT_STATUS.Pending['value']){
			status = 'Pending';
		}else if(statusVal == PRODUCT_STATUS.Disagree['value']){
			status = 'Disagree';
		}else if(statusVal == PRODUCT_STATUS.Del['value']){
			status = 'Del';
		}else if(statusVal == PRODUCT_STATUS.Cancel['value']){
			status = 'Cancel';
		}
		var toolsArray = PRODUCT_STATUS[status]['tools'];
		//console.log("attrs.id="+id+" \t attrs.index="+index+" \t status="+status+" \t tools="+toolsArray);
		
		 angular.forEach(toolsArray, function(value , index){
			 html += '<span class="icon '+value.icon+' my_tools_button color_288" ';
			 
			 if(toolsArray.length > 1){//index < (toolsArray.length - 1) 
				 html += ' style="margin-right: 15px;" ';
				 html += 'ng-click="btnNgClick(\''+value.ngClick+'\');">'+value.iconName+'</span>';
				 html += '<span class="my_left_line my_tools_button" style="margin-right: 15px;"></span>';
			  }else{
				  html += 'ng-click="btnNgClick(\''+value.ngClick+'\');">'+value.iconName+'</span>';
			  }
		  });
		// html += '<span style="margin-right: 15px;" ng-click="handle();">测试<span>';
		return html;
	};
	
	/***
	 * 根据状态 重新初始化button
	 */
	var resetBtn = function(scope , elem , id , index){
		 elem.empty();//首先删除所有子对象
		//重新初始化按钮
		var tempHtml = getBtnToolsHtml(scope.item.status , id , index);
		elem.append($compile(tempHtml)(scope));
	};
	
	
	return {
		restrict: 'E',
		template: '<div class="zhibiao_my padding-top padding-bottom" ></div>' , 
		replace: true ,
		scope: {
            item: '=',
            handle: '&ptHandle'
        },
		link: function(scope, elem, attrs){
			//console.log("ps.elem..="+elem+" \t attrs.status="+attrs.status+" \t publishDate="+attrs.publishDate+" \t icon-click="+attrs.iconClick);
			//操作按钮 调用函数方法
			scope.btnNgClick = function(ngClick){
				//console.log(elem);
				//第一种方法
				//scope.$parent.vm.delProduct(attrs.id , attrs.index);//可以成功调用
				//第二种方法
				var str = "scope.$parent.vm."+ngClick+"(attrs.id , attrs.index);";
				eval(str);
				//第三种方法
				/*if(ngClick == "downProduct"){
					downProduct(scope , elem ,attrs.id , attrs.index );
				}else if(ngClick == "upProduct"){
					upProduct(scope , elem ,attrs.id , attrs.index );
				}*/ 
				//测试 指令中调用外部Controller中定义函数
				//scope.handle({'id': attrs.id , 'index':attrs.index});
			};
			
			//初始化
			var html = getBtnToolsHtml(scope.item.status , attrs.id , attrs.index );
			elem.append($compile(html)(scope));
		}
	};
}]);



/***测试指令***/
xbmtDirectives.directive('hello' ,['$rootScope' ,  function($rootScope){
	//console.log("pymtDirectives.directive.myDiv=11");
	return {
		restrict: 'AE',
		template: '<div> hi my demo</div>' , 
		replace: true
	};
}]);


xbmtDirectives.directive('hello2' ,['$rootScope' ,'$compile' , '$log' ,  function($rootScope , $compile , $log){
	console.log("pymtDirectives.directive.hello2=88");
	return {
		restrict: 'E',
		template: '<div> hi my demo88</div>' , 
		replace: true,
		link: function(scope, elem){
			console.log("elem="+elem);
			var html = "<b>动态增加内容 {{name}}</b>";
			elem.append($compile(html)(scope));
			 $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
				 console.log("hello66.app.toState="+toState.name+" \t toParams="+toParams+" \t fromState="+fromState.name+" \t fromParams="+fromParams);
				 $log.debug('toState', toState);
				 scope.name = toState.name;
			 });
		}
	};
}]);


/***
 * 测试双向数据绑定 2
 */
xbmtDirectives.directive("myclick", function() {
    return function (scope, element, attr) {
        element.on("click", function() {
            scope.counter++;
            console.log("scope.counter="+scope.counter);
            scope.$digest();
        });
    };
});
