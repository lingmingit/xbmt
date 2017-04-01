/*'use strict';*/
var xbmtControllers = angular.module('xbmt.controllers', [])
	.config(function($httpProvider){
		$httpProvider.defaults.transformRequest = function(obj){
			var str = [];
			for(var p in obj){
				str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
			}
			return str.join("&");
		};
		$httpProvider.defaults.headers.post = {
			'Content-Type': 'application/x-www-form-urlencoded'
	    };
	});




/*****Controller定义**************/
xbmtControllers.controller('IndexCtrl', function($scope,$rootScope,$http,$state,$timeout , $log) {
	$log.debug("IndexCtrl");
});



/*****Controller定义**************/
xbmtControllers.controller('FindCtrl', function($scope,$rootScope,$http,$state,$timeout , $log) {
	$log.debug("FindCtrl");
});


/*****Controller定义**************/
xbmtControllers.controller('FindCtrl', function($scope,$rootScope,$http,$state,$timeout , $log) {
	$log.debug("FindCtrl");
});


/*****Controller定义**************/
xbmtControllers.controller('MyCtrl', function($scope,$rootScope,$http,$state,$timeout , $log , Auth ,UserService , UserService2 , UserService3 , TestListService2) {
	/*if(!Auth.isLoggedIn()){
		return;
	}*/
	$log.debug("MyCtrl");
	
	$scope.userInfo = {};
	//UserService , UserService2 http摘要认证 , UserService3  自定义token算法认证
	
	 var promise = UserService2.getUser();
     promise.success(function(data, status) {
         console.log("data="+data.code+" entity="+data.entity);
        if(data.code == 1){
        	$scope.userInfo = data.entity;
        }else{
          console.log("data="+data.code+" entity="+data.entity);
        }
     }).error(function(data , status , headers , config){
         console.debug("error.data=" + data+" \t status="+status);
     });
     
     //测试请数据list
     var getList = function(){
    	 var result = TestListService2.list();
         result.$promise.then(function(data){
        	 if(data.code == 1){
        		 $log.debug("list数据请求data=" , data);
        	 }else{
        		 console.log("data.code="+data.code+" \t data.message="+data.message);
        	 }
        } , function(error){
            console.log("registerValid.error="+error);
        });
     };
     //测试请数据list2
     var getList2 = function(){
    	 var result = TestListService2.list2();
         result.$promise.then(function(data){
        	 if(data.code == 1){
        		 $log.debug("list2数据请求data=" , data);
        	 }else{
        		 console.log("data.code="+data.code+" \t data.message="+data.message);
        	 }
        } , function(error){
            console.log("registerValid.error="+error);
        });
     };
     //测试 数据请求list
     getList2();
     
	
     //注销退出
     $scope.logout = function(){
    	 Auth.logoutAll();//注销所有信息：用户登录信息、登录状态、摘要认证信息
    	 $state.go("login");
     };
});


/*****Controller定义**************/
xbmtControllers.controller('LoginCtrl', function($scope,$rootScope,$http,$state,$timeout , $log) {
	$log.debug("LoginCtrl");
	
	
});




/***
 * 登录页面Controller定义
 * 【自定义实现 token 认证方法】
 */
xbmtControllers.controller('LoginCtrl3', function($rootScope , $scope , $http , $state , $log , $ionicHistory , APIURL  , Auth , MD5Service , UserService  ,UserService3 ) {//登录页面
	 var user = $scope.user = {
		      show_error:false , 
		      logo: 'img/login-logo.png'
	  };
	 console.log("$rootScope.LogoInfo="+$rootScope.LogoInfo);
	//初始化 登录logo
	 if($rootScope.LogoInfo){
		 user.logo = APIURL.BaseImageURL + $rootScope.LogoInfo.value;
	 }
	 
	 
	//登录表单 及 获取 token 
    user.loginValid = function(login_form) {
      user.show_error = true;
      login_form.$setDirty();
      if(login_form.$valid) {
    	  
    	  $log.debug("login");
    	  userLogin();
      }
    };
	
	//用户登录操作方法
    var userLogin = function(token){
    	console.log("user.username="+user.username+"\t user.password="+user.password+" \t token="+token);
        //对用户密码进行 MD5密码加密处理
        var md5_pwd = MD5Service.getMD5Value(user.password);
        var new_user = {
          username: user.username,
          password: md5_pwd,
          //token : token ,
          Rememberme:true
        };
        console.log("md5_pwd2="+md5_pwd);
        var promise = UserService3.login(new_user);
        promise.success(function(data, status) {
            console.log("data="+data.code+" entity="+data.entity);
           if(data.code == 1){
           	 $log.debug("登录成功");
           	 Auth.setUser(data.entity , true);
           	 $state.go("foot.my");
           }else{
             console.log("data="+data.code+" data.message="+data.message+" entity="+data.entity);
             user.show_error = true;
             user.error_msg = data.message;
           }
        }).error(function(data , status , headers , config){
            console.debug("error.data=" + data+" \t status="+status);
        });
    };

    $scope.goBack = function(){
    	$state.go("foot.index");
    };
});



/***
 * http 摘要认证 测试
 * 后台配置成功，但是是浏览器弹出窗口提示登录，怎么配合自己登录 还未实现，
 * 登录页面Controller定义
 */
