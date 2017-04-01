// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.services' is found in services.js
// 'starter.controllers' is found in controllers.js

angular.module('xbmtApp', ['ionic','ngResource','ngCookies' ,'door3.css', 'ion-affix',
        'xbmtCommon' , 'xbmtFilter' , 'xbmt.controllers', 'xbmt.services', 'xbmt.directives'
        ])
                           
.run(function($rootScope , $ionicHistory,$ionicPlatform , $state , $location , $log , ACCESS_LEVELS ,  APIURL, Auth ) {

	$rootScope.goBackCustomerFunc = function(){
		$ionicHistory.goBack();
	};
	$rootScope.goBackFunc = function(){
		$rootScope.goBackCustomerFunc ();
	};
	
	
	
	
  $ionicPlatform.ready(function() {
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    if (window.cordova && window.cordova.plugins && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
      cordova.plugins.Keyboard.disableScroll(true);

    }
    if (window.StatusBar) {
      // org.apache.cordova.statusbar required
      StatusBar.styleDefault();
    }
  });
  
	//自定义事件
   $rootScope.$on('auth:loginRequired' , function(){
		console.log("$rootScope.auth:loginRequired");
		$location.path('/login');
  });
  /**增加路由状态监听事件***/
  $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
	  //$state.current.name
	  //event, toState, toParams, fromState, fromParams
	 // console.log("app.toState="+toState.name+" \t toParams="+toParams+" \t fromState="+fromState.name+" \t fromParams="+fromParams);
	  //$log.debug('toState', toState);
	 // $log.debug('toParams', toParams);
		$rootScope.fromState = fromState.url == null || fromState.url == '^' ? null : fromState;
		$rootScope.fromParams = fromParams;
	  if(toState && toState.name){
           if((toState.access_levels == ACCESS_LEVELS.user) && !Auth.isLoggedIn()){
        	   console.log(" go to LoginPage 333="+Auth.isLoggedIn());
        	  // $state.go("login");//状态 调整不起作用
        	  $location.path('/login');
        	  return;
           }
       }
	  
  });
})

.config(['$logProvider', function($logProvider){//增加调试信息打印设置控制
    $logProvider.debugEnabled(true);
}])

.config(function($stateProvider, $urlRouterProvider, $ionicConfigProvider , $httpProvider , ACCESS_LEVELS) {
	/*******ionic公共平台相关参数配置****************/
//	$ionicConfigProvider.views.maxCache(0);
	
	//将ion-nav-back-button中的文本隐藏
	$ionicConfigProvider.backButton.text("");
	$ionicConfigProvider.backButton.previousTitleText(false);
	
	$ionicConfigProvider.platform.ios.tabs.style('standard'); 
    $ionicConfigProvider.platform.ios.tabs.position('bottom');
    $ionicConfigProvider.platform.android.tabs.style('standard');// Tab
    $ionicConfigProvider.platform.android.tabs.position('standard');// Tab

    $ionicConfigProvider.platform.ios.navBar.alignTitle('center'); 
    $ionicConfigProvider.platform.android.navBar.alignTitle('center');// 

//    $ionicConfigProvider.platform.ios.backButton.previousTitleText('').icon('ion-ios-arrow-thin-left');
    $ionicConfigProvider.platform.android.backButton.previousTitleText('').icon('ion-chevron-left');
    $ionicConfigProvider.platform.ios.backButton.previousTitleText('').icon('ion-chevron-left');

    $ionicConfigProvider.platform.ios.views.transition('ios'); 
    $ionicConfigProvider.platform.android.views.transition('android');
    
	// 注册拦截器 UserInterceptor \ UserInterceptor2 HTTP摘要认证   \UserInterceptor3 自定义token 算法
    $httpProvider.interceptors.push('UserInterceptor2');
    // 跨域解决方案，CORS规范
    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
    //$httpProvider.defaults.headers.post['Origin'] = '*';
    // Override $http service's default transformRequest
   
    /*$httpProvider.defaults.transformRequest = [function(data) {
     // return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
    	console.log("transformRequest.data");
    	console.log(data);
    	return data;
    }];*/
    

  
  // 底板菜单 setup an abstract state for the tabs directive
  $stateProvider.state('foot', {
    url: '/foot',
    abstract: true,
    templateUrl: '../app/templates/foot.html'
    /*controller : 'FootCtrl'*/
  })
  	/** ********首页**** */
	.state('foot.index', {
		url : '/index',
		access_levels : ACCESS_LEVELS.pub,
		css : 'tst_css/test1.css',
		 cache:'false',
		views : {
			'foot-index' : {
				templateUrl : '../app/templates/index.html',
				controller : 'IndexCtrl'
			}
		}
	})
	
  /***********发现***********/
  .state('foot.find', {
      url: '/find',
      access_levels: ACCESS_LEVELS.pub,
      cache:'false',
      views: {
          'foot-find': {
              templateUrl: '../app/templates/find.html',
              controller: 'FindCtrl'
          }
      }
  })
  
      /************我的******/
   .state('foot.my', {
    url: '/my',
    access_levels: ACCESS_LEVELS.user,
    cache:'false',
    views: {
      'foot-my': {
        templateUrl: '../app/templates/my.html',
        controller: 'MyCtrl'
      }
    } 
   })
  
  
   //登录
   .state('login', {
		url : '/login',
		access_levels: ACCESS_LEVELS.pub,
		cache:'false',
		css: 'css/form.css',
		controller: 'LoginCtrl2', // LoginCtrl  、LoginCtrl2  http摘要认证 测试 、 LoginCtrl3 自定义token
		templateUrl : '../app/templates/login.html'
	})
  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/foot/index');

});