xbmtControllers.controller('LoginCtrl2', function($rootScope , $scope , $http , $state , $log , $ionicHistory , APIURL  , Auth , MD5Service , UserService  ,UserService2 ) {//登录页面
	 var user = $scope.user = {
		      show_error:false , 
		      logo: 'img/login-logo.png'
	  };
	 console.log("$rootScope.LogoInfo="+$rootScope.LogoInfo);
	//初始化 登录logo
	 if($rootScope.LogoInfo){
		 user.logo = APIURL.BaseImageURL + $rootScope.LogoInfo.value;
	 }
	 
	 
	//登录表单 及 获取 token 
    user.loginValid = function(login_form) {
      user.show_error = true;
      login_form.$setDirty();
      if(login_form.$valid) {
    	  $log.debug("login");
        //首先获取登录token
        var promise = UserService2.getToken();
        promise.success(function(data, status , headers, config) {
            // console.log("data="+data.code+" entity="+data.entity);
            // $log.debug("headers=" , headers);
            // $log.debug("config=" , config);			        //WWW-Authenticate
           //  $log.debug("headers('WWW-Authenticate')=" , headers('WWW-Authenticate'));
            if(data.code == 1){
            	// $log.debug();
            	userLogin(data.entity['nonce']);
            }else{
              console.log("data="+data.code+" entity="+data.entity);
            }
         }).error(function(data , status , headers , config){
             console.debug("error.data=" + data+" \t status="+status);
         });
      }
    };
	
	//用户登录操作方法
    var userLogin = function(token){
    	console.log("user.username="+user.username+"\t user.password="+user.password+" \t token="+token);
        //对用户密码进行 MD5密码加密处理
        var md5_pwd = MD5Service.getMD5Value(user.password);
       // console.log("md5_pwd="+md5_pwd+" \t token="+token);
        //Password：加密后的密码（MD5（（MD5（Password）+Token））
       // md5_pwd = MD5Service.getMD5Value(md5_pwd+token);
        var new_user = {
        		username: user.username,
          password: md5_pwd,
          token : token ,
          Rememberme:true
        };
         console.log("md5_pwd2="+md5_pwd);
       //var result = UserService.login($.param(new_user));
         //"UserName="+user.UserName + "&Password="+ new_user.Password+"&Rememberme="+new_user.Rememberme
        var promise = UserService2.login(new_user);
        promise.success(function(data, status) {
            console.log("data="+data.code+" entity="+data.entity);
           if(data.code == 1){
           	    $log.debug("登录成功");
           	 Auth.setUser(data.entity , true);
           	 $state.go("foot.my");
           }else{
        	   console.log("data="+data.code+" data.message="+data.message+" entity="+data.entity);
               user.show_error = true;
               user.error_msg = data.message;
           }
        }).error(function(data , status , headers , config){
            console.debug("error.data=" + data+" \t status="+status);
        });
    };

    $scope.goBack = function(){
    	$state.go("foot.index");
    };
});



/***
 * 登录页面Controller定义
 */
xbmtControllers.controller('LoginCtrl', function($rootScope , $scope , $http , $state , $log , $ionicHistory , APIURL  , Auth , MD5Service , UserService  ,UserService2 ) {//登录页面
	 var user = $scope.user = {
		      show_error:false , 
		      logo: 'img/login-logo.png'
	  };
	 console.log("$rootScope.LogoInfo="+$rootScope.LogoInfo);
	//初始化 登录logo
	 if($rootScope.LogoInfo){
		 user.logo = APIURL.BaseImageURL + $rootScope.LogoInfo.value;
	 }
	 
	 
	//登录表单 及 获取 token 
    user.loginValid = function(login_form) {
      user.show_error = true;
      login_form.$setDirty();
      if(login_form.$valid) {
    	  
    	  $log.debug("login");
    	  
        //首先获取登录token
        var promise = UserService2.getToken();
        promise.success(function(data, status) {
             console.log("data="+data.code+" entity="+data.entity);
            if(data.code == 1){
            	userLogin(data.entity);
            }else{
              console.log("data="+data.code+" entity="+data.entity);
            }
         }).error(function(data , status , headers , config){
             console.debug("error.data=" + data+" \t status="+status);
         });
      }
    };
	
	//用户登录操作方法
    var userLogin = function(token){
    	console.log("user.username="+user.username+"\t user.password="+user.password+" \t token="+token);
        //对用户密码进行 MD5密码加密处理
        var md5_pwd = MD5Service.getMD5Value(user.password);
       // console.log("md5_pwd="+md5_pwd+" \t token="+token);
        //Password：加密后的密码（MD5（（MD5（Password）+Token））
       // md5_pwd = MD5Service.getMD5Value(md5_pwd+token);
        var new_user = {
          UserName: user.username,
          Password: md5_pwd,
          token : token ,
          Rememberme:true
        };
         console.log("md5_pwd2="+md5_pwd);
       //var result = UserService.login($.param(new_user));
         //"UserName="+user.UserName + "&Password="+ new_user.Password+"&Rememberme="+new_user.Rememberme
        var promise = UserService2.login(new_user);
        promise.success(function(data, status) {
            console.log("data="+data.code+" entity="+data.entity);
           if(data.code == 1){
           	    $log.debug("登录成功");
           }else{
             console.log("data="+data.code+" entity="+data.entity);
           }
        }).error(function(data , status , headers , config){
            console.debug("error.data=" + data+" \t status="+status);
        });
    };

    $scope.goBack = function(){
    	$state.go("foot.index");
    };
});

